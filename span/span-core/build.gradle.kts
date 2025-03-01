import fe.build.dependencies.Grrfe

plugins {

}

dependencies{
    api(AndroidX.core.ktx)

    testImplementation(Grrfe.std.result.assert)
    testImplementation(Koin.test)
    testImplementation(Koin.junit4)
    testImplementation(Koin.android)
    testImplementation(AndroidX.test.ext.junit)
    testImplementation(AndroidX.test.ext.junit.ktx)
    testImplementation(Testing.junit4)
    testImplementation(Testing.robolectric)
    testImplementation("com.willowtreeapps.assertk:assertk:_")
    testImplementation(kotlin("test"))
}
