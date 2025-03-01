plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    api(project(":preference-core"))
    api(project(":preference-compose-core"))
    implementation(AndroidX.compose.runtime)
}
