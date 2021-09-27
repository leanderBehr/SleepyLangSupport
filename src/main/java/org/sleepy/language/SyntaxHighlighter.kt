// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.sleepy.language

import com.intellij.lexer.Lexer
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class SyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return AdaptedSleepyLexer()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return attributesOfType[tokenType] ?: arrayOf()
    }

    companion object {
        val attributesOfType = mapOf(
            TokenType.BAD_CHARACTER to arrayOf(HighlighterColors.BAD_CHARACTER),
            SleepyTokenType.KEYWORD to arrayOf(DefaultLanguageHighlighterColors.KEYWORD),
            SleepyTokenType.IDENTIFIER to arrayOf(DefaultLanguageHighlighterColors.IDENTIFIER),
            SleepyTokenType.OPERATOR to arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN),
            SleepyTokenType.COMMENT to arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT),
            SleepyTokenType.BRACES to arrayOf(DefaultLanguageHighlighterColors.BRACES),
            SleepyTokenType.BRACKETS to arrayOf(DefaultLanguageHighlighterColors.BRACKETS),
            SleepyTokenType.SEMICOLON to arrayOf(DefaultLanguageHighlighterColors.SEMICOLON),
            SleepyTokenType.COMMA to arrayOf(DefaultLanguageHighlighterColors.COMMA),
            SleepyTokenType.DOT to arrayOf(DefaultLanguageHighlighterColors.DOT),
            SleepyTokenType.PARENTHESES to arrayOf(DefaultLanguageHighlighterColors.PARENTHESES),
            SleepyTokenType.STRING to arrayOf(DefaultLanguageHighlighterColors.STRING),
            SleepyTokenType.ANNOTATION to arrayOf(DefaultLanguageHighlighterColors.METADATA),
            SleepyTokenType.NUMBER to arrayOf(DefaultLanguageHighlighterColors.NUMBER),
//            SleepyTokenType.SEPARATORS to arrayOf(DefaultLanguageHighlighterColors.),
        ).entries.associate {
            it.key to (it.value.asList()
                .map { color -> TextAttributesKey.createTextAttributesKey(it.key.toString() + "_TType", color) }).toTypedArray()
        }
    }
}