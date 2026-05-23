package fe.composekit.route

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public object ComposeKitRoute {
    public suspend fun warmup(): Unit = withContext(Dispatchers.IO){
        NavTypes.Types
    }
}
