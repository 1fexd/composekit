package fe.android.compose.dialog.helper.base

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf


@Stable
public abstract class BaseDialogState(initial: DialogState = DialogState.Closed) {
    private val dialogState = mutableStateOf(initial)

    internal fun tryOpen(): Boolean {
        return updateState(DialogState.Open)
    }

    internal fun tryClose(): Boolean {
        return updateState(DialogState.Closed)
    }

    private fun updateState(newState: DialogState): Boolean {
        if (dialogState.value == newState) return false
        dialogState.value = newState

        return true
    }

    public val currentState: DialogState
        get() = dialogState.value

    public val isOpen: Boolean
        get() = currentState == DialogState.Open
}


