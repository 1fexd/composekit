plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    api(project(":preference:preference-core"))
    implementation(AndroidX.compose.runtime)
}
