package fe.android.span.helper.formatter

import android.text.SpannableStringBuilder
import android.text.Spanned
import androidx.core.text.set
import androidx.core.text.toSpanned
import fe.android.span.helper.ext.getAllParcelableSpans
import java.util.*

public object SpanFormatter {
    public fun format(spanned: Spanned, vararg args: Any?): Spanned {
        if (args.isEmpty()) return spanned

        val text = spanned.toString()
        val interceptor = InterceptingAppendable(text.length)
        Formatter(interceptor).use { it.format(text, *args) }

        val (newString, adjustments) = interceptor.get()

        val builder = SpannableStringBuilder(newString)

        for ((span, pos) in spanned.getAllParcelableSpans()) {
            val newPos = adjustments[pos] ?: pos
            builder[newPos.first, newPos.last] = span
        }

        return builder.toSpanned()
    }
}
