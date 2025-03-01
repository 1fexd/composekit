package fe.android.span.helper.formatter

import android.util.Log
//import fe.android.span.helper.BuildConfig

internal class InterceptingAppendable(
    private val inputLen: Int,
    private val stringBuilder: StringBuilder = StringBuilder()
) : Appendable by stringBuilder {
    private var length: Int = 0

    private fun trackAppend(text: String): StringBuilder {
        length += text.length
        return stringBuilder.append(text)
    }

    private val formatChar = mutableMapOf<Int, Int>()
    private val adjustments = mutableMapOf<IntRange, IntRange>()

    override fun append(csq: CharSequence?): Appendable {
        if (csq == null) return this

//        if (BuildConfig.DEBUG) {
//            Log.d("AppendIntercept", "append(csq=$csq)")
//        }

        val start = length
        val end = start + csq.length

        val diff = end - inputLen
        if (diff != 0) {
            val original = start..inputLen
            val replaced = start..end

            adjustments[original] = replaced
        }

        formatChar[end] = start
        return trackAppend(csq.toString())
    }

    override fun append(csq: CharSequence?, start: Int, end: Int): Appendable {
        if (csq == null) return this

//        if (BuildConfig.DEBUG) {
//            Log.d("AppendIntercept", "append(csq=$csq, start=$start, end=$end)")
//        }

        if (start > 0) {
            // We might be skipping a format char, check its length
            // append(csq=foo)
            // append(csq=%sbar, start=2, end=5)
            val diff = length - start
            if (diff != 0) {
                // Replaced format char has different length than format char itself
                val formatStart = formatChar[length]
                if (formatStart != null) {
                    val original = formatStart..length - diff
                    val replaced = formatStart..length

                    adjustments[original] = replaced
                }
            }
        }

        return trackAppend(csq.substring(start, end))
    }

    override fun append(c: Char): Appendable {
        return stringBuilder.append(c)
    }

    override fun toString(): String {
        return stringBuilder.toString()
    }

    fun get(): Pair<String, Map<IntRange, IntRange>> {
        return toString() to adjustments
    }
}
