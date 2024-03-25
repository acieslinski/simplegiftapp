package com.amc.acieslinski.simplegiftapp.convention

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
            versionCatalog.findVersion("compose-compiler").get().requiredVersion
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    project.tasks.withType(KotlinJvmCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}