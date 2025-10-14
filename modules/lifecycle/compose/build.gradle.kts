import fe.build.dependencies.Grrfe

plugins {

}

dependencies {
    implementation(platform(Grrfe.std.bom))
    api(Grrfe.std.coroutines)
    api(AndroidX.lifecycle.common)
}
