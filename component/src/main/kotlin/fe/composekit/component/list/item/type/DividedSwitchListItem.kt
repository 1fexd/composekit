package fe.composekit.component.list.item.type

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.composekit.component.CommonDefaults
import fe.composekit.component.shape.CustomShapeDefaults
import fe.android.compose.content.OptionalContent
import fe.android.compose.content.rememberOptionalContent
import fe.android.compose.text.DefaultContent.Companion.text
import fe.android.compose.text.TextContent
import fe.composekit.component.list.column.shape.ClickableShapeListItem
import fe.composekit.component.list.item.ContentPosition
import fe.composekit.component.list.item.EnabledContent
import fe.composekit.component.list.item.EnabledContentSet


@Composable
fun DividedSwitchListItem(
    enabled: EnabledContentSet = EnabledContent.all,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onContentClick: () -> Unit,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    position: ContentPosition,
    headlineContent: TextContent,
    overlineContent: TextContent? = null,
    supportingContent: TextContent? = null,
    otherContent: OptionalContent = null,
) {
    ClickableShapeListItem(
        enabled = EnabledContent.Main in enabled,
        onClick = onContentClick,
        role = Role.Button,
        shape = shape,
        padding = padding,
        position = position,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        primaryContent = {
            DefaultDividedListItemSwitch(
                enabled = EnabledContent.Primary in enabled,
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        },
        otherContent = otherContent
    )
}

@Composable
private fun DefaultDividedListItemSwitch(
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

    Box(modifier = CommonDefaults.BaseContentModifier) {
        VerticalDivider(
            modifier = Modifier
                .height(32.dp)
                .align(Alignment.Center)
        )
    }

    Switch(
        modifier = CommonDefaults.BaseContentModifier.padding(start = 12.dp),
        enabled = enabled,
        checked = checked,
        thumbContent = thumbContent,
        onCheckedChange = onCheckedChange
    )
}


@Preview
@Composable
fun DividedClickableShapeListItemPreview() {


    var checked by remember { mutableStateOf(true) }
    DividedSwitchListItem(
        checked = checked,
        onContentClick = {

        },
        onCheckedChange = { checked = !checked },
        position = ContentPosition.Trailing,
        headlineContent = text("Preview"),
        supportingContent = text(
            "Subtitle 123\ntest\n" +
                    "test\n" +
                    "test\n" +
                    "test\n" +
                    "test"
        ),
    )
}
