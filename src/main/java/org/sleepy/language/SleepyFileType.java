package org.sleepy.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SleepyFileType extends LanguageFileType {

    public static final SleepyFileType INSTANCE = new SleepyFileType();

    private SleepyFileType() {
        super(SleepyLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Sleepy File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Sleepy language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "slp";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SleepyIcons.FILE;
    }

}