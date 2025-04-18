import com.gitlab.grrfe.gradlebuild.android.extension.singleVariant
import com.gitlab.grrfe.gradlebuild.common.extension.isPlatform
import com.gitlab.grrfe.gradlebuild.common.extension.isTestApp
import com.gitlab.grrfe.gradlebuild.library.publishing.PublicationComponent2
import com.gitlab.grrfe.gradlebuild.library.publishing.PublicationName2
import fe.build.dependencies.Grrfe
import fe.buildlogic.Plugins
import fe.buildlogic.Version
import fe.buildlogic.accessor.androidLibraryProxy
import fe.buildlogic.accessor.kotlinAndroidProxy
import fe.buildlogic.applyPlugin
import fe.buildlogic.version.AndroidVersionStrategy

plugins {
    id("org.jetbrains.kotlin.android") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.plugin.compose") apply false
    id("net.nemerosa.versioning") apply false
    id("com.gitlab.grrfe.new-build-logic-plugin")
    id("com.gitlab.grrfe.library-build-plugin")
}

val baseGroup = "com.github.1fexd.composekit"

subprojects {
    logger.quiet("Init for $this, isTestApp=$isTestApp, isPlatform=$isPlatform")

    if (!isTestApp && !isPlatform) {
        applyPlugin(Plugins.AndroidLibrary, Plugins.KotlinAndroid, Plugins.KotlinPluginCompose)
    }

    applyPlugin(
        Plugins.MavenPublish,
        Plugins.GrrfeNewBuildLogic,
        Plugins.GrrfeLibraryBuild,
        Plugins.NemerosaVersioning
    )

    group = baseGroup
    library {
        val now = System.currentTimeMillis()
        versionStrategy.set(AndroidVersionStrategy(now))

        if (!isTestApp) {
            publication {
                name.set(PublicationName2.Release)
                component.set(if (isPlatform) PublicationComponent2.JavaPlatform else PublicationComponent2.Android)
            }
        }
    }

    if (!isPlatform && !isTestApp) {
        kotlinAndroidProxy().run {
            jvmToolchain(Version.JVM)
            explicitApiWarning()
        }

        androidLibraryProxy().run {
            namespace = baseGroup.replace("1fexd", "fexd")
            compileSdk = 35

            defaultConfig {
                minSdk = 25
            }

            lint {
                disable.add("EmptyNavDeepLink")
            }

            publishing {
                singleVariant(PublicationName2.Release) {
                    withSourcesJar()
                    withJavadocJar()
                }
            }
        }

        this@subprojects.dependencies {
            add("implementation", platform(Grrfe.std.bom))
            add("implementation", platform(AndroidX.compose.bom))
            add("implementation", AndroidX.compose.ui.withVersion("1.8.0-rc03"))
            add("implementation", AndroidX.compose.material3.withVersion("1.4.0-alpha12"))
        }
    }
}
