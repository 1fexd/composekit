package fe.composekit.theme.preference

import fe.android.preference.helper.OptionTypeMapper
import fe.composekit.theme.Theme


object ThemeMapper : OptionTypeMapper<Theme, String>({ it.name }, {
    arrayOf(Theme.System, Theme.Light, Theme.Dark)
})
