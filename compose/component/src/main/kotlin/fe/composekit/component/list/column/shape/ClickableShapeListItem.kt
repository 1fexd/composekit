package fe.composekit.component.list.column.shape

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import fe.composekit.component.CommonDefaults
import fe.composekit.component.shape.CustomShapeDefaults
import fe.android.compose.content.OptionalContent
import fe.android.compose.text.OptionalTextContent
import fe.android.compose.text.TextContent
import fe.android.compose.extension.enabled
import fe.composekit.component.list.item.ContentPosition
import fe.composekit.component.list.column.*

@Composable
public fun ClickableShapeListItem(
    modifier: Modifier = CommonDefaults.BaseModifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    role: Role? = null,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    colors: ListItemColors = ShapeListItemDefaults.colors(),
    containerHeight: CustomListItemContainerHeight = CustomListItemDefaults.containerHeight(),
    innerPadding: CustomListItemPadding = CustomListItemDefaults.padding(),
    textOptions: CustomListItemTextOptions = CustomListItemDefaults.textOptions(),
    position: ContentPosition,
    headlineContent: TextContent,
    overlineContent: OptionalTextContent = null,
    supportingContent: OptionalTextContent = null,
    primaryContent: OptionalContent = null,
    otherContent: OptionalContent = null,
) {
    ClickableShapeListItem(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        role = role,
        shape = shape,
        padding = padding,
        colors = colors,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        leadingContent = ContentPosition.Leading.pick(position, primaryContent, otherContent),
        trailingContent = ContentPosition.Trailing.pick(position, primaryContent, otherContent),
        containerHeight = containerHeight,
        innerPadding = innerPadding,
        textOptions = textOptions
    )
}

@Composable
public fun ClickableShapeListItem(
    modifier: Modifier = CommonDefaults.BaseModifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    role: Role? = null,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    colors: ListItemColors = ShapeListItemDefaults.colors(),
    containerHeight: CustomListItemContainerHeight = CustomListItemDefaults.containerHeight(),
    innerPadding: CustomListItemPadding = CustomListItemDefaults.padding(),
    textOptions: CustomListItemTextOptions = CustomListItemDefaults.textOptions(),
    headlineContent: TextContent,
    overlineContent: OptionalTextContent = null,
    supportingContent: OptionalTextContent = null,
    leadingContent: OptionalContent = null,
    trailingContent: OptionalContent = null,
) {
    ShapeListItem(
        modifier = Modifier
            .clickable(enabled = enabled, role = role, onClick = onClick)
            .enabled(enabled)
            .then(modifier),
        shape = shape,
        padding = padding,
        colors = colors,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        containerHeight = containerHeight,
        innerPadding = innerPadding,
        textOptions = textOptions
    )
}
