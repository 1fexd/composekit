package fe.composekit.component.list.column.group

import androidx.compose.runtime.Stable
import fe.android.compose.icon.IconPainter
import fe.android.compose.text.OptionalTextContent
import fe.android.compose.text.TextContent
import fe.composekit.layout.column.GroupValueProvider


@Stable
open class ListItemData<T>(
    val icon: IconPainter? = null,
    val headlineContent: TextContent,
    val subtitleContent: OptionalTextContent = null,
    val additional: T? = null,
) : GroupValueProvider<Any> {
    override val key = headlineContent.key
}

@DslMarker
annotation class RememberGroupDslMarker

@RememberGroupDslMarker
open class RememberGroupScope<K : Any, T : GroupValueProvider<K>>(
    private val provider: MutableList<T> = mutableListOf(),
) {
    val providers: List<T>
        get() = provider

    fun add(provider: T) {
        this.provider.add(provider)
    }
}
