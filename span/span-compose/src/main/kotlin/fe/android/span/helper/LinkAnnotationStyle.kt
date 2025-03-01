package fe.android.span.helper

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration


@Immutable
public data class LinkAnnotationStyle(
    val style: SpanStyle,
    val focusedStyle: SpanStyle? = null,
    val hoveredStyle: SpanStyle? = null,
    val pressedStyle: SpanStyle? = null,
    val interactionListener: LinkInteractionListener? = null
) {
    public fun toUrlAnnotation(value: String): LinkAnnotation.Url {
        return LinkAnnotation.Url(value, TextLinkStyles(style, focusedStyle, hoveredStyle, pressedStyle), interactionListener)
    }
}

public val DefaultHyperLinkStyle: LinkAnnotationStyle = LinkAnnotationStyle(
    style = SpanStyle(color = Color(0xff3b82f6), textDecoration = TextDecoration.Underline),
)


public val LocalLinkAnnotationStyle: ProvidableCompositionLocal<LinkAnnotationStyle> = compositionLocalOf {
    DefaultHyperLinkStyle
}
