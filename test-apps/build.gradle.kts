plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":riot-api"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}