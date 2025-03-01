package fe.composekit.component.list.column

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fe.composekit.component.list.column.group.SaneLazyListScopeImpl
import fe.composekit.layout.column.SaneLazyColumnLayoutBase
import fe.composekit.layout.column.SaneLazyListScope

object SaneLazyColumnDefaults {
    val VerticalSpacing = 12.dp
    val HorizontalSpacing = 20.dp
    val BottomSpacing = 6.dp

    val ContentPadding = PaddingValues(
        start = HorizontalSpacing,
        end = HorizontalSpacing,
        top = VerticalSpacing,
        bottom = BottomSpacing
    )

    val TextDividerPadding = PaddingValues(
        start = 16.dp,
        top = VerticalSpacing,
        bottom = VerticalSpacing,
        end = 16.dp
    )

    val GroupSpacingTop = PaddingValues(bottom = 1.dp)
    val GroupSpacingMiddle = PaddingValues(vertical = 1.dp)
    val GroupSpacingBottom = PaddingValues(top = 1.dp)
}

@Composable
fun SaneLazyColumnLayout(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    padding: PaddingValues,
    contentPadding: PaddingValues = SaneLazyColumnDefaults.ContentPadding,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: SaneLazyListScope.() -> Unit,
) {
    SaneLazyColumnLayoutBase(
        modifier = modifier,
        state = state,
        padding = padding,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        scopeImpl = { SaneLazyListScopeImpl(it) },
        content = content
    )
}
