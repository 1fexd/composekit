package fe.composekit.component.list.column.group

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import fe.composekit.component.ContentType
import fe.composekit.layout.column.SaneLazyColumnGroupScope
import fe.composekit.layout.column.SaneLazyListScope


@Stable
public data class SaneLazyListScopeImpl(val lazyListScope: LazyListScope) : SaneLazyListScope, LazyListScope by lazyListScope {
    override fun divider(@StringRes id: Int, key: Any) {
        item(key = compute(id, key), contentType = ContentType.Divider) {
            SaneLazyListTextDivider(text = stringResource(id = id))
        }
    }

    override fun divider(key: Any, text: String) {
        item(key = key, contentType = ContentType.Divider) {
            SaneLazyListTextDivider(text = text)
        }
    }

    override fun group(size: Int, content: SaneLazyColumnGroupScope.() -> Unit) {
        require(size > 0) { "Group size must be greater than 0" }
        SaneLazyColumnGroupScopeImpl(size, this).apply(content)
    }
}

private fun compute(@StringRes id: Int, key: Any): Any {
    val keyHashCode = key.hashCode().toLong()
    return if (key == id) keyHashCode + System.currentTimeMillis() else keyHashCode
}
