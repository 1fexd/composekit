package fe.composekit.layout.column

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.LazyListScope

@DslMarker
public annotation class SaneLazyListScopeDslMarker


@SaneLazyListScopeDslMarker
public interface SaneLazyListScope : LazyListScope {
    public fun divider(@StringRes id: Int, key: Any = id)
    public fun divider(key: Any, text: String)
    public fun group(size: Int, content: SaneLazyColumnGroupScope.() -> Unit)
    public fun group(base: Int, vararg optional: Boolean, content: SaneLazyColumnGroupScope.() -> Unit)
}
