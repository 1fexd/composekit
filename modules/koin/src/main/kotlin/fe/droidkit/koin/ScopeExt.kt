package fe.droidkit.koin

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.core.content.ContextCompat
import org.koin.core.scope.Scope

public inline fun <reified T : Any> Scope.getSystemServiceOrNull(): T? {
    return ContextCompat.getSystemService(get<Context>(), T::class.java)
}

public inline fun <reified T : Any> Scope.getSystemServiceOrThrow(): T {
    return getSystemServiceOrNull()!!
}

public fun Scope.getPackageManager(): PackageManager {
    return get<Context>().packageManager
}

public fun Scope.getResources(): Resources {
    return get<Context>().resources
}
