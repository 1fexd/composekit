package fe.composekit.component.list.column.group

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
import fe.composekit.component.ContentType
import fe.composekit.component.list.column.SaneLazyColumnDefaults
import fe.composekit.component.shape.CustomShapeDefaults
import fe.composekit.layout.column.GroupValueProvider
import fe.composekit.layout.column.SaneLazyColumnGroupScope
import fe.composekit.layout.column.SaneLazyListScope


@Stable
data class GroupItem(
    val contentType: ContentType,
    val padding: PaddingValues = PaddingValues(),
    val shape: Shape,
) {
    companion object {
        val Top = GroupItem(
            ContentType.TopGroupItem,
            SaneLazyColumnDefaults.GroupSpacingTop,
            CustomShapeDefaults.TopShape
        )

        val Middle = GroupItem(
            ContentType.MiddleGroupItem,
            SaneLazyColumnDefaults.GroupSpacingMiddle,
            CustomShapeDefaults.MiddleShape
        )

        val Bottom = GroupItem(
            ContentType.BottomGroupItem,
            SaneLazyColumnDefaults.GroupSpacingBottom,
            CustomShapeDefaults.BottomShape
        )

        val Single = GroupItem(
            ContentType.SingleGroupItem,
            shape = CustomShapeDefaults.SingleShape
        )
    }
}

@Stable
data class SaneLazyColumnGroupScopeImpl(
    val size: Int,
    val listScope: SaneLazyListScope,
) : SaneLazyColumnGroupScope, SaneLazyListScope by listScope {

    private var counter = 0

    private fun currentItem(): GroupItem {
        if (size == 1) return GroupItem.Single

        return when (counter) {
            0 -> GroupItem.Top
            size - 1 -> GroupItem.Bottom
            else -> GroupItem.Middle
        }
    }

    override fun item(key: Any, content: @Composable LazyItemScope.(PaddingValues, Shape) -> Unit) {
        require(counter < size) { "Group has ${counter + 1} items, but only supports $size" }

        val groupItem = currentItem()
        item(key = key, contentType = groupItem.contentType) {
            content(groupItem.padding, groupItem.shape)
        }

        counter++
    }

    override fun <K : Any, T : GroupValueProvider<K>> items(
        array: Array<T>,
        content: @Composable (LazyItemScope.(T, PaddingValues, Shape) -> Unit),
    ) {
        itemsInternal(valueSize = array.size, values = array.iterator(), key = { it.key }, content = content)
    }

    override fun <K : Any, T : GroupValueProvider<K>> items(
        values: List<T>,
        content: @Composable (LazyItemScope.(T, PaddingValues, Shape) -> Unit),
    ) {
        itemsInternal(valueSize = values.size, values = values.iterator(), key = { it.key }, content = content)
    }

    override fun <K : Any, T> items(
        list: List<T>,
        key: (T) -> K,
        content: @Composable (LazyItemScope.(T, PaddingValues, Shape) -> Unit),
    ) {
        itemsInternal(valueSize = list.size, values = list.iterator(), key = key, content = content)
    }

    private inline fun <K : Any, T> itemsInternal(
        valueSize: Int,
        values: Iterator<T>,
        key: (T) -> K,
        crossinline content: @Composable (LazyItemScope.(T, PaddingValues, Shape) -> Unit),
    ) {
        require(counter < size) { "Group has ${counter + 1} items, but only supports $size" }
        require(counter + valueSize <= size) { "Group has ${counter + 1}/$size items, can't fit an additional $valueSize" }

        for (value in values) {
            val groupItem = currentItem()
            item(key = key(value), contentType = groupItem) {
                content(value, groupItem.padding, groupItem.shape)
            }

            counter++
        }
    }

    override fun <K : Any, T : GroupValueProvider<K>, V> items(
        map: Map<T, V>,
        content: @Composable (LazyItemScope.(T, V, PaddingValues, Shape) -> Unit),
    ) {
        itemsInternal(values = map, key = { it.key }, content = content)
    }

    override fun <K : Any, T, V> items(
        map: Map<T, V>,
        key: (T) -> K,
        content: @Composable (LazyItemScope.(T, V, PaddingValues, Shape) -> Unit),
    ) {
        itemsInternal(values = map, key = key, content = content)
    }

    private inline fun <K : Any, T, V> itemsInternal(
        values: Map<T, V>,
        key: (T) -> K,
        crossinline content: @Composable (LazyItemScope.(T, V, PaddingValues, Shape) -> Unit),
    ) {
        require(counter < size) { "Group has ${counter + 1} items, but only supports $size" }
        require(counter + values.size <= size) { "Group has ${counter + 1}/$size items, can't fit an additional ${values.size}" }

        for ((k, value) in values) {
            val groupItem = currentItem()
            item(key = key(k), contentType = groupItem) {
                content(k, value, groupItem.padding, groupItem.shape)
            }

            counter++
        }
    }
}
