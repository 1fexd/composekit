package fe.composekit.component.list.item.type

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import fe.android.compose.content.OptionalContent
import fe.android.compose.content.rememberOptionalContent
import fe.android.compose.text.TextContent
import fe.composekit.component.CommonDefaults
import fe.composekit.component.list.column.shape.ClickableShapeListItem
import fe.composekit.component.list.item.ContentPosition
import fe.composekit.component.list.item.EnabledContent
import fe.composekit.component.list.item.EnabledContentSet
import fe.composekit.component.shape.CustomShapeDefaults

@Composable
public fun SwitchListItem(
    enabled: EnabledContentSet = EnabledContent.all,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    position: ContentPosition = ContentPosition.Trailing,
    headlineContent: TextContent,
    overlineContent: TextContent? = null,
    supportingContent: TextContent? = null,
    otherContent: OptionalContent = null,
) {
    ClickableShapeListItem(
        enabled = EnabledContent.Main in enabled,
        onClick = { onCheckedChange(!checked) },
        role = Role.Switch,
        shape = shape,
        padding = padding,
        position = position,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        primaryContent = {
            DefaultListItemSwitch(
                enabled = EnabledContent.Primary in enabled,
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        },
        otherContent = otherContent
    )
}

@Composable
private fun DefaultListItemSwitch(
    enabled: Boolean = true,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    val thumbContent = rememberOptionalContent(checked) {
        Icon(
            modifier = Modifier.size(SwitchDefaults.IconSize),
            imageVector = Icons.Filled.Check,
            contentDescription = null,
        )
    }

    Switch(
        modifier = CommonDefaults.BaseContentModifier,
        enabled = enabled,
        checked = checked,
        thumbContent = thumbContent,
        onCheckedChange = onCheckedChange
    )
}
