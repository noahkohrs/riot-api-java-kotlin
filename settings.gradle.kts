plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "riot-api-sdk"
include("riot-api")
include("test-apps")
