import com.android.build.gradle.LibraryExtension
import fe.buildlogic.ProjectInfo
import fe.buildlogic.Version
import fe.buildlogic.extension.asProvider
import fe.buildlogic.extension.getReleaseVersion
import fe.buildlogic.fixGroup
import fe.buildlogic.publishing.PublicationComponent
import fe.buildlogic.publishing.PublicationName
import fe.buildlogic.publishing.publish
import fe.buildlogic.version.AndroidVersionStrategy
import net.nemerosa.versioning.VersioningExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

plugins {
    id("org.jetbrains.kotlin.android") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.plugin.compose") apply false
    id("net.nemerosa.versioning") apply false
    id("com.gitlab.grrfe.build-logic-plugin")
    `maven-publish`
}

val projectInfo = ProjectInfo("com.github.1fexd", "fe","composekit")

subprojects {
    val isPlatform = name == "platform"
    val isTestApp = name.endsWith("test-app")

    if (!isTestApp && !isPlatform) {
        apply(plugin = "com.android.library")
        apply(plugin = "org.jetbrains.kotlin.android")
        apply(plugin = "org.jetbrains.kotlin.plugin.compose")
    }

    apply(plugin = "net.nemerosa.versioning")
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "com.gitlab.grrfe.build-logic-plugin")

    val now = System.currentTimeMillis()
    val provider = AndroidVersionStrategy(now)

    val versionProvider = with(extensions["versioning"] as VersioningExtension) {
        asProvider(this@subprojects, provider)
    }

    group = this@subprojects.fixGroup("${projectInfo.prefix}.${projectInfo.projectName}")
    version = versionProvider.getReleaseVersion()

    if (!isPlatform && !isTestApp) {
        with(extensions["kotlin"] as KotlinAndroidProjectExtension) {
            jvmToolchain(Version.JVM)
        }

        with(extensions["android"] as LibraryExtension) {
            namespace = this@subprojects.fixGroup("fe.${projectInfo.projectName}")
            compileSdk = Version.COMPILE_SDK

            defaultConfig {
                minSdk = Version.MIN_SDK
            }

            publishing {
                multipleVariants {
                    singleVariant(PublicationName.Release)
                    withSourcesJar()
                }
            }
        }

        this@subprojects.dependencies {
            add("implementation", platform(AndroidX.compose.bom))
            add("implementation", AndroidX.compose.ui)
            add("implementation", AndroidX.compose.material3)
        }
    }

    if (!isTestApp) {
        publishing.publish(
            this@subprojects,
            group.toString(),
            versionProvider,
            if (isPlatform) PublicationComponent.JavaPlatform else PublicationComponent.Android,
            PublicationName.Release
        )
    }
}
