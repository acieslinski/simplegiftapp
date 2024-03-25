plugins {
    id("gift-base")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val androidMain by getting {
            dependencies {
                api(libs.androidx.lifecycle.viewmodel.ktx)
            }
        }
        val iosMain by getting {
            dependencies {
                // TODO
            }
        }
    }
}