plugins {

}

dependencies {
    api(project(":koin"))
    api(project(":lifecycle-core"))
    implementation(Koin.android)
}
