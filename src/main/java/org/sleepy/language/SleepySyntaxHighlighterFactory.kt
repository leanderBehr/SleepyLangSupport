package org.sleepy.language

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.sleepy.language.compilerbased.CompilerBasedSyntaxHighlighter

class SleepySyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = CompilerBasedSyntaxHighlighter()
}