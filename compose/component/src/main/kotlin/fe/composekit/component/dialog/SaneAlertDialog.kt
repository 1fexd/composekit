package fe.composekit.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
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
import fe.android.compose.text.DefaultContent.Companion.text
import fe.android.compose.text.TextContent
import my.nanihadesuka.compose.LazyColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings

@Composable
public fun SaneAlertDialog(
    state: LazyListState = rememberLazyListState(),
    title: TextContent,
    onDismiss: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
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

@Composable
public fun SaneAlertDialogContent(
    state: LazyListState = rememberLazyListState(),
    modifier: Modifier = SaneDialogDefaults.SaneDialogContentModifier,
    innerModifier: Modifier = SaneDialogDefaults.SaneDialogInnerModifier,
    settings: ScrollbarSettings = DialogDefaults.DefaultScrollbarSettings,
    content: @Composable BoxScope.() -> Unit
) {
    Column(modifier = modifier) {
        HorizontalDivider()

        LazyColumnScrollbar(modifier = Modifier, state = state, settings = settings) {
            Box(
                modifier = innerModifier,
                content = content
            )
        }

        HorizontalDivider()
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
