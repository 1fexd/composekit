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
    modifier: Modifier = Modifier,
    contentModifier: Modifier = SaneDialogDefaults.SaneDialogContentModifier,
    innerModifier: Modifier = SaneDialogDefaults.SaneDialogInnerModifier,
    state: LazyListState = rememberLazyListState(),
    settings: ScrollbarSettings = DialogDefaults.DefaultScrollbarSettings,
    icon: OptionalContent = null,
    title: TextContent,
    onDismiss: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: OptionalContent = null,
    content: @Composable BoxScope.() -> Unit
) {
    AlertDialog(
        modifier = modifier,
        padding = SaneDialogDefaults.SaneDialogPadding,
        icon = icon,
        title = title.content,
        text = {
            SaneAlertDialogContent(
                state = state,
                contentModifier = contentModifier,
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
