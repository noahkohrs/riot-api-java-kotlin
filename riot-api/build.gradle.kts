import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

apply(plugin = "org.jetbrains.dokka")
plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation("io.github.openfeign:feign-moshi:13.3")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    project(":riot-static")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()

    systemProperties["junit.jupiter.execution.parallel.enabled"] = "true"
    systemProperties["junit.jupiter.execution.parallel.mode.default"] = "concurrent"
    systemProperties["junit.jupiter.execution.parallel.mode.classes.default"] = "concurrent"

    systemProperties["junit.jupiter.execution.parallel.config.strategy"] = "fixed"
    systemProperties["junit.jupiter.execution.parallel.config.fixed.parallelism"] = "16"
}

kotlin {
    jvmToolchain(21)
    explicitApi()
    explicitApi = ExplicitApiMode.Strict
}
