plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":riot-api"))
    testImplementation(kotlin("test"))

    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
