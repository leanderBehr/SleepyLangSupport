package org.sleepy.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object SleepyFileType : LanguageFileType(SleepyLanguage) {
    override fun getName(): String = "Sleepy File"

    override fun getDescription(): String = "Sleepy language file"

    override fun getDefaultExtension(): String = "slp"

    override fun getIcon(): Icon = SleepyIcons.FILE
}