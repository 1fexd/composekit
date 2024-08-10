package fe.composekit.layout.column

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.LazyListScope

@DslMarker
annotation class SaneLazyListScopeDslMarker


@SaneLazyListScopeDslMarker
interface SaneLazyListScope : LazyListScope {
    fun divider(@StringRes id: Int, key: Any = id)

    fun divider(key: Any, text: String)

    fun group(size: Int, content: SaneLazyColumnGroupScope.() -> Unit)
}
