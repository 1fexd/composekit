package fe.composekit.component.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fe.android.compose.content.OptionalContent
import my.nanihadesuka.compose.LazyColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings

@Composable
public fun SaneAlertDialogContent(
    modifier: Modifier = SaneDialogDefaults.SaneDialogContentModifier,
    innerModifier: Modifier = SaneDialogDefaults.SaneDialogInnerModifier,
    state: LazyListState = rememberLazyListState(),
    settings: ScrollbarSettings = DialogDefaults.DefaultScrollbarSettings,
    dividerTop: OptionalContent? = { HorizontalDivider() },
    dividerBottom: OptionalContent? = { HorizontalDivider() },
    content: @Composable BoxScope.() -> Unit
) {
    Column(modifier = modifier) {
        dividerTop?.invoke()

        LazyColumnScrollbar(modifier = Modifier, state = state, settings = settings) {
            Box(
                modifier = innerModifier,
                content = content
            )
        }

        dividerTop?.invoke()
    }
}
