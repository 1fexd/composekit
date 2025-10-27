plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    api(project(":preference-compose-core2"))
    implementation(AndroidX.compose.runtime)
    implementation(KotlinX.coroutines.android)
}
