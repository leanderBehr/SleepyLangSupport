package org.sleepy.language

import com.intellij.psi.tree.IElementType
import org.sleepy.language.SleepyLanguage

object SleepyTokenType {
    @JvmField var KEYWORD = IElementType("KEYWORD", SleepyLanguage.INSTANCE)
    @JvmField var IDENTIFIER = IElementType("IDENTIFIER", SleepyLanguage.INSTANCE)
    @JvmField var OPERATOR = IElementType("OPERATOR", SleepyLanguage.INSTANCE)
    @JvmField var COMMENT = IElementType("COMMENT", SleepyLanguage.INSTANCE)
    @JvmField var BRACES = IElementType("BRACES", SleepyLanguage.INSTANCE)
    @JvmField var BRACKETS = IElementType("BRACKETS", SleepyLanguage.INSTANCE)
    @JvmField var SEMICOLON = IElementType("SEMICOLON", SleepyLanguage.INSTANCE)
    @JvmField var COMMA = IElementType("COMMA", SleepyLanguage.INSTANCE)
    @JvmField var DOT = IElementType("DOT", SleepyLanguage.INSTANCE)
    @JvmField var PARENTHESES = IElementType("PARENTHESES", SleepyLanguage.INSTANCE)
    @JvmField var STRING = IElementType("STRING", SleepyLanguage.INSTANCE)
    @JvmField var ANNOTATION = IElementType("ANNOTATION", SleepyLanguage.INSTANCE)
    @JvmField var NUMBER = IElementType("NUMBER", SleepyLanguage.INSTANCE)
    @JvmField var OTHER = IElementType("GENERIC", SleepyLanguage.INSTANCE)
}