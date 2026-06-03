package fe.composekit.component.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public object SaneDialogDefaults {
    public val HorizontalContentPadding: Dp = 16.dp
    public val MaxHeight: Dp = 280.dp

    public val SaneDialogPadding: DialogPaddingOptions = DialogPaddingOptions(
        box = PaddingValues(vertical = DialogDefaults.DialogBoxPadding),
        icon = PaddingValues(
            start = DialogDefaults.DialogBoxPadding,
            end = DialogDefaults.DialogBoxPadding,
            bottom = DialogDefaults.DialogIconBottomPadding
        ),
        title = PaddingValues(
            start = DialogDefaults.DialogBoxPadding,
            end = DialogDefaults.DialogBoxPadding,
            bottom = DialogDefaults.DialogTitleBottomPadding
        ),
        text = PaddingValues(bottom = DialogDefaults.DialogTextBottomPadding),
        buttons = PaddingValues(horizontal = DialogDefaults.DialogBoxPadding)
    )

    public val ContentSettings: SaneDialogContentSettings = SaneDialogContentSettings(
        innerPadding = PaddingValues(horizontal = HorizontalContentPadding),
        contentSize = ContentSize.Max
    )

    public val SaneDialogContentModifier: Modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = MaxHeight)

    @Deprecated("Use new settings-based API")
    public val SaneDialogInnerModifier: Modifier = ContentSettings.buildModifier()
}
