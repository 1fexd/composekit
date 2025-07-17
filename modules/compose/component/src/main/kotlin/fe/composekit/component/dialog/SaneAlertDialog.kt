package fe.composekit.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.android.compose.content.OptionalContent
import fe.android.compose.text.DefaultContent.Companion.text
import fe.android.compose.text.TextContent

@Composable
public fun SaneAlertDialog(
    state: LazyListState = rememberLazyListState(),
    title: TextContent,
    onDismiss: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: OptionalContent? = null,
    content: @Composable BoxScope.() -> Unit
) {
    AlertDialog(
        padding = SaneDialogDefaults.SaneDialogPadding,
        title = title.content,
        text = {
            SaneAlertDialogContent(
                state = state,
                modifier = SaneDialogDefaults.SaneDialogContentModifier,
                innerModifier = SaneDialogDefaults.SaneDialogInnerModifier,
                settings = DialogDefaults.DefaultScrollbarSettings,
                dividerTop = { HorizontalDivider() },
                dividerBottom = { HorizontalDivider() },
                content = content
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
    )
}

@Composable
public fun SaneAlertDialogTextButton(content: TextContent, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        content.content()
    }
}

@Preview
@Composable
private fun SaneAlertDialogPreview() {
    val state = rememberLazyListState()
    SaneAlertDialog(
        state = state,
        title = text("Test"),
        onDismiss = {},
        confirmButton = {
            SaneAlertDialogTextButton(
                content = text("Save"),
                onClick = {}
            )
        },
        dismissButton = {
            SaneAlertDialogTextButton(
                content = text("Dismiss"),
                onClick = {}
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.matchParentSize(),
            state = state,
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(count = 50) {
                Text(text = "$it")
            }
        }
    }
}
