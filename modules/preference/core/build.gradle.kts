
plugins {
    id("de.infix.testBalloon") version "0.8.4-K2.3.20"
}

dependencies {
    implementation(AndroidX.preference)
    implementation(AndroidX.preference.ktx)

    testImplementation("de.infix.testBalloon:testBalloon-framework-core:_")
    testImplementation("de.infix.testBalloon:testBalloon-integration-robolectric:_")
    testImplementation(Testing.junit4)
    testImplementation(Koin.test)
    testImplementation(Koin.junit4)
    testImplementation(Koin.android)
    testImplementation(AndroidX.test.ext.junit)
    testImplementation(AndroidX.test.ext.junit.ktx)
    testImplementation(Testing.junit4)
    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:_")
}
