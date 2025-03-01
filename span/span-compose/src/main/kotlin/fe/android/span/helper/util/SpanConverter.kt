package fe.android.span.helper.util

import android.graphics.Typeface
import android.text.ParcelableSpan
import android.text.style.AbsoluteSizeSpan
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em


private val EmptySpanStyle = SpanStyle()
private val FontFamilyDefault = SpanStyle(fontFamily = FontFamily.Default)

private val FontFamilies = arrayOf(
    FontFamily.SansSerif,
    FontFamily.Serif,
    FontFamily.Monospace,
    FontFamily.Cursive
).associate { it.name to SpanStyle(fontFamily = it) }

private val TypefaceNormal = SpanStyle(fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal)
private val TypefaceBold = SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal)
private val TypefaceItalic = SpanStyle(fontWeight = FontWeight.Normal, fontStyle = FontStyle.Italic)
private val TypefaceBoldItalic = SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)

private val Typefaces = mapOf(
    Typeface.NORMAL to TypefaceNormal,
    Typeface.BOLD to TypefaceBold,
    Typeface.ITALIC to TypefaceItalic,
    Typeface.BOLD_ITALIC to TypefaceBoldItalic
)

private val TextDecorationLineThrough = SpanStyle(textDecoration = TextDecoration.LineThrough)
private val TextDecorationUnderline = SpanStyle(textDecoration = TextDecoration.Underline)
private val BaselineShiftSuperscript = SpanStyle(baselineShift = BaselineShift.Superscript)
private val BaselineShiftSubscript = SpanStyle(baselineShift = BaselineShift.Subscript)

public fun ParcelableSpan.toSpanStyle(density: Density): SpanStyle {
    return when (this) {
        is StyleSpan -> Typefaces[style] ?: EmptySpanStyle
        is TypefaceSpan -> FontFamilies[family] ?: FontFamilyDefault
        is BulletSpan -> EmptySpanStyle
        is StrikethroughSpan -> TextDecorationLineThrough
        is UnderlineSpan -> TextDecorationUnderline
        is SuperscriptSpan -> BaselineShiftSuperscript
        is SubscriptSpan -> BaselineShiftSubscript
        is ForegroundColorSpan -> SpanStyle(color = Color(foregroundColor))
        is RelativeSizeSpan -> SpanStyle(fontSize = sizeChange.em)
        is AbsoluteSizeSpan -> with(density) {
            SpanStyle(fontSize = if (dip) size.dp.toSp() else size.toSp())
        }

        else -> EmptySpanStyle
    }
}

