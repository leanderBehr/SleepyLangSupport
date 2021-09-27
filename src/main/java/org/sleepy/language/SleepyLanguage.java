package org.sleepy.language;

import com.intellij.lang.Language;

public class SleepyLanguage extends Language {

    public static final SleepyLanguage INSTANCE = new SleepyLanguage();

    private SleepyLanguage() {
        super("Sleepy");
    }

}