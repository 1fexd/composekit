package fe.composekit.component.icon

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object IconDefaults {
    val IconSize = 24.0.dp
    val ContainerSize = 40.0.dp
}

data class IconOffset(val x: Dp = 0.dp, val y: Dp = 0.dp) {
    val modifier by lazy { Modifier.offset(x = x, y = y) }
}
