package fe.composekit.testapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.composekit.testapp.page.dialog.ConfirmActionDialogTest
import fe.composekit.testapp.page.dialog.InputResultDialogTest
import fe.composekit.testapp.page.dialog.ResultDialogTest
import fe.composekit.testapp.page.dialog.StatefulDialogTest

@Composable
fun DialogPage() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Text(text = "Stateful dialog test") }
        item { StatefulDialogTest() }
        item { HorizontalDivider() }
        item { Text(text = "Result dialog test") }
        item { ResultDialogTest() }
        item { HorizontalDivider() }
        item { Text(text = "Input result dialog test") }
        item { InputResultDialogTest() }
        item { HorizontalDivider() }
        item { Text(text = "Confirm action test") }
        item { ConfirmActionDialogTest() }
    }
}

@Preview(showBackground = true)
@Composable
private fun DialogPagePreview() {
    DialogPage()
}
