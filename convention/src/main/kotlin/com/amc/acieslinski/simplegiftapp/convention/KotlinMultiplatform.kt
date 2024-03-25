package com.amc.acieslinski.simplegiftapp.convention

import baseAndroid
import baseIos
import baseMain
import dataAndroid
import dataIos
import dataMain
import koinAndroid
import koinCore
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import test
import uiAndroid
import uiIos
import uiMain

internal fun Project.configureKotlinMultiplatformBase(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    configureKotlinMultiplatformCore(extension)

    sourceSets.apply {
        sourceSets.apply {
            getByName("commonMain") { dependencies { baseMain() } }
            getByName("androidMain") { dependencies { baseAndroid() } }
            getByName("iosMain") { dependencies { baseIos() } }
            getByName("commonTest") { dependencies { test() } }
        }
    }
}

internal fun Project.configureKotlinMultiplatformModule(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    configureKotlinMultiplatformCore(extension)

    sourceSets.apply {
        sourceSets.apply {
            getByName("commonMain") { dependencies {
                baseMain()
                koinCore()
                if (project.name != "configuration") {
                    implementation(project(":core:configuration"))
                }
            } }
            getByName("androidMain") { dependencies {
                koinAndroid()
            } }
            getByName("iosMain") { dependencies { } }
            getByName("commonTest") { dependencies { } }
        }
    }
}

internal fun Project.configureKotlinMultiplatformData(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    configureKotlinMultiplatformCore(extension)

    sourceSets.apply {
        getByName("commonMain") { dependencies { dataMain() } }
        getByName("androidMain") { dependencies { dataAndroid() } }
        getByName("iosMain") { dependencies { dataIos() } }
        getByName("commonTest") { dependencies { test() } }
    }
}

internal fun Project.configureKotlinMultiplatformDomain(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    configureKotlinMultiplatformBase(extension)
}

internal fun Project.configureKotlinMultiplatformUi(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    configureKotlinMultiplatformCore(extension)

    sourceSets.apply {
        getByName("commonMain") { dependencies { uiMain() } }
        getByName("androidMain") { dependencies { uiAndroid() } }
        getByName("iosMain") { dependencies { uiIos() } }
        getByName("commonTest") { dependencies { test() } }
    }
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
private fun Project.configureKotlinMultiplatformCore(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosTargets()

    task("testClasses")
}

fun KotlinMultiplatformExtension.iosTargets() = listOf(
//        iosX64(),
//        iosArm64(),
    iosSimulatorArm64()
)