package fe.composekit.testapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fe.android.compose.text.DefaultContent.Companion.text
import fe.composekit.component.appbar.SearchTopAppBar


@Composable
fun SearchTopAppBarPage(){
    SearchTopAppBar(
        titleContent = text("Hello"),
        placeholderContent = text("World"),
        query = "Test",
        onQueryChange =  {

        },
        onBackPressed = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchTopAppBarPagePreview() {
    SearchTopAppBarPage()
}
