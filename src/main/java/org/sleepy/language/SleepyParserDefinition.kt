package org.sleepy.language

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.sleepy.language.compilerbased.CompilerBasedLexer
import org.sleepy.language.compilerbased.CompilerBasedParser
import org.sleepy.language.psi.SleepyPsiFile

class SleepyParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = CompilerBasedLexer()

    override fun createParser(project: Project?): PsiParser = CompilerBasedParser()

    override fun getFileNodeType(): IFileElementType = SleepyAstType.File

    override fun getCommentTokens(): TokenSet = TokenSet.create(SleepyTokens.nameToType("COMMENT"))

    override fun getStringLiteralElements(): TokenSet = TokenSet.create(SleepyTokens.nameToType("str"))

    override fun getWhitespaceTokens(): TokenSet = TokenSet.create(SleepyTokens.nameToType("WHITESPACE"))

    override fun createElement(node: ASTNode?): PsiElement = ASTWrapperPsiElement(node!!)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = SleepyPsiFile(viewProvider)
}