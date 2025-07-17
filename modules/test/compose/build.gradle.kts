plugins {

}

dependencies{
    implementation(project(":test-core"))
    implementation(AndroidX.compose.ui.testJunit4)

    implementation(Koin.test)
    implementation(Koin.junit4)
    implementation(Koin.android)
    implementation(Testing.junit4)
}
