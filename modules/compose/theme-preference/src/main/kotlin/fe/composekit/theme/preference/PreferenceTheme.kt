package fe.composekit.theme.preference

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import fe.android.preference.helper.Preference
import fe.android.preference.helper.compose.StatePreferenceRepository
import fe.composekit.theme.Theme
import fe.composekit.theme.ThemeConfig


@Composable
fun PreferenceTheme(
    preferences: StatePreferenceRepository,
    themePref: Preference.Mapped<Theme, String> = ThemePreferences.theme,
    materialYouPref: Preference.Boolean = ThemePreferences.themeMaterialYou,
    amoledPref: Preference.Boolean = ThemePreferences.themeAmoled,
    content: @Composable (ThemeConfig) -> Unit,
) {
    val theme by remember { preferences.asState(themePref) }
    val themeMaterialYou by remember { preferences.asState(materialYouPref) }
    val themeAmoled by remember { preferences.asState(amoledPref) }

    content(ThemeConfig(theme, themeMaterialYou, themeAmoled))
}
