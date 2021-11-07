package org.sleepy.language.compilerbased

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import org.sleepy.language.SleepyCompiler
import kotlin.system.measureTimeMillis

class CompilerBasedParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        val time = measureTimeMillis {  SleepyCompiler.get { parse(builder) } }
        println("Parsing took ${time}ms")
        return builder.treeBuilt
    }

}
