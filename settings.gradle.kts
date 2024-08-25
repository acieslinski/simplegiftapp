enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "simplegiftapp"
includeBuild("convention")
include(":androidApp")
include(":drawing")
include(":drawing:data")
include(":drawing:domain")
include(":drawing:ui")
include(":account")
include(":account:data")
include(":account:domain")
include(":account:ui")
include(":core:logger")
include(":core:network")
include(":core:database")
include(":core:configuration")
include(":core:resources")
include(":core:ui")
include(":shared")