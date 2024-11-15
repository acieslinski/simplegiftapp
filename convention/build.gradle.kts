import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

group = "com.amc.acieslinski.simplegiftapp"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("giftBaseBuildPlugin"){
            id = "gift-base"
            implementationClass = "com.amc.acieslinski.simplegiftapp.convention.GiftBaseBuildPlugin"
        }

        register("gift-data"){
            id = "gift-data"
            implementationClass = "com.amc.acieslinski.simplegiftapp.convention.GiftDataBuildPlugin"
        }

        register("gift-domain"){
            id = "gift-domain"
            implementationClass = "com.amc.acieslinski.simplegiftapp.convention.GiftDomainBuildPlugin"
        }

        register("gift-ui"){
            id = "gift-ui"
            implementationClass = "com.amc.acieslinski.simplegiftapp.convention.GiftUiBuildPlugin"
        }

        register("gift-module"){
            id = "gift-module"
            implementationClass = "com.amc.acieslinski.simplegiftapp.convention.GiftModuleBuildPlugin"
        }

        register("gift-android-app"){
            id = "gift-android-app"
            implementationClass = "com.amc.acieslinski.simplegiftapp.convention.GiftAndroidAppBuildPlugin"
        }
    }
}