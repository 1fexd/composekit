plugins {
}

dependencies {
    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.compose.ui.text)
    implementation(AndroidX.compose.material3)
    implementation(libs.org.jetbrains.kotlin.reflect)
    implementation(project(":compose-core"))
    implementation(project(":compose-component"))
    implementation(project(":compose-layout"))
}
