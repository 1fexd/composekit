import com.android.build.api.dsl.AndroidLibrarySourceSet

plugins {
}

android {
    // Need to cast to com.android.build.gradle.dsl.AndroidLibrarySourceSet, otherwise the following exception is thrown:
//    class com.android.build.gradle.internal.api.DefaultAndroidLibrarySourceSet_Decorated cannot be cast to class com.android.build.gradle.api.AndroidLibrarySourceSet (com.android.build.gradle.internal.api.DefaultAndroidLibrarySourceSet_Decorated and com.android.build.gradle.api.AndroidLibrarySourceSet are in unnamed module of loader org.gradle.internal.classloader.VisitableURLClassLoader$InstrumentingVisitableURLClassLoader @2a7ab924)
    (sourceSets["main"] as AndroidLibrarySourceSet).kotlin.directories.add("src/main/compat")
}

dependencies {
    api(AndroidX.core)
    api(AndroidX.annotation)
}
