package fe.composekit.component.list.item.type

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fe.composekit.component.CommonDefaults
import fe.composekit.component.shape.CustomShapeDefaults
import fe.android.compose.content.OptionalContent
import fe.android.compose.text.TextContent
import fe.composekit.component.list.column.CustomListItemContainerHeight
import fe.composekit.component.list.column.CustomListItemDefaults
import fe.composekit.component.list.column.CustomListItemPadding
import fe.composekit.component.list.column.CustomListItemTextOptions
import fe.composekit.component.list.column.shape.SelectableShapeListItem
import fe.composekit.component.list.column.shape.ShapeListItemDefaults
import fe.composekit.component.list.item.ContentPosition
import fe.composekit.component.list.item.EnabledContent
import fe.composekit.component.list.item.EnabledContentSet

object RadioButtonListItemDefaults {
    val Width = 24.dp
}

@Composable
fun RadioButtonListItem(
    modifier: Modifier = CommonDefaults.BaseModifier,
    enabled: EnabledContentSet = EnabledContent.all,
    width: Dp = RadioButtonListItemDefaults.Width,
    selected: Boolean,
    onSelect: () -> Unit,
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
    SelectableShapeListItem(
        modifier = modifier,
        enabled = EnabledContent.Main in enabled,
        selected = selected,
        onClick = onSelect,
        role = Role.RadioButton,
        shape = shape,
        padding = padding,
        colors = colors,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        position = position,
        primaryContent = {
            DefaultListItemRadioButton(
                enabled = EnabledContent.Primary in enabled,
                width = width,
                selected = selected,
                onSelect = onSelect
            )
        },
        otherContent = otherContent,
        containerHeight = containerHeight,
        innerPadding = innerPadding,
        textOptions = textOptions
    )
}

@Composable
private fun DefaultListItemRadioButton(
    enabled: Boolean = true,
    width: Dp = RadioButtonListItemDefaults.Width,
    selected: Boolean,
    onSelect: () -> Unit,
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides width) {
        RadioButton(
            modifier = CommonDefaults.BaseContentModifier.width(width),
            enabled = enabled,
            selected = selected,
            onClick = onSelect,
        )
    }
}
