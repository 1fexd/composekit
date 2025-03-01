import fe.build.dependencies._1fexd

plugins {

}

dependencies {
    implementation(project(":core"))
    implementation(project(":layout"))

    implementation(AndroidX.compose.ui.text)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material.icons.core)

    implementation(_1fexd.droidKit.span.compose)
}
