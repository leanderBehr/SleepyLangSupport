package org.sleepy.language

import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.util.elementType

class SleepyAstType private constructor(val name: String) : IElementType(name, SleepyLanguage) {
    companion object {
        val File = IFileElementType(SleepyLanguage)
        private val NameToTypeMap = mutableMapOf<String, SleepyAstType>()
        fun nameToType(name: String) = NameToTypeMap.getOrPut(name) { SleepyAstType(name) }
    }
}

fun isSleepyAstType(type: IElementType?, name: String) = type is SleepyAstType && type.name == name

fun isFuncDecl(psi: PsiElement): Boolean =
    psi.elementType is SleepyAstType
            && (psi.elementType as SleepyAstType).name == "Stmt"
            && psi.node.findChildByType(SleepyTokenType.nameToType("func")) != null

fun isFuncDeclIdentifier(psi: PsiElement): Boolean =
    psi.elementType != null
            && isSleepyTokenType(psi.elementType!!, "identifier")
            && isFuncDecl(psi.parent)

fun isStructMember(psi: PsiElement): Boolean =
    psi.elementType != null
            && isSleepyTokenType(psi.elementType!!, "identifier")
            && isSleepyAstType(psi.parent.elementType, "MemberList+")