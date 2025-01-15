plugins {
    id("gift-module")
    alias(libs.plugins.kotlinSerialization)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:database"))
                api(project(":core:network"))
            }
        }

        val androidMain by getting {
            dependencies {
                api(project(":core:database"))
                api(project(":core:network"))
            }
        }

        val iosMain by getting {
            dependencies {
                api(project(":core:database"))
                api(project(":core:network"))
            }
        }
    }
}