plugins {

}

dependencies {
    implementation(project(":compose:core"))
    implementation(project(":preference:preference-core"))
    implementation(project(":preference:preference-compose:preference-compose-core"))

    implementation(AndroidX.compose.ui.text)
}
