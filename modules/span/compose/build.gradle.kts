plugins {
    kotlin("plugin.compose")
}

dependencies{
    api(project(":span-core"))
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.graphics)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material3)
}
