plugins {
    id("gift-module")
    alias(libs.plugins.sqlDelight)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.sql.coroutines.extensions)
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.sql.android.driver)
            }
        }

        val iosMain by getting {
            dependencies {
                api(libs.sql.native.driver)
            }
        }
    }
}

sqldelight {
    databases {
        create(name = "SimpleGiftAppDatabase") {
            packageName.set("com.amc.acieslinski.simplegiftapp.db")
        }
    }
}