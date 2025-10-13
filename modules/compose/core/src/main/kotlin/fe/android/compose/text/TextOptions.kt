package fe.android.compose.text

import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Immutable
public data class TextOptions(
    val maxLines: Int = Int.MAX_VALUE,
    val overflow: TextOverflow = TextOverflow.Clip,
    val style: TextStyle? = null
)

public val DefaultTextOptions: TextOptions = TextOptions()
public val LocalTextOptions: ProvidableCompositionLocal<TextOptions> = compositionLocalOf { DefaultTextOptions }

public typealias OptionalTextContent = TextContent?

@Composable
public fun ProvideTextOptions(
    textOptions: TextOptions? = null,
    content: @Composable () -> Unit,
) {
    val options = textOptions ?: LocalTextOptions.current
    CompositionLocalProvider(LocalTextOptions provides options, content = content)
}

@Composable
public fun TextContent(
    textContent: TextContent,
    textOptions: TextOptions? = null
) {
    ProvideTextOptions(
        textOptions = textOptions,
        content = textContent.content
    )
}
