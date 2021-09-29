package org.sleepy.language

import com.intellij.history.core.Paths
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.jetbrains.rd.util.printlnError
import jep.Interpreter
import jep.JepConfig
import jep.JepException
import jep.MainInterpreter
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.sleepy.settings.AppSettingsState
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

    fun init() {
        println("THREAD ID: ${Thread.currentThread().id}")
        val path = PluginManagerCore.getPlugin(PluginId.getId("org.sleepy.SleepyLangSupport"))?.pluginPath
        MainInterpreter.setJepLibraryPath(Paths.appended(path.toString(), "lib/jep.so"))
        setCompiler(AppSettingsState.getInstance().SleepyPath)
    }

    fun tokenize(buffer: CharSequence): List<Token> {
        println("THREAD ID: ${Thread.currentThread().id}")

        val currentInterpreter = if (interpreter != null) interpreter!! else return listOf()
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

    fun setCompiler(sleepyPath: String) {
        println("THREAD ID: ${Thread.currentThread().id}")

        if (interpreter != null) interpreter!!.close()
        interpreter = try {
            val config = JepConfig()
            config.addIncludePaths(sleepyPath)
            config.redirectStdout(System.out)

            val newInterpreter = config.createSubInterpreter()
            newInterpreter.runScript("$sleepyPath/sleepy/plugin_setup.py")
            newInterpreter
        } catch (e: JepException) {
            printlnError(e.message ?: "JEP ERROR WITHOUT MESSAGE")
            null
        }
    }
}

data class Token(val start: Int, val end: Int, val type: String)

