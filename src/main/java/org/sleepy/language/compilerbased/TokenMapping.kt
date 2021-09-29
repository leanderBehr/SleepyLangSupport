package org.sleepy.language.compilerbased

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.psi.tree.IElementType
import org.sleepy.language.SleepyLanguage


object TokenMapping {
    private val TypeNameToHighlighting = mapOf(
        "BAD_CHARACTER" to arrayOf(HighlighterColors.BAD_CHARACTER),
        "KEYWORD" to arrayOf(DefaultLanguageHighlighterColors.KEYWORD),
        "IDENTIFIER" to arrayOf(DefaultLanguageHighlighterColors.IDENTIFIER),
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
    )

    val defaultElementType = IElementType("OTHER", SleepyLanguage.INSTANCE)
    val TypeNameToElementType =
        TypeNameToHighlighting.keys.associateWith { s -> IElementType(s, SleepyLanguage.INSTANCE) }
    val ElementTypeToHighlighting = TypeNameToHighlighting.map { (name, color) ->
        Pair(TypeNameToElementType[name]!!, color)
    }.toMap()
}