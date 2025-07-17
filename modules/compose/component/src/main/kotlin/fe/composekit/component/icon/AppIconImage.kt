package fe.composekit.component.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fe.android.compose.icon.IconPainter
import fe.composekit.component.CommonDefaults

@Composable
public fun AppIconImage(
    modifier: Modifier = CommonDefaults.BaseContentModifier,
    size: Dp = 32.dp,
    icon: IconPainter,
    label: String,
) {
    Image(
        painter = icon.rememberPainter(),
        contentDescription = label,
        modifier = modifier.size(size)
    )
}
