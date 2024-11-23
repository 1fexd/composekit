package fe.composekit.component.list.item.default

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import fe.android.compose.content.OptionalContent
import fe.android.compose.content.rememberOptionalContent
import fe.android.compose.icon.IconPainter
import fe.android.compose.text.TextContent
import fe.composekit.component.CommonDefaults
import fe.composekit.component.list.column.shape.ClickableShapeListItem
import fe.composekit.component.list.item.ContentPosition
import fe.composekit.component.shape.CustomShapeDefaults

@Composable
fun DefaultTwoLineIconClickableShapeListItem(
    enabled: Boolean = true,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    onClick: () -> Unit,
    position: ContentPosition = ContentPosition.Leading,
    headlineContent: TextContent,
    overlineContent: TextContent? = null,
    supportingContent: TextContent? = null,
    icon: IconPainter? = null,
    otherContent: OptionalContent = null,
) {
    val content: OptionalContent = rememberOptionalContent(icon) {
        DefaultListItemIcon(icon = it)
    }

    ClickableShapeListItem(
        enabled = enabled,
        shape = shape,
        padding = padding,
        onClick = onClick,
        role = Role.Button,
        overlineContent = overlineContent,
        headlineContent = headlineContent,
        supportingContent = supportingContent,
        position = position,
        primaryContent = content,
        otherContent = otherContent
    )
}

@Composable
private fun DefaultListItemIcon(
    icon: IconPainter,
    contentDescription: String? = null,
) {
    val painter = icon.rememberPainter()

    Box(modifier = CommonDefaults.BaseContentModifier, contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painter,
            contentDescription = contentDescription
        )
    }
}
