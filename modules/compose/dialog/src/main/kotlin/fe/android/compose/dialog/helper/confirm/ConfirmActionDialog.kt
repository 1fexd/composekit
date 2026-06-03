package fe.android.compose.dialog.helper.confirm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
public fun <T : Any> ConfirmActionDialog(
    state: ConfirmActionDialogState<T>,
    onConfirm: (T) -> Unit,
    onDismiss: ((T) -> Unit)? = null,
    content: @Composable (T) -> Unit,
) {
    var wasEverOpen by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = state.isOpen) {
        if (!wasEverOpen) return@LaunchedEffect
        val (data, result) = state.tryGetResult() ?: return@LaunchedEffect
        if (result) {
            onConfirm(data)
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
public fun <T : Any> rememberConfirmActionDialog(): ConfirmActionDialogState<T> {
    return rememberSaveable(saver = ConfirmActionDialogState.Saver()) { ConfirmActionDialogState() }
}
