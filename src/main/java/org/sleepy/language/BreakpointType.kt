package org.sleepy.language

import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.xdebugger.breakpoints.XLineBreakpointTypeBase
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider

class BreakpointType: XLineBreakpointTypeBase("Sleepy", "Sleepy", DebuggerEditorsProvider()) {
    override fun canPutAt(file: VirtualFile, line: Int, project: Project): Boolean {
        return file.fileType == SleepyFileType.INSTANCE
    }
}

class DebuggerEditorsProvider : XDebuggerEditorsProvider() {
    override fun getFileType(): FileType {
        return SleepyFileType.INSTANCE
    }

}