package fe.android.lifecycle

import androidx.lifecycle.LifecycleOwner

public interface LifecycleAwareService {
    public suspend fun onAppInitialized(owner: LifecycleOwner) {}

    public suspend fun onCreate() {}

    public suspend fun onResume() {}

    public suspend fun onPause() {}

    public suspend fun onStop() {}
}
