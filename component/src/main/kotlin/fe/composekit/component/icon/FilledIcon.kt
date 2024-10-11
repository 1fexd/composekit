package fe.composekit.component.icon

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fe.android.compose.icon.IconPainter
import fe.android.compose.icon.iconPainter

object IconDefaults {
    val IconSize = 24.0.dp
    val ContainerSize = 40.0.dp
}

@Composable
fun FilledIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconSize: Dp = IconDefaults.IconSize,
    containerSize: Dp = IconDefaults.ContainerSize,
    shape: Shape = IconButtonDefaults.filledShape,
    colors: IconButtonColors = IconButtonDefaults.filledIconButtonColors(),
    icon: IconPainter,
    contentDescription: String?,
) {
    val painter = icon.rememberPainter()

    Surface(
        shape = shape,
        color = colors.containerColor(enabled),
        contentColor = colors.contentColor(enabled),
    ) {
        val alignment: Alignment = Alignment.Center
        Box(modifier = Modifier.size(containerSize), contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier.size(iconSize).then(modifier)
                    .border(1.dp, Color.Red)
                ,
                painter = painter,
                contentDescription = contentDescription
            )
        }
    }
}

@Stable
internal fun IconButtonColors.containerColor(enabled: Boolean): Color =
    if (enabled) containerColor else disabledContainerColor

@Stable
internal fun IconButtonColors.contentColor(enabled: Boolean): Color =
    if (enabled) contentColor else disabledContentColor
