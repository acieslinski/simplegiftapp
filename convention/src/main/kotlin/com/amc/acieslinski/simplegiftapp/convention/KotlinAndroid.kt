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
    namespace = BuildConfig.basePackageName
    compileSdk = BuildConfig.androidCompileSdk
    defaultConfig {
        minSdk = BuildConfig.androidMinSdk
    }
    packaging {
        resources {
            excludes += "/META-INF/**"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    project.tasks.withType(KotlinJvmCompile::class.java).configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

internal fun Project.configureKotlinAndroidApp(
    extension: BaseAppModuleExtension
) = extension.apply {
    namespace = BuildConfig.basePackageName + ".android"
    compileSdk = BuildConfig.androidCompileSdk
    defaultConfig {
        applicationId = BuildConfig.basePackageName + ".android"
        minSdk = BuildConfig.androidMinSdk
        targetSdk = BuildConfig.androidCompileSdk
        versionCode = BuildConfig.androidVersionCode
        versionName = BuildConfig.androidVersionName
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    project.tasks.withType(KotlinJvmCompile::class.java).configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}