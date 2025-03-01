package fe.android.compose.text

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import fe.android.span.helper.composable.createAnnotatedString


@Immutable
interface TextContent {
    val key: Any
    val content: @Composable () -> Unit
}

@Immutable
class DefaultContent private constructor(text: String) : TextContent {
    override val key = text
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current
        Text(text = text, overflow = textOptions.overflow, maxLines = textOptions.maxLines)
    }

    companion object {
        fun text(text: String): DefaultContent {
            return DefaultContent(text)
        }

        fun textOrNull(text: String?): DefaultContent? {
            return if (text != null) DefaultContent(text) else null
        }
    }
}

@Immutable
class StringResourceContent private constructor(@StringRes id: Int, vararg formatArgs: Any) : TextContent {
    override val key = id
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current
        Text(
            text = stringResource(id = id, formatArgs = formatArgs),
            overflow = textOptions.overflow,
            maxLines = LocalTextOptions.current.maxLines
        )
    }

    companion object {
        fun textContent(@StringRes id: Int, vararg formatArgs: Any): StringResourceContent {
            return StringResourceContent(id, *formatArgs)
        }
    }
}


@Immutable
class AnnotatedStringContent(annotatedString: AnnotatedString) : TextContent {
    override val key = annotatedString.text
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current
        Text(text = annotatedString, overflow = textOptions.overflow, maxLines = textOptions.maxLines)
    }

    companion object {
        inline fun buildAnnotatedTextContent(builder: AnnotatedString.Builder.() -> Unit): AnnotatedStringContent {
            return AnnotatedStringContent(buildAnnotatedString(builder))
        }

        val AnnotatedString.content: AnnotatedStringContent
            get() = AnnotatedStringContent(this)
    }
}

@Immutable
class AnnotatedStringResourceContent private constructor(@StringRes id: Int, vararg formatArgs: Any) : TextContent {
    override val key = id
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current

        Text(
            text = createAnnotatedString(id = id, *formatArgs),
            overflow = textOptions.overflow,
            maxLines = textOptions.maxLines
        )
    }

    companion object {
        fun annotatedStringResource(@StringRes id: Int, vararg formatArgs: Any): AnnotatedStringResourceContent {
            return AnnotatedStringResourceContent(id, *formatArgs)
        }
    }
}


@Immutable
class ComposableTextContent(
    override val content: @Composable () -> Unit,
    override val key: Any = Unit
) : TextContent {

    companion object {
        fun content(content: @Composable () -> Unit): ComposableTextContent {
            return ComposableTextContent(content)
        }
    }
}
