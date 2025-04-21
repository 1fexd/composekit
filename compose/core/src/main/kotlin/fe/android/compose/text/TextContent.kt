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
public interface TextContent {
    public val key: Any
    public val content: @Composable () -> Unit
}

@Immutable
public class DefaultContent private constructor(text: String) : TextContent {
    override val key: String = text
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current
        Text(text = text, overflow = textOptions.overflow, maxLines = textOptions.maxLines)
    }

    public companion object {
        public fun text(text: String): DefaultContent {
            return DefaultContent(text)
        }

        public fun textOrNull(text: String?): DefaultContent? {
            return if (text != null) DefaultContent(text) else null
        }
    }
}

@Immutable
public class StringResourceContent private constructor(@StringRes id: Int, vararg formatArgs: Any) : TextContent {
    override val key: Int = id
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current
        Text(
            text = stringResource(id = id, formatArgs = formatArgs),
            overflow = textOptions.overflow,
            maxLines = LocalTextOptions.current.maxLines
        )
    }

    public companion object {
        public fun textContent(@StringRes id: Int, vararg formatArgs: Any): StringResourceContent {
            return StringResourceContent(id, *formatArgs)
        }
    }
}


@Immutable
public class AnnotatedStringContent(annotatedString: AnnotatedString) : TextContent {
    override val key: String = annotatedString.text
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current
        Text(text = annotatedString, overflow = textOptions.overflow, maxLines = textOptions.maxLines)
    }

    public companion object {
        public inline fun buildAnnotatedTextContent(builder: AnnotatedString.Builder.() -> Unit): AnnotatedStringContent {
            return AnnotatedStringContent(buildAnnotatedString(builder))
        }

        public val AnnotatedString.content: AnnotatedStringContent
            get() = AnnotatedStringContent(this)
    }
}

@Immutable
public class AnnotatedStringResourceContent private constructor(@StringRes id: Int, vararg formatArgs: Any) : TextContent {
    override val key: Int = id
    override val content: @Composable () -> Unit = {
        val textOptions = LocalTextOptions.current

        Text(
            text = createAnnotatedString(id = id, *formatArgs),
            overflow = textOptions.overflow,
            maxLines = textOptions.maxLines
        )
    }

    public companion object {
        public fun annotatedStringResource(@StringRes id: Int, vararg formatArgs: Any): AnnotatedStringResourceContent {
            return AnnotatedStringResourceContent(id, *formatArgs)
        }
    }
}


@Immutable
public class ComposableTextContent(
    override val content: @Composable () -> Unit,
    override val key: Any = Unit
) : TextContent {

    public companion object {
        public fun content(content: @Composable () -> Unit): ComposableTextContent {
            return ComposableTextContent(content)
        }
    }
}
