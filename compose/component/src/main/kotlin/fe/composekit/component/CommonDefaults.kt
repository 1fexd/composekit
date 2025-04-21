package fe.composekit.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier

public object CommonDefaults {
    public val EmptyPadding: PaddingValues = PaddingValues()

    public val BaseModifier: Modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
    public val BaseContentModifier: Modifier = Modifier.fillMaxHeight()
}
