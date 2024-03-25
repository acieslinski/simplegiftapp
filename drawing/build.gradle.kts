plugins {
    id("gift-module")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.drawing.data)
                implementation(projects.drawing.domain)
                api(projects.drawing.ui)
            }
        }
    }
}