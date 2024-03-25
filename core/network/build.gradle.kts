plugins {
    id("gift-module")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                ktorForMainNetworkModule()
            }
        }

        val androidMain by getting {
            dependencies {
                ktorForAndroidNetworkModule()
            }
        }

        val iosMain by getting {
            dependencies {
                ktorForIosNetworkModule()
            }
        }
    }
}