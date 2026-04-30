package fe.composekit.component.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import fe.composekit.component.list.column.SaneLazyColumnDefaults
import fe.composekit.component.list.column.SaneLazyColumnLayout
import fe.composekit.layout.column.SaneLazyListScope

@Composable
public fun SaneScaffoldSettingsPageInternal(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = SaneLazyColumnDefaults.ContentPadding,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: SaneLazyListScope.() -> Unit,
) {
    val scope = SaneScaffoldSettingsPageOverrideScope(
        modifier = modifier,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        state = state,
        content = content,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    )
    with(LocalSaneScaffoldSettingsPageOverride.current) { scope.SaneScaffoldSettingsPage() }
}

@Composable
public fun SaneSettingsScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.navigationBars),
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentWindowInsets = contentWindowInsets,
        content = content
    )
}

public val LocalSaneScaffoldSettingsPageOverride: ProvidableCompositionLocal<SaneScaffoldSettingsPageOverride> =
    compositionLocalOf {
        DefaultSaneScaffoldSettingsPageOverride
    }

public interface SaneScaffoldSettingsPageOverride {
    @Composable
    public fun SaneScaffoldSettingsPageOverrideScope.SaneScaffoldSettingsPage()
}

public class SaneScaffoldSettingsPageOverrideScope(
    public val modifier: Modifier,
    public val topBar: @Composable () -> Unit,
    public val floatingActionButton: @Composable () -> Unit = {},
    public val floatingActionButtonPosition: FabPosition,
    public val state: LazyListState,
    public val content: SaneLazyListScope.() -> Unit,
    public val contentPadding: PaddingValues,
    public val verticalArrangement: Arrangement.Vertical,
)

public object DefaultSaneScaffoldSettingsPageOverride : SaneScaffoldSettingsPageOverride {
    @Composable
    override fun SaneScaffoldSettingsPageOverrideScope.SaneScaffoldSettingsPage() {
        SaneSettingsScaffold(
            modifier = modifier,
            topBar = topBar,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition,
            content = { padding ->
                SaneLazyColumnLayout(
                    state = state,
                    padding = padding,
                    contentPadding = contentPadding,
                    verticalArrangement = verticalArrangement,
                    content = content
                )
            }
        )
    }
}
