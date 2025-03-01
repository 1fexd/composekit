plugins {

}

dependencies {
    implementation(project(":compose-core"))
    implementation(project(":compose-layout"))
    implementation(project(":span-compose"))

    implementation(AndroidX.compose.ui.text)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material.icons.core)
}
