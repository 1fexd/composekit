package fe.composekit.component.list.item

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import fe.composekit.component.CommonDefaults
import fe.android.compose.icon.IconPainter

@Composable
public fun ListItemFilledIconButton(
    iconPainter: IconPainter,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    val painter = iconPainter.rememberPainter()

    Box(modifier = CommonDefaults.BaseContentModifier, contentAlignment = Alignment.Center) {
        FilledTonalIconButton(onClick = onClick) {
            Icon(
                painter = painter,
                contentDescription = contentDescription
            )
        }
    }
}
