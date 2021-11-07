package org.sleepy.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.sleepy.language.SleepyFileType
import org.sleepy.language.SleepyLanguage

class SleepyPsiFile(provider: FileViewProvider) : PsiFileBase(provider, SleepyLanguage) {
    override fun getFileType(): FileType = SleepyFileType
}