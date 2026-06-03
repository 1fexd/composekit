package fe.composekit.component.dialog

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fe.android.compose.content.OptionalContent
import fe.android.compose.text.TextContent
import my.nanihadesuka.compose.ScrollbarSettings

@Deprecated("Use new settings-based API")
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

@Composable
public fun SaneIconAlertDialog2(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = SaneDialogDefaults.SaneDialogContentModifier,
    state: LazyListState = rememberLazyListState(),
    settings: ScrollbarSettings = DialogDefaults.DefaultScrollbarSettings,
    contentSettings: SaneDialogContentSettings = SaneDialogDefaults.ContentSettings,
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
            SaneAlertDialogContent2(
                modifier = contentModifier,
                state = state,
                contentSettings = contentSettings,
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
