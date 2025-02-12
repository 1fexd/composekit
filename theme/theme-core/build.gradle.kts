import fe.build.dependencies.PinnedVersions
import fe.build.dependencies._1fexd

plugins {

}

dependencies {
    implementation(project(":core"))
    implementation(_1fexd.android.preference.core)
    implementation(_1fexd.android.preference.compose)

    implementation(AndroidX.compose.ui.text)
}
