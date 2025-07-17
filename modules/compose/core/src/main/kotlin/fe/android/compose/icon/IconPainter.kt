package fe.android.compose.icon

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.core.graphics.drawable.toBitmap

@Stable
public interface IconPainter {
    @Composable
    public fun rememberPainter(): Painter
}

@JvmInline
@Stable
public value class ImageVectorIconPainter private constructor(private val imageVector: ImageVector) : IconPainter {
    @Composable
    override fun rememberPainter(): Painter {
        return rememberVectorPainter(imageVector)
    }

    public companion object {
        public fun imageVector(imageVector: ImageVector): ImageVectorIconPainter {
            return ImageVectorIconPainter(imageVector)
        }
    }
}

@Stable
public val ImageVector.iconPainter: IconPainter
    get() = ImageVectorIconPainter.imageVector(this)


@JvmInline
@Stable
public value class DrawableIconPainter private constructor(@param:DrawableRes private val id: Int) : IconPainter {

    @Composable
    override fun rememberPainter(): Painter {
        return painterResource(id)
    }

    public companion object {
        public fun drawable(@DrawableRes id: Int): DrawableIconPainter {
            return DrawableIconPainter(id)
        }
    }
}

@JvmInline
@Stable
public value class BitmapIconPainter private constructor(private val bitmap: ImageBitmap) : IconPainter {

    @Composable
    override fun rememberPainter(): Painter {
        return remember(bitmap) { BitmapPainter(bitmap) }
    }

    public companion object {
        public fun bitmap(bitmap: ImageBitmap): BitmapIconPainter {
            return BitmapIconPainter(bitmap)
        }

        public fun drawable(drawable: Drawable): BitmapIconPainter {
            val bitmap = drawable.toBitmap().asImageBitmap()
            return BitmapIconPainter(bitmap)
        }
    }
}
