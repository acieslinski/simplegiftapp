package com.amc.acieslinski.simplegiftapp.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import versionCatalog

class GiftModuleBuildPlugin: Plugin<Project> {

    override fun apply(target: Project):Unit = with(target){
        with(pluginManager){
            apply(versionCatalog.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(versionCatalog.findPlugin("androidLibrary").get().get().pluginId)
//            apply(versionCatalog.findPlugin("jetbrainsCompose").get().get().pluginId)
//            apply(versionCatalog.findPlugin("compose-compiler").get().get().pluginId)
//            alias(libs.plugins.jetbrainsCompose)
//            alias(libs.plugins.compose.compiler)
        }

        extensions.configure(KotlinMultiplatformExtension::class.java, ::configureKotlinMultiplatformModule)
        extensions.configure(LibraryExtension::class.java, ::configureKotlinAndroid)
    }
}