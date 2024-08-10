package fe.composekit.appbase

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import fe.composekit.theme.AppColorScheme
import fe.composekit.theme.Theme
import fe.composekit.theme.ThemeConfig


tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

val LocalActivity = staticCompositionLocalOf<Activity> { error("CompositionLocal LocalActivity not present") }


/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

typealias EdgeToEdgeUpdate = (SystemBarStyle, SystemBarStyle) -> Unit

@Composable
fun AppBaseComponentActivity.AppTheme(
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColorScheme,
    typography: Typography,
    config: ThemeConfig,
    content: @Composable () -> Unit,
) {
    AppTheme(
        edgeToEdge = edgeToEdge,
        systemDarkTheme = systemDarkTheme,
        appColor = appColor,
        typography = typography,
        config = config,
        updateEdgeToEdge = ::updateEdgeToEdge,
        content = content
    )
}

@Composable
fun AppTheme(
    edgeToEdge: Boolean = true,
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColorScheme,
    typography: Typography,
    theme: Theme,
    materialYou: Boolean,
    amoled: Boolean,
    updateEdgeToEdge: EdgeToEdgeUpdate? = null,
    content: @Composable () -> Unit,
) {
    AppTheme(
        edgeToEdge = edgeToEdge,
        systemDarkTheme = systemDarkTheme,
        appColor = appColor,
        typography = typography,
        config = ThemeConfig(theme, materialYou, amoled),
        updateEdgeToEdge = updateEdgeToEdge,
        content = content
    )
}

@Composable
fun AppTheme(
    edgeToEdge: Boolean = true,
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColorScheme,
    typography: Typography,
    config: ThemeConfig,
    updateEdgeToEdge: EdgeToEdgeUpdate? = null,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val (colorScheme, isDarkMode) = config.getColorScheme(context, appColor, systemDarkTheme)

    val activity = context.findActivity()

    if (edgeToEdge && updateEdgeToEdge != null) {
        LaunchedEffect(key1 = config) {
            updateEdgeToEdge(
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT, detectDarkMode = isDarkMode),
                SystemBarStyle.auto(lightScrim, darkScrim, detectDarkMode = isDarkMode)
            )
        }
    }

    CompositionLocalProvider(LocalActivity provides activity!!) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

@Composable
fun AppBaseComponentActivity.BoxAppHost(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    defaultAppColor: AppColorScheme,
    typography: Typography,
    config: ThemeConfig,
    content: @Composable BoxScope.() -> Unit,
) {
    AppTheme(
        appColor = defaultAppColor,
        typography = typography,
        config = config
    ) {
        Box(modifier = modifier, contentAlignment = contentAlignment, content = content)
    }
}
