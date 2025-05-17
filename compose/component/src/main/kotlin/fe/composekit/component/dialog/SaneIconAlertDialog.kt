package fe.composekit.component.dialog

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fe.android.compose.content.OptionalContent
import fe.android.compose.text.TextContent
import my.nanihadesuka.compose.ScrollbarSettings

@Composable
public fun SaneIconAlertDialog(
    state: LazyListState = rememberLazyListState(),
    modifier: Modifier = SaneDialogDefaults.SaneDialogContentModifier,
    innerModifier: Modifier = SaneDialogDefaults.SaneDialogInnerModifier,
    settings: ScrollbarSettings = DialogDefaults.DefaultScrollbarSettings,
    icon: OptionalContent? = null,
    title: TextContent,
    onDismiss: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: OptionalContent? = null,
    content: @Composable BoxScope.() -> Unit
) {
    AlertDialog(
        padding = SaneDialogDefaults.SaneDialogPadding,
        icon = icon,
        title = title.content,
        text = {
            SaneAlertDialogContent(
                state = state,
                modifier = modifier,
                innerModifier = innerModifier,
                settings = settings,
                dividerTop = null,
                dividerBottom = null,
                content = content
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
    )
}
