package org.sleepy.language

import com.jetbrains.cidr.execution.debugger.breakpoints.CidrLineBreakpointFileTypesProvider

class LineBreakpointFileTypesProvider : CidrLineBreakpointFileTypesProvider {
    override fun getFileTypes() = setOf(SleepyFileType)
}