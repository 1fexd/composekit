package fe.composekit.extension

import android.content.Context

public inline fun <reified T : Any> Context.getSystemServiceOrThrow(): T {
    return getSystemService(T::class.java) ?: error("System service '${T::class.simpleName}' is not available")
}
