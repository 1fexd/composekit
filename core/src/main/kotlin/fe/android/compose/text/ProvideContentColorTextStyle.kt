package fe.android.compose.text

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
@Deprecated(message = "Migrate to new API")
fun ProvideContentColorTextStyle(
    contentColor: Color,
    textStyle: TextStyle,
    textOptions: TextOptions? = null,
    content: @Composable () -> Unit,
) {
    val mergedStyle = LocalTextStyle.current.merge(textStyle)

    ProvideTextOptions(textOptions = textOptions) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides mergedStyle,
            content = content
        )
    }
}

@Composable
fun ProvideContentColorOptionsStyleText(
    contentColor: Color,
    textOptions: TextOptions? = null,
    content: @Composable () -> Unit,
) {
    val mergedStyle = LocalTextStyle.current.merge(textOptions?.style)
    val options = textOptions ?: LocalTextOptions.current

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedStyle,
        LocalTextOptions provides options,
        content = content
    )
}

