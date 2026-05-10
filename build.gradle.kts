import com.gitlab.grrfe.gradlebuild.Plugins
import com.gitlab.grrfe.gradlebuild.PublicationComponent2
import com.gitlab.grrfe.gradlebuild.PublicationName2
import com.gitlab.grrfe.gradlebuild.Version
import com.gitlab.grrfe.gradlebuild.android.AndroidSdk
import com.gitlab.grrfe.gradlebuild.android.accessor.androidLibraryExtension
import com.gitlab.grrfe.gradlebuild.android.extension.singleVariant
import com.gitlab.grrfe.gradlebuild.applyPlugin
import com.gitlab.grrfe.gradlebuild.extension.isPlatform
import com.gitlab.grrfe.gradlebuild.extension.isTestApp
import com.gitlab.grrfe.gradlebuild.util.FileUtil
import fe.build.dependencies.Grrfe
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension


plugins {
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.plugin.compose") apply false
    id("com.gitlab.grrfe.android-build-plugin")
    id("com.gitlab.grrfe.library-build-plugin")
    id("org.jetbrains.kotlinx.binary-compatibility-validator")
}

val baseGroup = "com.github.1fexd.composekit"
val externalDir = rootDir.resolve("external")

fun Project.isExternal(): Boolean {
    return FileUtil.isChildOf(projectDir, externalDir, false)
}

fun Project.toNamespace() = buildString {
    append(baseGroup.replace("1fexd", "qfexd"))
    append(".")
    append(name.replace("-", ""))
}

subprojects {
    val subProject = this

    logger.quiet("Init for $this, isTestApp=$isTestApp, isPlatform=$isPlatform")

    if (!isTestApp && !isPlatform) {
        applyPlugin(Plugins.AndroidLibrary, Plugins.KotlinPluginCompose)
    }

    applyPlugin(
        Plugins.MavenPublish,
        Plugins.GrrfeAndroidBuild,
        Plugins.GrrfeLibraryBuild,
    )

    group = baseGroup
    library {
        if (!isTestApp) {
            publication {
                name.set(PublicationName2.Release)
                component.set(if (isPlatform) PublicationComponent2.JavaPlatform else PublicationComponent2.Android)
            }
        }
    }

    if (!isPlatform && !isTestApp) {
        kotlinExtension.apply {
            jvmToolchain(Version.JVM)
            if (!isExternal()) {
                explicitApiWarning()
            }
        }

        androidLibraryExtension.apply {
            namespace = subProject.toNamespace()
            compileSdk = AndroidSdk.COMPILE_SDK

            defaultConfig {
                minSdk = AndroidSdk.MIN_SDK
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testOptions.unitTests.isIncludeAndroidResources = true
            }

            lint {
                disable.add("EmptyNavDeepLink")
            }

            publishing {
                singleVariant(PublicationName2.Release) {
                    withSourcesJar()
                    // Disable for now https://github.com/Kotlin/dokka/issues/2956#issuecomment-2191606810
//                    withJavadocJar()
                }
            }
        }

        afterEvaluate {
            dependencies {
                configurations.findByName("implementation")?.let { implementation ->
                    implementation(platform(Grrfe.std.bom))
                    implementation(platform("androidx.compose:compose-bom-alpha:_"))
                    implementation(AndroidX.compose.ui)
                    implementation(AndroidX.compose.material3)
                    implementation(AndroidX.test.core)
                }
            }
        }
    }
}
