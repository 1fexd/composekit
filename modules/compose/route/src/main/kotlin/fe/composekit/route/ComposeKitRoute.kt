package fe.composekit.route

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public object ComposeKitRoute {
    public suspend fun warmup(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Unit = withContext(dispatcher) {
        NavTypes.Types
    }
}
