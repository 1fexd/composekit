package fe.android.span.helper

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.androidspanhelper.testapp.R

@Composable
fun SpanTextPreviewColumn() {
    CompositionLocalProvider(
        LocalLinkTags provides DefaultLinkTags(),
        LocalLinkAnnotationStyle provides DefaultHyperLinkStyle
    ) {
        Column(modifier = Modifier.padding(all = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            TestText(name = "test_string", id = R.string.test_string)
            TestText(name = "test_string_format", id = R.string.test_string_format, "Regular hello!")
            TestText(
                name = "test_string_format_annotated",
                id = R.string.test_string_format_annotated,
                "Bold hello!"
            )
            TestText(
                name = "test_string_format_url",
                id = R.string.test_string_format_url,
                "google.com"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpanTextPreview() {
    MaterialTheme {
        SpanTextPreviewColumn()
    }
}

@Composable
private fun TestText(name: String, @StringRes id: Int, vararg formatArgs: Any?) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(text = name, fontWeight = FontWeight.Bold)
//        Text(text = createAnnotatedString(id = id, *formatArgs))
    }
}
