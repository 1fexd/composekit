package fe.composekit.component.list.column

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fe.composekit.component.list.column.group.SaneLazyListScopeImpl
import fe.composekit.layout.column.SaneLazyColumnLayoutBase
import fe.composekit.layout.column.SaneLazyListScope

public object SaneLazyColumnDefaults {
    public val VerticalSpacing: Dp = 12.dp
    public val HorizontalSpacing: Dp = 20.dp
    public val BottomSpacing: Dp = 6.dp

    public val ContentPadding: PaddingValues = PaddingValues(
        start = HorizontalSpacing,
        end = HorizontalSpacing,
        top = VerticalSpacing,
        bottom = BottomSpacing
    )

    public val TextDividerPadding: PaddingValues = PaddingValues(
        start = 16.dp,
        top = VerticalSpacing,
        bottom = VerticalSpacing,
        end = 16.dp
    )

    public val GroupSpacingTop: PaddingValues = PaddingValues(bottom = 1.dp)
    public val GroupSpacingMiddle: PaddingValues = PaddingValues(vertical = 1.dp)
    public val GroupSpacingBottom: PaddingValues = PaddingValues(top = 1.dp)
}

@Composable
public fun SaneLazyColumnLayout(
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
