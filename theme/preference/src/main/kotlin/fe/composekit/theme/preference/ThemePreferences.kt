package fe.composekit.theme.preference

import fe.android.preference.helper.PreferenceDefinition
import fe.composekit.theme.Theme


object ThemePreferences : PreferenceDefinition() {
    val theme = mapped("theme", Theme.System, ThemeMapper)
    val themeMaterialYou = boolean("theme_material_you", true)
    val themeAmoled = boolean("theme_amoled_enabled")

    init {
        finalize()
    }
}
