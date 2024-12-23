plugins {
    id("gift-data")
    alias(libs.plugins.kotlinSerialization)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.data)
                implementation(projects.feature.drawingmanagement.domain)
            }
        }
    }
}