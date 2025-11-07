plugins {

}

dependencies {
    api(project(":compose-m3compat"))
    api(project(":compose-core"))
    api(project(":compose-layout"))
    api(project(":span-compose"))

    api("com.github.nanihadesuka:LazyColumnScrollbar:_")
    implementation(AndroidX.compose.ui.text)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material.icons.core)
}
