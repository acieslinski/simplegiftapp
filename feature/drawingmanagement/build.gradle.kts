plugins {
    id("gift-module")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.feature.drawingmanagement.data)
                implementation(projects.feature.drawingmanagement.domain)
                api(projects.feature.drawingmanagement.ui)
            }
        }
    }
}