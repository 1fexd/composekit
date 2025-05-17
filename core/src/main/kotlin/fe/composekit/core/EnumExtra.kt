package fe.composekit.core

import android.content.Intent
import kotlin.enums.enumEntries


public inline fun <reified E : Enum<E>> Intent.getEnumExtra(key: String): E? {
    return getIntExtra(key, -1)
        .takeIf { it != -1 }
        ?.let { enumEntries<E>().getOrNull(it) }
}

public fun <E : Enum<E>> Intent.putEnumExtra(key: String, value: E) {
    putExtra(key, value.ordinal)
}
