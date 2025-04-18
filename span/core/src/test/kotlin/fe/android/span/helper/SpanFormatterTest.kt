package fe.android.span.helper

import android.text.Html
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.test.ext.junit.runners.AndroidJUnit4
import fe.android.span.helper.formatter.SpanFormatter
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class SpanFormatterTest {

    private fun compareHtml(
        input: SpannableStringBuilder.() -> Unit,
        vararg args: Any?,
        expected: SpannableStringBuilder.() -> Unit
    ) {
        val result = Html.toHtml(SpanFormatter.format(buildSpannedString(input), *args), Html.FROM_HTML_MODE_LEGACY)
        val ex = Html.toHtml(buildSpannedString(expected), Html.FROM_HTML_MODE_LEGACY)

        assertEquals(ex, result)
    }

    private fun SpannableStringBuilder.bold(text: String): SpannableStringBuilder {
        return bold { append(text) }
    }

    @Test
    fun test() {
        compareHtml(
            input = { append("%s") },
            "henlo",
            expected = { append("henlo") }
        )

        compareHtml(
            input = { bold("hello world") },
            expected = { bold("hello world") }
        )

        compareHtml(
            input = { bold("%s") },
            "world",
            expected = { bold("world") }
        )

        compareHtml(
            input = { bold("%s").append("bar") },
            "foo",
            expected = { bold("foo").append("bar") }
        )

        compareHtml(
            input = { bold("foo").append("%s") },
            "bar",
            expected = { bold("foo").append("bar") }
        )
    }
}
