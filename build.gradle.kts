plugins {
    kotlin("jvm") version "2.0.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1" apply false
    id("org.jetbrains.dokka") version "1.9.20" apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

group = "com.noahkohrs"
version = "1.0-SNAPSHOT"