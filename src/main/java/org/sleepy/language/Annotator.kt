package org.sleepy.language

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class Annotator : Annotator {

    private fun highlight(element: PsiElement, holder: AnnotationHolder, key: TextAttributesKey) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(element).textAttributes(key).create()
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (isFuncDeclIdentifier(element)) highlight(element, holder, DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
        else if(isStructMember(element)) highlight(element, holder, DefaultLanguageHighlighterColors.INSTANCE_FIELD)
        else if(isSleepyAstType(element.node.elementType, "Annotation")) highlight(element, holder, DefaultLanguageHighlighterColors.METADATA)
    }
}