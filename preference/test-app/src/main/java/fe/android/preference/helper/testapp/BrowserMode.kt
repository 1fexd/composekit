package fe.android.preference.helper.testapp

import fe.android.preference.helper.OptionTypeMapper

sealed class BrowserMode(val value: String) {
    object None : BrowserMode("none")
    object AlwaysAsk : BrowserMode("always_ask")
    object SelectedBrowser : BrowserMode("browser")
    object Whitelisted : BrowserMode("whitelisted")

    companion object Companion : OptionTypeMapper<BrowserMode, String>(
        { it.value }, { arrayOf(None, AlwaysAsk, SelectedBrowser, Whitelisted) }
    )

    override fun toString(): String {
        return value
    }
}
