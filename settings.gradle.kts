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
include(":feature:drawingmanagement")
include(":feature:drawingmanagement:data")
include(":feature:drawingmanagement:domain")
include(":feature:drawingmanagement:ui")
include(":feature:registration")
include(":feature:registration:data")
include(":feature:registration:domain")
include(":feature:registration:ui")
include(":feature:dashboard")
include(":feature:dashboard:data")
include(":feature:dashboard:domain")
include(":feature:dashboard:ui")
include(":core:logger")
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:configuration")
include(":core:resources")
include(":core:ui")
include(":shared")
include(":core:data")