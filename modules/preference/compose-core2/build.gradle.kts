plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    api(project(":preference-core"))
    implementation(AndroidX.compose.runtime)
    implementation(KotlinX.coroutines.android)
}
