package fe.composekit.component.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fe.android.compose.text.TextOptions
import fe.composekit.component.CommonDefaults
import fe.composekit.component.list.column.CustomListItemDefaults
import fe.composekit.component.list.column.CustomListItemPadding
import fe.composekit.component.list.column.CustomListItemTextOptions
import fe.composekit.component.list.column.shape.ShapeListItemDefaults

public object DialogDefaults {
    public val RadioButtonWidth: Dp = 48.dp
    public val ContentPadding: Dp = 6.dp

    public val ListItemInnerPadding: CustomListItemPadding = CustomListItemDefaults.padding(
        start = 0.dp,
        leadingContentEnd = 4.dp
    )

    public val ListItemTextOptions: CustomListItemTextOptions
        @Composable
        get() = CustomListItemDefaults.textOptions(
            headline = TextOptions(style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp))
        )

    public val ListItemColors: ListItemColors
        @Composable
        get() = ShapeListItemDefaults.colors(
            headlineColor = AlertDialogDefaults.textContentColor,
            supportingColor = AlertDialogDefaults.textContentColor,
            containerColor = AlertDialogDefaults.containerColor
        )

    public val DialogBoxPadding: Dp = 24.dp
    public val DialogIconBottomPadding: Dp = 16.dp
    public val DialogTitleBottomPadding: Dp = 16.dp
    public val DialogTextBottomPadding: Dp = 24.dp

    public val DefaultDialogPadding: DialogPaddingOptions
        @Composable
        get() = DialogPaddingOptions(
            box = PaddingValues(all = DialogBoxPadding),
            icon = PaddingValues(bottom = DialogIconBottomPadding),
            title = PaddingValues(bottom = DialogTitleBottomPadding),
            text = PaddingValues(bottom = DialogTextBottomPadding),
            buttons = CommonDefaults.EmptyPadding
        )

    public val ButtonsMainAxisSpacing: Dp = 8.dp
    public val ButtonsCrossAxisSpacing: Dp = 12.dp

    public val DefaultButtonsSpacing: ButtonSpacingOptions = ButtonSpacingOptions(
        mainAxisSpacing = ButtonsMainAxisSpacing,
        crossAxisSpacing = ButtonsCrossAxisSpacing,
    )

    public val DialogMinWidth: Dp = 280.dp
    public val DialogMaxWidth: Dp = 560.dp

    public val DefaultDialogSize: DialogSizeOptions = DialogSizeOptions(
        minWidth = DialogMinWidth,
        maxWidth = DialogMaxWidth,
    )
}
