package fe.android.compose.dialog.helper.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
public fun <I : Any, R : Any> InputResultDialog(
    state: InputResultDialogState<I, R>,
    onClose: (R) -> Unit,
    onDismiss: (() -> Unit)? = null,
    content: @Composable (I) -> Unit,
) {
    var wasEverOpen by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = state.isOpen) {
        if (!wasEverOpen || state.isOpen) return@LaunchedEffect

        val result = state.result
        if (result != null) {
            onClose(result)
        } else {
            onDismiss?.invoke()
        }
    }

    if (state.isOpen) {
        wasEverOpen = true
        content(state.data)
    }
}

@Deprecated("Use renamed method", replaceWith = ReplaceWith("rememberInputResultDialogState<I, R>()"))
@Composable
public fun <I : Any, R : Any> rememberInputResultDialog(): InputResultDialogState<I, R> {
    return rememberInputResultDialogState()
}

@Composable
public fun <I : Any, R : Any> rememberInputResultDialogState(): InputResultDialogState<I, R> {
    return rememberSaveable(saver = InputResultDialogState.Saver()) { InputResultDialogState() }
}
