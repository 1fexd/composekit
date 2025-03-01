package fe.android.compose.icon

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource

@Stable
interface IconPainter {
    @Composable
    fun rememberPainter(): Painter
}

@JvmInline
@Stable
value class ImageVectorIconPainter private constructor(private val imageVector: ImageVector) : IconPainter {
    @Composable
    override fun rememberPainter(): Painter = rememberVectorPainter(imageVector)

    companion object {
        fun imageVector(imageVector: ImageVector): ImageVectorIconPainter {
            return ImageVectorIconPainter(imageVector)
        }
    }
}

@Stable
val ImageVector.iconPainter: IconPainter
    get() = ImageVectorIconPainter.imageVector(this)


@JvmInline
@Stable
value class DrawableIconPainter private constructor(@DrawableRes private val id: Int) : IconPainter {

    @Composable
    override fun rememberPainter(): Painter = painterResource(id)

    companion object {
        fun drawable(@DrawableRes id: Int): DrawableIconPainter {
            return DrawableIconPainter(id)
        }
    }
}

@JvmInline
@Stable
value class BitmapIconPainter private constructor(private val bitmap: ImageBitmap) : IconPainter {

    @Composable
    override fun rememberPainter(): Painter {
        return remember(bitmap) { BitmapPainter(bitmap) }
    }

    companion object {
        fun bitmap(bitmap: ImageBitmap): BitmapIconPainter {
            return BitmapIconPainter(bitmap)
        }
    }
}
