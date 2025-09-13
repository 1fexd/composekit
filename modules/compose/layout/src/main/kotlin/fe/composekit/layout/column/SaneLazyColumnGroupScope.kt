package fe.composekit.layout.column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape

@DslMarker
public annotation class LazyGroupScopeMarker

@LazyGroupScopeMarker
public interface SaneLazyColumnGroupScope {
    public fun item(key: Any, content: @Composable LazyItemScope.(PaddingValues, Shape) -> Unit)

    fun <K : Any, T> items(
        list: List<T>,
        key: (T) -> K,
        content: @Composable LazyItemScope.(T, PaddingValues, Shape) -> Unit,
    )

    public fun <K : Any, T : GroupValueProvider<K>> items(
        array: Array<T>,
        content: @Composable LazyItemScope.(T, PaddingValues, Shape) -> Unit,
    )

    public fun <K : Any, T : GroupValueProvider<K>> items(
        values: List<T>,
        content: @Composable LazyItemScope.(T, PaddingValues, Shape) -> Unit,
    )

    public fun <K : Any, T : GroupValueProvider<K>, V> items(
        map: Map<T, V>,
        content: @Composable LazyItemScope.(T, V, PaddingValues, Shape) -> Unit,
    )

    public fun <K : Any, T, V> items(
        map: Map<T, V>,
        key: (T) -> K,
        content: @Composable LazyItemScope.(T, V, PaddingValues, Shape) -> Unit,
    )
}

public fun <K : Any, T : GroupValueProvider<K>> SaneLazyListScope.group(
    array: Array<T>,
    content: @Composable (LazyItemScope.(T, PaddingValues, Shape) -> Unit),
) {
    group(size = array.size) { items(array = array, content = content) }
}

public fun <K : Any, T : GroupValueProvider<K>> SaneLazyListScope.group(
    list: List<T>,
    content: @Composable (LazyItemScope.(T, PaddingValues, Shape) -> Unit),
) {
    group(size = list.size) { items(values = list, content = content) }
}

public fun <K : Any, T> SaneLazyListScope.group(
    list: List<T>,
    key: (T) -> K,
    content: @Composable (LazyItemScope.(T, PaddingValues, Shape) -> Unit),
) {
    group(size = list.size) { items(list = list, key = key, content = content) }
}

public fun <K : Any, T : GroupValueProvider<K>, V> SaneLazyListScope.group(
    map: Map<T, V>,
    content: @Composable (LazyItemScope.(T, V, PaddingValues, Shape) -> Unit),
) {
    group(size = map.size) { items(map = map, content = content) }
}

public fun <K : Any, T, V> SaneLazyListScope.group(
    map: Map<T, V>,
    key: (T) -> K,
    content: @Composable (LazyItemScope.(T, V, PaddingValues, Shape) -> Unit),
) {
    group(size = map.size) { items(map = map, key = key, content = content) }
}

