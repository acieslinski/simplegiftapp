package com.amc.acieslinski.simplegiftapp.convention

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import versionCatalog

internal fun Project.configureKotlinAndroid(
    extension: LibraryExtension
) = extension.apply {
    namespace = Config.basePackageName
    compileSdk = Config.androidCompileSdk
    defaultConfig {
        minSdk = Config.androidMinSdk
    }
    packaging {
        resources {
            excludes += "/META-INF/**"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

internal fun Project.configureKotlinAndroidApp(
    extension: BaseAppModuleExtension
) = extension.apply {
    namespace = Config.basePackageName + ".android"
    compileSdk = Config.androidCompileSdk
    defaultConfig {
        applicationId = Config.basePackageName + ".android"
        minSdk = Config.androidMinSdk
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/**"
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            versionCatalog.findPlugin("compose-compiler").get().get().version.requiredVersion
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    project.tasks.withType(KotlinJvmCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}