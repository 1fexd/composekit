package fe.composekit.component.list.column.group

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fe.composekit.component.list.column.SaneLazyColumnDefaults

@Composable
public fun SaneLazyListTextDivider(text: String, padding: PaddingValues = SaneLazyColumnDefaults.TextDividerPadding) {
    Text(
        modifier = Modifier.padding(padding),
        text = text,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleSmall
    )
}
