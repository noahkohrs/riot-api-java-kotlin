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
