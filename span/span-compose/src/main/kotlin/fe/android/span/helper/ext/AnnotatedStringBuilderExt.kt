package fe.android.span.helper.ext

import android.text.Annotation
import android.text.ParcelableSpan
import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Density
import fe.android.span.helper.LinkAnnotationStyle
import fe.android.span.helper.LinkTags
import fe.android.span.helper.util.toSpanStyle

public fun AnnotatedString.Builder.handleSpanned(
    spanned: Spanned,
    density: Density,
    style: LinkAnnotationStyle,
    tags: LinkTags,
): AnnotatedString.Builder {
    append(spanned)

    for ((span, range) in spanned.getAllParcelableSpans()) {
        putSpan(span, density, style, tags, range.first, range.last)
    }

    return this
}

public fun AnnotatedString.Builder.putSpan(
    span: ParcelableSpan,
    density: Density,
    style: LinkAnnotationStyle,
    tags: LinkTags,
    start: Int,
    end: Int,
) {
    if (span !is Annotation) {
        addStyle(span.toSpanStyle(density), start, end)
        return
    }

    val link = when (span.key) {
        tags.urlAnnotationKey -> span.value
        tags.urlIdAnnotationKey -> tags.urlIds[span.value] ?: error("No link found for url id: ${span.value}")
        else -> return
    }

    addLink(style.toUrlAnnotation(link), start, end)
}
