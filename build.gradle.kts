plugins {
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.intellij") version "1.1.4"
}

group = "org.sleepy"
version = "0.5"

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
}
tasks {
    patchPluginXml {
        changeNotes.set(
            """
            Performance improvements.
            """.trimIndent()
        )
    }
}