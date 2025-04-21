package fe.composekit.component.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fe.composekit.component.CommonDefaults

@Composable
public fun AppIconImage(
    modifier: Modifier = CommonDefaults.BaseContentModifier,
    size: Dp = 32.dp,
    bitmap: ImageBitmap,
    label: String,
) {
    Image(
        bitmap = bitmap,
        contentDescription = label,
        modifier = modifier.size(size)
    )
}
