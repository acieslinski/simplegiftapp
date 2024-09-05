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
            export(projects.account)
            export(projects.account.ui)
            export(projects.drawing)
            export(projects.drawing.ui)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.resources)
                api(projects.core.logger)
                api(projects.account)
                api(projects.drawing)
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