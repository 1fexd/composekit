package fe.android.compose.dialog.helper.stateful

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
public fun <T : Any, R : Any> StatefulDialog(
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
public fun <T : Any, R : Any> rememberStatefulDialog(data: T): StatefulDialogState<T, R> {
    return rememberSaveable(saver = StatefulDialogState.Saver()) { StatefulDialogState(data) }
}
