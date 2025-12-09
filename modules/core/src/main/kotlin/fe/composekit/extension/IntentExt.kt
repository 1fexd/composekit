package fe.composekit.extension

import android.content.Intent
import android.os.IBinder
import android.os.IInterface
import androidx.core.content.IntentCompat

public inline fun <reified T> Intent.getParcelableExtraCompat(name: String): T? {
    return IntentCompat.getParcelableExtra(this, name, T::class.java)
}

public fun <T : IInterface> Intent.getBundleBinder(name: String, block: (IBinder) -> T): T? {
    return getBundleExtra(name)?.getBinder(name)?.let(block)
}
