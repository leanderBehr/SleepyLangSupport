package org.sleepy.language

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document

class SleepyFoldingBuilder : FoldingBuilder {
    override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        collectDescriptors(node, descriptors)
        return descriptors.toTypedArray()
    }

    private fun collectDescriptors(node: ASTNode, descriptors: MutableList<FoldingDescriptor>) {
        node.getChildren(null).forEach { collectDescriptors(it, descriptors) }

        val type = node.elementType as? SleepyAstType ?: return
        if(type.name == "Scope" || type.name == "StructBody") descriptors.add(FoldingDescriptor(node, node.textRange))
    }

    override fun getPlaceholderText(node: ASTNode): String = "{...}"

    override fun isCollapsedByDefault(node: ASTNode) = false
}