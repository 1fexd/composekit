package fe.composekit.component.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.android.compose.icon.IconPainter
import fe.android.compose.icon.iconPainter
import fe.android.compose.text.DefaultContent.Companion.text
import fe.android.compose.text.ProvideContentColorOptionsStyleText
import fe.android.compose.text.TextContent
import fe.android.compose.text.TextOptions

@Composable
public fun PageInfo(
    modifier: Modifier = Modifier,
    textContent: TextContent,
    icon: IconPainter = Icons.Outlined.Info.iconPainter,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainerHigh)

        Icon(
            painter = icon.rememberPainter(),
            contentDescription = null,
            tint = contentColor,
        )

        ProvideContentColorOptionsStyleText(
            contentColor = contentColor,
            textOptions = TextOptions(style = MaterialTheme.typography.bodyMedium),
            content = textContent.content
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PageInfoPreview() {
    PageInfo(
        textContent = text("Test")
    )
}
