import co.touchlab.skie.configuration.SuspendInterop
import com.amc.acieslinski.simplegiftapp.convention.iosTargets

plugins {
    id("gift-module")
    alias(libs.plugins.skie)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    iosTargets().forEach {
        it.binaries.framework {
            baseName = "shared"
//            isStatic = true
            linkerOpts.add("-lsqlite3")
            export(projects.core.resources)
            export(compose.components.resources)
            export(projects.core.logger)
            export(projects.feature.registration.ui)
            export(projects.feature.registration)
            export(projects.feature.drawingmanagement.ui)
            export(projects.feature.drawingmanagement)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.resources)
                api(projects.core.logger)
                api(projects.feature.registration)
                api(projects.feature.drawingmanagement)
            }
        }
    }
}

skie {
    features {
        group("com.amc.acieslinski.simplegiftapp.resources.Res") {
            SuspendInterop.Enabled(false)
        }
    }
}