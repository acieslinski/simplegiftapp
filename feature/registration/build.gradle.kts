plugins {
    id("gift-module")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.feature.registration.data)
                implementation(projects.feature.registration.domain)
                api(projects.feature.registration.ui)
            }
        }
    }
}