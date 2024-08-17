package fe.android.compose.extension

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha


inline fun Modifier.thenIf(condition: Boolean, block: (Modifier) -> Modifier): Modifier {
    return if (condition) let(block) else this
}

fun Modifier.optionalClickable(onClick: (() -> Unit)? = null): Modifier {
    return thenIf(onClick != null) { it.clickable(onClick = onClick!!) }
}

fun Modifier.enabled(enabled: Boolean, alpha: Float = 0.3f): Modifier {
    return thenIf(!enabled) { it.alpha(alpha) }
}
