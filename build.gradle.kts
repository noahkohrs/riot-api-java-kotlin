plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Feign
    implementation("io.github.openfeign:feign-core:13.3")
    implementation("io.github.openfeign:feign-moshi:13.3")
    // Moshi Kotlin
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)

    explicitApi()
}