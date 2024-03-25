import com.amc.acieslinski.simplegiftapp.convention.iosTargets

plugins {
    id("gift-module")
    alias(libs.plugins.skie)
}

kotlin {
    iosTargets().forEach {
        it.binaries.framework {
            baseName = "shared"
            linkerOpts.add("-lsqlite3")
            export(projects.core.logger)
            export(projects.account)
            export(projects.account.ui)
            export(projects.drawing)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.logger)
                api(projects.account)
                api(projects.drawing)
            }
        }
    }
}