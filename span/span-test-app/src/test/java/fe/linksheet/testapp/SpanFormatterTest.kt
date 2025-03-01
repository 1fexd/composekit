package fe.linksheet.testapp

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.SpannedString
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.toSpanned
import androidx.test.core.app.ApplicationProvider
import fe.android.span.helper.formatter.SpanFormatter
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import androidx.test.ext.junit.runners.AndroidJUnit4
import fe.android.span.helper.composable.createAnnotatedString
import fe.android.span.helper.ext.format
import fe.androidspanhelper.testapp.R
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.N], manifest = Config.NONE)
class SpanFormatterTest {

    @Test
    fun test2() {
//        val spanned = SpannedString("""This format string links to <annotation url="https://google.com">%s</annotation>""")
//
//        val y = spanned
//        val x = spanned.toSpanned()
//
//        val z = x.format("google.com")
//        val zz = z



    }
}
