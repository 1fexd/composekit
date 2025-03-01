package fe.composekit.component.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object CustomShapeDefaults {
    // TODO: Use shape defaults or our own? Can we provide our own via LocalComposition or MaterialTheme?
    private val ShapeLarge = 20.dp //    ShapeDefaults.Large
    private val ShapeSmall = 4.dp

    val SingleShape = RoundedCornerShape(ShapeLarge)

    val TopShape = RoundedCornerShape(
        topStart = ShapeLarge,
        topEnd = ShapeLarge,
        bottomStart = ShapeSmall,
        bottomEnd = ShapeSmall
    )

    val MiddleShape = RoundedCornerShape(ShapeSmall)

    val BottomShape = RoundedCornerShape(
        topStart = ShapeSmall,
        topEnd = ShapeSmall,
        bottomEnd = ShapeLarge,
        bottomStart = ShapeLarge
    )
}
