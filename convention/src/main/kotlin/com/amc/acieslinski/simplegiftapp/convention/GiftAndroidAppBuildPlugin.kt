package com.amc.acieslinski.simplegiftapp.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import versionCatalog

class GiftAndroidAppBuildPlugin: Plugin<Project> {

    override fun apply(target: Project):Unit = with(target){
        with(pluginManager){
            apply(versionCatalog.findPlugin("androidApplication").get().get().pluginId)
            apply(versionCatalog.findPlugin("kotlinAndroid").get().get().pluginId)
        }

        extensions.configure(BaseAppModuleExtension::class.java, ::configureKotlinAndroidApp)
    }
}