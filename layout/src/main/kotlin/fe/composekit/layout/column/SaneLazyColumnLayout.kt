package fe.composekit.layout.column

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T : SaneLazyListScope> SaneLazyColumnLayoutBase(
    modifier: Modifier,
    state: LazyListState,
    padding: PaddingValues,
    contentPadding: PaddingValues,
    verticalArrangement: Arrangement.Vertical,
    scopeImpl: (LazyListScope) -> T,
    content: T.() -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(padding)
            .fillMaxSize(),
        state = state,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        content = {
            content(scopeImpl(this))

            item(key = -1) {
                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
            }
        }
    )
}
