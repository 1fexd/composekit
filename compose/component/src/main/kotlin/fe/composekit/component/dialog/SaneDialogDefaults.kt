package fe.composekit.component.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

public object SaneDialogDefaults {
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

    public val SaneDialogContentModifier: Modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 280.dp)

    public val SaneDialogInnerModifier: Modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
}
