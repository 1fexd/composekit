package fe.composekit.theme

import android.content.Context
import android.content.res.Resources
import androidx.compose.material3.ColorScheme

data class ThemeConfig(
    val theme: Theme,
    val materialYou: Boolean,
    val amoled: Boolean,
) {
    fun getColorScheme(
        context: Context,
        defaultAppColor: AppColorScheme,
        systemDarkTheme: Boolean,
    ): Pair<ColorScheme, (Resources) -> Boolean> {
        val isDarkMode: (Resources) -> Boolean = { _ -> theme == Theme.Dark || systemDarkTheme }
        val colorScheme = theme.getColorScheme(context, defaultAppColor, systemDarkTheme, materialYou, amoled)

        return colorScheme to isDarkMode
    }
}
