package org.sleepy.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import org.sleepy.settings.AppSettingsState

/**
 * Supports storing the application settings in a persistent way.
 * The [State] and [Storage] annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(name = "org.intellij.sdk.settings.AppSettingsState", storages = [Storage("SdkSettingsPlugin.xml")])
class AppSettingsState : PersistentStateComponent<AppSettingsState?> {
    var sleepyPath = ""
    var pythonIncludePath: String? = null
    override fun getState(): AppSettingsState = this

    override fun loadState(state: AppSettingsState) = XmlSerializerUtil.copyBean(state, this)

    companion object {
        val instance: AppSettingsState = ApplicationManager.getApplication().getService(AppSettingsState::class.java)
    }
}