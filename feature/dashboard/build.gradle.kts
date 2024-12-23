plugins {
    id("gift-module")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.feature.dashboard.data)
                implementation(projects.feature.dashboard.domain)
                api(projects.feature.dashboard.ui)
            }
        }
    }
}