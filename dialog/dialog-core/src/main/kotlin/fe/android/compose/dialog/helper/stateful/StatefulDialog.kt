package fe.android.compose.dialog.helper.stateful

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun <T : Any, R : Any> StatefulDialog(
    state: StatefulDialogState<T, R>,
    onClose: (T, R) -> Unit,
    onDismiss: ((T) -> Unit)? = null,
    content: @Composable (T) -> Unit,
) {
    var wasEverOpen by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = state.isOpen) {
        if (!wasEverOpen) return@LaunchedEffect
        val (data, result) = state.tryGetResult() ?: return@LaunchedEffect
        if (result != null) {
            onClose(data, result)
        } else {
            onDismiss?.invoke(data)
        }
    }

    if (state.isOpen) {
        wasEverOpen = true
        content(state.data)
    }
}

@Composable
fun <T : Any, R : Any> rememberStatefulDialog(data: T): StatefulDialogState<T, R> {
    return rememberSaveable(saver = StatefulDialogState.Saver()) { StatefulDialogState(data) }
}
