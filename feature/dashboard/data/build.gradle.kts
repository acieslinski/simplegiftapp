plugins {
    id("gift-data")
    alias(libs.plugins.kotlinSerialization)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.qrcode.kotlin)
                implementation(projects.feature.dashboard.domain)
            }
        }
    }
}