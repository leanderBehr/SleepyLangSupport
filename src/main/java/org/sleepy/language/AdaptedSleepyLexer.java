package org.sleepy.language;

import com.intellij.lexer.FlexAdapter;

public class AdaptedSleepyLexer extends FlexAdapter {
    public AdaptedSleepyLexer() {
        super(new SleepyLexer(null));
    }
}
