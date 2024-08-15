import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    kotlin("jvm") version "2.0.0"
    // KtLinter plugin
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}
group = "com.noahkohrs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Feign
    implementation("io.github.openfeign:feign-core:13.3")
    implementation("io.github.openfeign:feign-moshi:13.3")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
    explicitApi()
    explicitApi = ExplicitApiMode.Strict
}