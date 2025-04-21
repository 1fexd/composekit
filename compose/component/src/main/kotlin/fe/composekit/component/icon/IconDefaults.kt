package fe.composekit.component.icon

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public object IconDefaults {
    public val IconSize: Dp = 24.0.dp
    public val ContainerSize: Dp = 40.0.dp
}

public data class IconOffset(val x: Dp = 0.dp, val y: Dp = 0.dp) {
    val modifier: Modifier by lazy { Modifier.offset(x = x, y = y) }
}
