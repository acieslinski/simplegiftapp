plugins {
    id("gift-module")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.account.data)
                implementation(projects.account.domain)
                api(projects.account.ui)
            }
        }
    }
}