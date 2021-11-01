plugins {
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.intellij") version "1.1.4"
}

group = "org.sleepy"
version = "0.6"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    implementation(fileTree("lib"))
    implementation("net.lingala.zip4j:zip4j:2.9.0")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2021.2")
    type.set("CL")
    plugins.set(listOf("com.intellij.clion"))
}
tasks {
    patchPluginXml {
        changeNotes.set(
            """
            Add support for debugging with native debuggers in CLion.
            """.trimIndent()
        )
    }
}