plugins {
    id("gift-android-app")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

dependencies {
    implementation(projects.shared)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.barcode.scanning)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation (libs.accompanist.swiperefresh)
    debugImplementation(libs.compose.ui.tooling)
}

tasks.named<Delete>("clean") {
    doLast {
        val buildDirectory = rootProject.file("build")
        if (buildDirectory.exists()) {
            buildDirectory.deleteRecursively()
            println("Deleted directory: ${buildDirectory.absolutePath}")
        }
    }
}