package fe.composekit.component.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.Dp
import fe.android.compose.extension.thenIf
import fe.android.compose.icon.IconPainter

@Composable
public fun FilledIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconSize: Dp = IconDefaults.IconSize,
    iconOffset: IconOffset? = null,
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
        Box(modifier = Modifier.size(containerSize), contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier
                    .thenIf(iconOffset != null) { iconOffset!!.modifier }
                    .size(iconSize)
                    .then(modifier),
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
