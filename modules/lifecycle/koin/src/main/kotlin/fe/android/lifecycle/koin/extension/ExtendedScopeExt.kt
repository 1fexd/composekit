package fe.android.lifecycle.koin.extension

import fe.android.lifecycle.LifecycleServiceRegistry
import fe.droidkit.koin.ExtendedScope

public val <T : Any> ExtendedScope<T>.applicationLifecycle
    get() = scope.get<LifecycleServiceRegistry>()
