package org.sleepy.language

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType

class SleepyAstType private constructor(name: String) : IElementType(name, SleepyLanguage) {
    companion object {
        val File = IFileElementType(SleepyLanguage)
        private val NameToTypeMap = mutableMapOf<String, SleepyAstType>()
        fun nameToType(name: String) = NameToTypeMap.getOrPut(name) { SleepyAstType(name) }
    }
}