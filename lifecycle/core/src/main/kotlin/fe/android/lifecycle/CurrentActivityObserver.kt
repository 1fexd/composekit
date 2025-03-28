package fe.android.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

public class CurrentActivityObserver : Application.ActivityLifecycleCallbacks {
    private val _current = MutableStateFlow<Activity?>(null)
    public val current: StateFlow<Activity?> = _current.asStateFlow()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        _current.update { activity }
    }

    override fun onActivityPaused(activity: Activity) {
        _current.compareAndSet(activity, null)
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}
