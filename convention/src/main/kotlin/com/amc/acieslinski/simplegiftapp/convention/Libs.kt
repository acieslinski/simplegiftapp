import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

val Project.versionCatalog
    get(): VersionCatalog = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

val KotlinDependencyHandler.versionCatalog
    get(): VersionCatalog = this.project.versionCatalog

fun KotlinDependencyHandler.koinCore() {
    implementation(versionCatalog.findLibrary("koin-core").get())
}

fun KotlinDependencyHandler.koinAndroid() {
    implementation(versionCatalog.findLibrary("koin-android").get())
    implementation(versionCatalog.findLibrary("koin-androidx-compose").get())
}

fun KotlinDependencyHandler.ktorForMainNetworkModule() {
    api(versionCatalog.findLibrary("ktor-client-core").get())
    api(versionCatalog.findLibrary("ktor-client-content-negotiation").get())
    implementation(versionCatalog.findLibrary("ktor-client-content-logging").get())
    api(versionCatalog.findLibrary("ktor-serialization-kotlinx-json").get())
}

fun KotlinDependencyHandler.ktorForAndroidNetworkModule() {
    api(versionCatalog.findLibrary("ktor-client-android").get())
}

fun KotlinDependencyHandler.ktorForIosNetworkModule() {
    api(versionCatalog.findLibrary("ktor-client-darwin").get())
}

fun KotlinDependencyHandler.baseMain() {
    if (project.name != "logger") {
        implementation(project(":core:logger"))
    }
    implementation(versionCatalog.findLibrary("kotlinx-datetime").get())
    implementation(versionCatalog.findLibrary("kotlinx-coroutines-core").get())
}

fun KotlinDependencyHandler.baseAndroid() {
    // placeholder
}

fun KotlinDependencyHandler.baseIos() {
    // placeholder
}

fun KotlinDependencyHandler.dataMain() {
    baseMain()
    api(project(":core:database"))
    api(project(":core:network"))
}

fun KotlinDependencyHandler.dataAndroid() {
    baseAndroid()
    api(project(":core:database"))
    api(project(":core:network"))
}

fun KotlinDependencyHandler.dataIos() {
    baseIos()
    api(project(":core:database"))
    api(project(":core:network"))
    implementation(versionCatalog.findLibrary("sql-native-driver").get())
}

fun KotlinDependencyHandler.uiMain() {
    baseMain()
    api(project(":core:ui"))
}

fun KotlinDependencyHandler.uiAndroid() {
    baseAndroid()
    api(project(":core:ui"))
}

fun KotlinDependencyHandler.uiIos() {
    baseIos()
    api(project(":core:ui"))
}

fun KotlinDependencyHandler.test() {
    implementation(versionCatalog.findLibrary("kotlin-test").get())
}