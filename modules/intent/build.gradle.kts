import fe.build.dependencies.Grrfe

plugins {
}

dependencies {
    implementation(project(":ext-mozilla-support-utils"))
    implementation(Grrfe.std.result.core)
}
