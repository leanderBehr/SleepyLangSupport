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

    override fun getPreferredFocusedComponent(): JComponent = mySettingsComponent!!.preferredFocusedComponent

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance
        return mySettingsComponent!!.sleepyPath != settings.sleepyPath
                || mySettingsComponent!!.pythonIncludePath != settings.pythonIncludePath
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.sleepyPath = mySettingsComponent!!.sleepyPath
        settings.pythonIncludePath = mySettingsComponent!!.pythonIncludePath
        SleepyCompiler.get { setCompiler(settings.sleepyPath,settings.pythonIncludePath) }
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        mySettingsComponent!!.sleepyPath = settings.sleepyPath
        mySettingsComponent!!.pythonIncludePath = settings.pythonIncludePath
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}