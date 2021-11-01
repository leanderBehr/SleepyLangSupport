package org.sleepy.language

import com.intellij.lang.Language

class SleepyLanguage: Language("Sleepy") {
    companion object { val INSTANCE = SleepyLanguage() }
}