package fe.android.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleCoroutineScope

public interface LifecycleServiceRegistry : DefaultLifecycleObserver {
    public val lifecycleCoroutineScope: LifecycleCoroutineScope

    public fun onAppInitialized()

    public fun register(service: LifecycleAwareService)

    public fun unregister(service: LifecycleAwareService)
}
