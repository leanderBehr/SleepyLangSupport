package org.sleepy.language

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.tree.IElementType

class SleepyTokenType private constructor(val name: String) : IElementType(name, SleepyLanguage) {
    override fun toString(): String = name

    companion object {


        private val StringToHighlightMap = mapOf(
            "BAD_CHARACTER" to arrayOf(HighlighterColors.BAD_CHARACTER),
            "KEYWORD" to arrayOf(DefaultLanguageHighlighterColors.KEYWORD),
            "IDENTIFIER" to arrayOf(DefaultLanguageHighlighterColors.CLASS_NAME),
            "OPERATOR" to arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN),
            "COMMENT" to arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT),
            "BRACES" to arrayOf(DefaultLanguageHighlighterColors.BRACES),
            "BRACKETS" to arrayOf(DefaultLanguageHighlighterColors.BRACKETS),
            "SEMICOLON" to arrayOf(DefaultLanguageHighlighterColors.SEMICOLON),
            "COMMA" to arrayOf(DefaultLanguageHighlighterColors.COMMA),
            "DOT" to arrayOf(DefaultLanguageHighlighterColors.DOT),
            "PARENTHESES" to arrayOf(DefaultLanguageHighlighterColors.PARENTHESES),
            "STRING" to arrayOf(DefaultLanguageHighlighterColors.STRING),
            "ANNOTATION" to arrayOf(DefaultLanguageHighlighterColors.METADATA),
            "NUMBER" to arrayOf(DefaultLanguageHighlighterColors.NUMBER),
            "WHITESPACE" to arrayOf()
        )

        private val NameToTypeMap = mutableMapOf<String, SleepyTokenType>()

        fun nameToType(name: String): IElementType = NameToTypeMap.getOrPut(name) { SleepyTokenType(name) }

        fun typeToHighlight(type: IElementType): Array<TextAttributesKey> = SleepyCompiler.get {
            val highlightString = tokenNameToHighlightString(type.toString())
            StringToHighlightMap[highlightString] ?: arrayOf()
        }
    }
}

fun isSleepyTokenType(type: IElementType, name: String) = type is SleepyTokenType && type.name == name