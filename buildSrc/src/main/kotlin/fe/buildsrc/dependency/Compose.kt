package fe.buildsrc.dependency

import AndroidX
import org.gradle.kotlin.dsl.IsNotADependency


object PinnedVersions : IsNotADependency {
    var ComposeUi = AndroidX.compose.ui
    var ComposeFoundation = AndroidX.compose.foundation

    var Material3 = AndroidX.compose.material3.withVersion("1.4.0-alpha04")
}
