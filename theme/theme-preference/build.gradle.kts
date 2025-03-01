import fe.build.dependencies._1fexd

plugins {

}

dependencies {
    implementation(project(":core"))
    implementation(project(":theme:theme-core"))
    implementation(_1fexd.droidKit.preference.core)
    implementation(_1fexd.droidKit.preference.compose.core)
}
