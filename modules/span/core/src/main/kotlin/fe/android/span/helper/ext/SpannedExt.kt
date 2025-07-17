package fe.android.span.helper.ext

import android.text.ParcelableSpan
import android.text.Spanned
import fe.android.span.helper.formatter.SpanFormatter

public fun Spanned.format(vararg args: Any?): Spanned {
    return SpanFormatter.format(this, *args)
}

public fun <T> Spanned.getSpanRange(tag: T): IntRange {
    return getSpanStart(tag)..getSpanEnd(tag)
}

public fun Spanned.getAllParcelableSpans(start: Int = 0, end: Int = length): Map<ParcelableSpan, IntRange> {
    return getAllSpans<ParcelableSpan>(start, end)
}

public inline fun <reified T> Spanned.getAllSpans(start: Int = 0, end: Int = length): Map<T, IntRange> {
    val spans = getSpans(start, end, T::class.java)

    val destination = mutableMapOf<T, IntRange>()
    for (span in spans) destination[span] = getSpanRange(span)

    return destination
}

public inline fun Spanned.forEachSpan(start: Int = 0, end: Int = length, fn: (ParcelableSpan, IntRange) -> Unit) {
    for ((span, range) in getAllParcelableSpans(start, end)) fn(span, range)
}
