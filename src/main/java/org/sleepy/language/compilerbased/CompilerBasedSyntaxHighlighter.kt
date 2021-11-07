package org.sleepy.language.compilerbased

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.sleepy.language.SleepyTokens

class CompilerBasedSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = CompilerBasedLexer()
    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        SleepyTokens.typeToHighlight(tokenType)
}