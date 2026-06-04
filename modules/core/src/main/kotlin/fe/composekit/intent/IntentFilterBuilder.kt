package fe.composekit.intent

import android.content.IntentFilter


public fun buildIntentFilter(block: IntentFilter.() -> Unit): IntentFilter {
    val filter = IntentFilter()
    block(filter)

    return filter
}
