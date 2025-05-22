package fe.android.preference.helper.testapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import fe.composekit.preference.collectAsStateWithLifecycle


@Composable
fun TestComposablePage(viewModel: TestViewModel) {
    val newTestInt2 by viewModel.newTestInt2.collectAsStateWithLifecycle()
//        var counter by remember { preferenceRepository.asState(TestPreferenceDefinition.counter) }

    Column {
        Text(text = "${newTestInt2}")

        Row {
            Button(onClick = { viewModel.newTestInt2(newTestInt2 - 1) }) {
                Text(text = "-")
            }

            Button(onClick = { viewModel.newTestInt2(newTestInt2 + 1) }) {
                Text(text = "+")
            }
        }
    }
}

@Composable
fun TestComposablePage2(viewModel: TestViewModel2) {
    val newTestInt2 by viewModel.newTestInt2.collectAsStateWithLifecycle()
//        var counter by remember { preferenceRepository.asState(TestPreferenceDefinition.counter) }

    Column {
        Text(text = "$newTestInt2")

        Row {
            Button(onClick = { viewModel.newTestInt2(newTestInt2 - 1) }) {
                Text(text = "-")
            }

            Button(onClick = { viewModel.newTestInt2(newTestInt2 + 1) }) {
                Text(text = "+")
            }
        }
    }
}
