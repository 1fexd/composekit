package fe.android.lifecycle

import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import fe.android.lifecycle.AppLifecycleObserver.Companion.TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

public typealias ExceptionHandler = (CoroutineContext, Throwable) -> Unit

public val DefaultHandler: ExceptionHandler = { context, throwable ->
    Log.e(TAG, "Exception ${throwable.message}", throwable)
}

public fun ProcessServiceRegistry(exceptionHandler: ExceptionHandler = DefaultHandler): AppLifecycleObserver {
    val owner = ProcessLifecycleOwner.get()
    val observer = AppLifecycleObserver(
        owner = owner,
        exceptionHandler = CoroutineExceptionHandler(exceptionHandler)
    )

    owner.lifecycle.addObserver(observer)
    return observer
}
