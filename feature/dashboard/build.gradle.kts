plugins {
    id("gift-module")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {

        val commonMain by getting {
            dependencies {
//                implementation(projects.registration.data)
//                implementation(projects.registration.domain)
                api(projects.feature.dashboard.ui)
            }
        }
    }
}