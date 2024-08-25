import com.amc.acieslinski.simplegiftapp.convention.Config

plugins {
    id("gift-module")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.components.resources)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "${Config.basePackageName}.resources"
    generateResClass = always
}