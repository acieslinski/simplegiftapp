package com.amc.acieslinski.simplegiftapp.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import versionCatalog

class GiftDataBuildPlugin: Plugin<Project> {

    override fun apply(target: Project):Unit = with(target){
        with(pluginManager){
            apply(versionCatalog.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(versionCatalog.findPlugin("androidLibrary").get().get().pluginId)
        }

        extensions.configure(KotlinMultiplatformExtension::class.java, ::configureKotlinMultiplatformData)
        extensions.configure(LibraryExtension::class.java, ::configureKotlinAndroid)
    }
}