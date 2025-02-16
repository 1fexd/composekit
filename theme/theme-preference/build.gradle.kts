import fe.build.dependencies._1fexd

plugins {

}

dependencies {
    implementation(project(":core"))
    implementation(project(":theme:theme-core"))

//    implementation(platform(_1fexd.android.preference.bom))
    implementation(_1fexd.android.preference.core)
    implementation(_1fexd.android.preference.compose)
}
