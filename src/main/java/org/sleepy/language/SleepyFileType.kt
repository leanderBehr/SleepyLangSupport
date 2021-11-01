package org.sleepy.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class SleepyFileType private constructor() : LanguageFileType(SleepyLanguage.INSTANCE) {
    override fun getName(): String = "Sleepy File"

    override fun getDescription(): String = "Sleepy language file"

    override fun getDefaultExtension(): String = "slp"

    override fun getIcon(): Icon = SleepyIcons.FILE

    companion object {
        val INSTANCE = SleepyFileType()
    }
}