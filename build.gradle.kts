plugins {
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.intellij") version "1.1.4"
}

group = "org.sleepy"
version = "0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2021.2")
}
tasks {
    patchPluginXml {
        changeNotes.set(
            """
            Add highlighting for "mutates" and "ref".
            """.trimIndent()
        )
    }
}
sourceSets["main"].java.srcDirs("src/main/gen")