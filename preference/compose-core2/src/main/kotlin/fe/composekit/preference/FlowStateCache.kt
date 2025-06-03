package fe.composekit.preference

public class FlowStateCache(
    private val cache: MutableMap<String, ViewModelStatePreference<*, *, *>> = mutableMapOf()
) : AutoCloseable {
//    private val mutex = Mutex()
//
//    public fun refresh() {
////        cache.forEach { (_, state) -> state.forceRefresh() }
//    }

    public fun dispose() {
        close()
    }

    public override fun close() {
        cache.clear()
    }

    internal fun put(key: String, state: ViewModelStatePreference<*, *, *>) {
        cache[key] = state
    }

    public fun get(key: String): ViewModelStatePreference<*, *, *>? {
        val value = cache[key]
        return value
    }

    public fun getAll(): MutableMap<String, ViewModelStatePreference<*, *, *>> {
        return cache
    }
}
