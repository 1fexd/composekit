package fe.composekit.bundle

import android.os.Bundle

public inline fun buildBundle(block: Bundle.() -> Unit): Bundle {
    val bundle = Bundle()
    bundle.apply(block)
    return bundle
}
