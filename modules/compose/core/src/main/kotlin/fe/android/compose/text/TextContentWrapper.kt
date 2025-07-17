package fe.android.compose.text

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
public fun TextContentWrapper(modifier: Modifier = Modifier, textContent: TextContent) {
    val content = remember(textContent) { textContent.content }

    Box(modifier = modifier) {
        content()
    }
}
