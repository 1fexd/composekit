package fe.composekit.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.android.compose.text.DefaultContent.Companion.text
import fe.composekit.component.list.column.SaneLazyColumnDefaults
import fe.android.compose.text.TextContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    titleContent: TextContent,
    placeholderContent: TextContent,
    query: String,
    onQueryChange: (String?) -> Unit,
    onBackPressed: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Column(
        modifier = Modifier.padding(bottom = SaneLazyColumnDefaults.BottomSpacing),
        verticalArrangement = Arrangement.spacedBy((-1).dp)
    ) {
        TopAppBar(
            title = titleContent.content,
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            scrollBehavior = scrollBehavior
        )

        DockedSearchBar(
            modifier = Modifier.padding(horizontal = SaneLazyColumnDefaults.HorizontalSpacing),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = {},
                    placeholder = placeholderContent.content,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { onQueryChange(null) }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            },
            expanded = false,
            onExpandedChange = {},
            content = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchTopAppBarPreview() {
    SearchTopAppBar(
        titleContent = text("Hello"),
        placeholderContent = text("World"),
        query = "Test",
        onQueryChange =  {

        },
        onBackPressed = {},
    )
}
