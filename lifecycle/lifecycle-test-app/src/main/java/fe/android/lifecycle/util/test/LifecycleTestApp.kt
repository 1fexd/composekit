package fe.android.lifecycle.util.test

import android.app.Application
import fe.android.lifecycle.CurrentActivityObserver
import fe.android.lifecycle.ProcessServiceRegistry

class LifecycleTestApp : Application() {
    private val lifecycleObserver by lazy { ProcessServiceRegistry() }
    private val currentActivityObserver by lazy { CurrentActivityObserver() }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(currentActivityObserver)
    }
}
