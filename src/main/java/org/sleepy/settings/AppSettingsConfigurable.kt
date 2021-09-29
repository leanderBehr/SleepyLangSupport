package org.sleepy.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import org.sleepy.language.SleepyCompiler
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "Sleepy Path"
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent? {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.getInstance()
        return mySettingsComponent!!.sleepyPath != settings.SleepyPath.toString()
    }

    override fun apply() {
        val settings = AppSettingsState.getInstance()
        settings.SleepyPath = mySettingsComponent!!.sleepyPath
        SleepyCompiler.get { setCompiler(settings.SleepyPath) }
    }

    override fun reset() {
        val settings = AppSettingsState.getInstance()
        mySettingsComponent!!.sleepyPath = settings.SleepyPath.toString()
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}