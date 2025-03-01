plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    api(project(":preference:preference-core"))
    api(project(":preference:preference-compose:preference-compose-core"))
    implementation(AndroidX.compose.runtime)
}
