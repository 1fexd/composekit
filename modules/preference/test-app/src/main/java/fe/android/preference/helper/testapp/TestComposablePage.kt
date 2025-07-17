package fe.android.preference.helper.testapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import fe.composekit.preference.collectAsStateWithLifecycle

@Composable
fun TestComposablePageWrapper(viewModel: TestViewModel) {
    var toggle by remember { mutableStateOf(true) }
    Row {
        Button(onClick = { toggle = !toggle }) {
            Text(text = "Toggle")
        }
        if (toggle) {
            TestComposablePage(viewModel = viewModel)
        } else {
            Text(text = "Disabled")
        }
    }
}

@Composable
fun TestComposablePage(viewModel: TestViewModel) {
    val newTestInt by viewModel.newTestInt.collectAsStateWithLifecycle()
    LaunchedEffect(newTestInt) {
        Log.d("TestComposablePage", "$newTestInt")
    }

    Column {
        Text(text = "${newTestInt}")

        Row {
            Button(onClick = { viewModel.newTestInt(newTestInt - 1) }) {
                Text(text = "-")
            }

            Button(onClick = { viewModel.newTestInt(newTestInt + 1) }) {
                Text(text = "+")
            }
        }
    }
}

@Composable
fun TestComposablePage2Wrapper(viewModel: TestViewModel2) {
    var toggle by remember { mutableStateOf(true) }
    Row {
        Button(onClick = { toggle = !toggle }) {
            Text(text = "Toggle")
        }
        if (toggle) {
            TestComposablePage2(viewModel = viewModel)
        } else {
            Text(text = "Disabled")
        }
    }
}

@Composable
fun TestComposablePage2(viewModel: TestViewModel2) {
    val newTestInt by viewModel.newTestInt.collectAsStateWithLifecycle()
    LaunchedEffect(newTestInt) {
        Log.d("TestComposablePage2", "$newTestInt")
    }

    Column {
        Text(text = "$newTestInt")

        Row {
            Button(onClick = { viewModel.newTestInt(newTestInt - 1) }) {
                Text(text = "-")
            }

            Button(onClick = { viewModel.newTestInt(newTestInt + 1) }) {
                Text(text = "+")
            }
        }
    }
}
