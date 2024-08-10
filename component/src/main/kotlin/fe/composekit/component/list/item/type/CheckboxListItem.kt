package fe.composekit.component.list.item.type

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import fe.android.compose.content.OptionalContent
import fe.android.compose.text.TextContent
import fe.composekit.component.CommonDefaults
import fe.composekit.component.list.column.CustomListItemContainerHeight
import fe.composekit.component.list.column.CustomListItemDefaults
import fe.composekit.component.list.column.CustomListItemPadding
import fe.composekit.component.list.column.CustomListItemTextOptions
import fe.composekit.component.list.column.shape.ClickableShapeListItem
import fe.composekit.component.list.column.shape.ShapeListItemDefaults
import fe.composekit.component.list.item.ContentPosition
import fe.composekit.component.shape.CustomShapeDefaults

@Composable
fun CheckboxListItem(
    enabled: Boolean = true,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    colors: ListItemColors = ShapeListItemDefaults.colors(),
    containerHeight: CustomListItemContainerHeight = CustomListItemDefaults.containerHeight(),
    innerPadding: CustomListItemPadding = CustomListItemDefaults.padding(),
    textOptions: CustomListItemTextOptions = CustomListItemDefaults.textOptions(),
    position: ContentPosition,
    headlineContent: TextContent,
    overlineContent: TextContent? = null,
    supportingContent: TextContent? = null,
    otherContent: OptionalContent,
) {
    ClickableShapeListItem(
        enabled = enabled,
        onClick = { onCheckedChange(!checked) },
        role = Role.Checkbox,
        shape = shape,
        padding = padding,
        colors = colors,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        position = position,
        primaryContent = {
            DefaultListItemCheckbox(enabled = enabled, checked = checked, onCheckedChange = onCheckedChange)
        },
        otherContent = otherContent,
        containerHeight = containerHeight,
        innerPadding = innerPadding,
        textOptions = textOptions
    )
}

@Composable
private fun DefaultListItemCheckbox(
    enabled: Boolean = true,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Checkbox(
        modifier = CommonDefaults.BaseContentModifier,
        enabled = enabled,
        checked = checked,
        onCheckedChange = onCheckedChange
    )
}
