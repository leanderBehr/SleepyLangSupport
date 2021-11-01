package org.sleepy.settings

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val sleepyPathField = TextFieldWithBrowseButton()
    private val pythonIncludePathField = TextFieldWithBrowseButton()
    private val specificPythonCheck = JBCheckBox("Use additional python include path", false)

    val preferredFocusedComponent: JComponent = sleepyPathField

    var sleepyPath: String by sleepyPathField::text
    var pythonIncludePath: String?
        get() = if (pythonIncludePathField.isEnabled) pythonIncludePathField.text else null
        set(value) {
            if (value != null) pythonIncludePathField.text = value
            specificPythonCheck.isSelected = value != null
        }

    init {
        panel = FormBuilder.createFormBuilder().run {
            addLabeledComponent("Specify the sleepy directory: ", sleepyPathField, 1, false)
            addComponent(specificPythonCheck)
            addLabeledComponent("", pythonIncludePathField, 1, false)
            addComponentFillVertically(JPanel(), 0)
            panel
        }

        pythonIncludePathField.isEnabled = false
        specificPythonCheck.addChangeListener {
            pythonIncludePathField.isEnabled = specificPythonCheck.isSelected
        }

        sleepyPathField.addBrowseFolderListener(
            "Sleepy Compiler",
            "Select the Sleepy compiler folder",
            null,
            FileChooserDescriptorFactory.createSingleFolderDescriptor()
        )
        pythonIncludePathField.addBrowseFolderListener(
            "Python Include Path",
            "Select a folder with sleepy's dependencies",
            null,
            FileChooserDescriptorFactory.createSingleFolderDescriptor()
        )

    }
}