package fe.composekit.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier

object CommonDefaults {
    val EmptyPadding = PaddingValues()

    val BaseModifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
    val BaseContentModifier = Modifier.fillMaxHeight()
}
