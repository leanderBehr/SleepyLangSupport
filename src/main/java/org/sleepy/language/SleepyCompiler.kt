package org.sleepy.language

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.lang.PsiBuilder
import com.intellij.notification.NotificationAction
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.options.ShowSettingsUtil
import jep.*
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import net.lingala.zip4j.ZipFile
import org.sleepy.settings.AppSettingsState
import org.sleepy.ui.SleepyNotifier
import java.nio.file.Files
import java.util.concurrent.Executors

object SleepyCompiler {
    val context = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    init {
        get { init() }
    }

    inline fun <T> get(crossinline block: SleepyCompilerInternal.() -> T): T {
        return runBlocking {
            withContext(context) {
                SleepyCompilerInternal.block()
            }
        }
    }
}

object SleepyCompilerInternal {
    private var interpreter: Interpreter? = null
    private val jepTmpDir = Files.createTempDirectory("jepDir").toAbsolutePath()
    private val pluginDirPath = PluginManagerCore.getPlugin(PluginId.getId("org.sleepy.SleepyLangSupport"))?.pluginPath

    fun init() {
        val jepZip = ZipFile("""$pluginDirPath/lib/jep.zip""")
        jepZip.extractAll(jepTmpDir.toString())

        MainInterpreter.setJepLibraryPath("$jepTmpDir/jep/libjep.so")
        MainInterpreter.setInitParams(PyConfig().setOptimizeFlag(2))
        setCompiler(AppSettingsState.instance.sleepyPath, AppSettingsState.instance.pythonIncludePath)
    }

    fun tokenNameToHighlightString(name: String): String {
        val currentInterpreter = interpreter ?: return ""
        return currentInterpreter.invoke("token_name_to_highlight", name) as String
    }

    fun parse(builder: PsiBuilder) {
        val currentInterpreter = interpreter ?: return
        builder.setDebugMode(true)
        class Marker(val marker: PsiBuilder.Marker) {
            fun drop() = marker.drop()
            fun precede() = Marker(marker.precede())
            fun done(name: String) {
                if(name == "FILE") marker.done(SleepyAstType.File)
                else marker.done(SleepyAstType.nameToType(name))
            }
            fun error(message: String) = marker.error(message)
        }

        class TokenStream {
            fun mark() = Marker(builder.mark())
            fun advance() = builder.advanceLexer()
            fun current(): String = builder.tokenType.toString()
        }

        currentInterpreter.invoke("parse", TokenStream())
    }

    fun tokenize(buffer: CharSequence): List<Token> {
        val currentInterpreter = interpreter ?: return listOf(Token(0, buffer.length, ""))
        val result = currentInterpreter.invoke("tokenize", buffer.toString()) as List<*>

        val tokenTypes = result[0] as List<*>
        val tokenPositions = result[1] as List<*>

        assert(tokenTypes.size == tokenPositions.size)

        val tokens = mutableListOf<Token>()

        for ((i, type) in tokenTypes.withIndex()) {
            val typeStr = type as String
            val tokenStart = (tokenPositions[i] as Long).toInt()
            val tokenEnd = if (i == tokenTypes.size - 1) buffer.length else (tokenPositions[i + 1] as Long).toInt()
            tokens.add(Token(tokenStart, tokenEnd, typeStr))
        }
        return tokens
    }

    fun setCompiler(sleepyPath: String, pythonHome: String?) {
        if (interpreter != null) interpreter!!.close()

        val config = JepConfig()
        config.addIncludePaths(sleepyPath, jepTmpDir.toString())
        if (pythonHome != null) config.addIncludePaths(pythonHome)
        config.redirectStdout(System.out)

        var newInterpreter: SubInterpreter? = null
        interpreter = try {
            newInterpreter = config.createSubInterpreter()
            newInterpreter.runScript("$sleepyPath/sleepy/plugin_setup.py")
            newInterpreter
        } catch (e: JepException) {
            SleepyNotifier.notifyError(
                "Cannot connect to Sleepy Compiler at $sleepyPath/sleepy/plugin_setup.py \n" + e.message,
                NotificationAction.create("Edit Path") { _ ->
                    ShowSettingsUtil.getInstance().editConfigurable(null, "Sleepy Path")
                })
            newInterpreter?.close()
            null
        }
        if(interpreter != null) SleepyNotifier.notifyInfo("Successfully started sleepy compiler")
    }
}

data class Token(val start: Int, val end: Int, val type: String)

