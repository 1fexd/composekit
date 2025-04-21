package fe.composekit.component.list.column.group

import androidx.compose.runtime.Stable
import fe.android.compose.icon.IconPainter
import fe.android.compose.text.OptionalTextContent
import fe.android.compose.text.TextContent
import fe.composekit.layout.column.GroupValueProvider


@Stable
public open class ListItemData<T>(
    public val icon: IconPainter? = null,
    public val headlineContent: TextContent,
    public val subtitleContent: OptionalTextContent = null,
    public val additional: T? = null,
) : GroupValueProvider<Any> {
    override val key: Any = headlineContent.key
}

@DslMarker
public annotation class RememberGroupDslMarker

@RememberGroupDslMarker
public open class RememberGroupScope<K : Any, T : GroupValueProvider<K>>(
    private val provider: MutableList<T> = mutableListOf(),
) {
    public val providers: List<T>
        get() = provider

    public fun add(provider: T) {
        this.provider.add(provider)
    }
}
