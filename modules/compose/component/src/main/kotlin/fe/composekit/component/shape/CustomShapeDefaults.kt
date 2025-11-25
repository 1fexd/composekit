package fe.composekit.component.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

public object CustomShapeDefaults {
    // TODO: Use shape defaults or our own? Can we provide our own via LocalComposition or MaterialTheme?
    private val ShapeLarge = 24.dp //    ShapeDefaults.Large
    private val ShapeSmall = 8.dp

    public val SingleShape: RoundedCornerShape = RoundedCornerShape(
        size = ShapeLarge
    )

    public val TopShape: RoundedCornerShape = RoundedCornerShape(
        topStart = ShapeLarge,
        topEnd = ShapeLarge,
        bottomStart = ShapeSmall,
        bottomEnd = ShapeSmall
    )

    public val MiddleShape: RoundedCornerShape = RoundedCornerShape(
        size = ShapeSmall
    )

    public val BottomShape: RoundedCornerShape = RoundedCornerShape(
        topStart = ShapeSmall,
        topEnd = ShapeSmall,
        bottomEnd = ShapeLarge,
        bottomStart = ShapeLarge
    )
}
