package fe.android.compose.dialog.helper.base

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow

@Stable
public abstract class BaseResultDialogState<R : Any>(
    initial: DialogState = DialogState.Closed,
    result: R? = null
) : BaseDialogState(initial) {
    private val resultState = MutableStateFlow(result)

    internal val result: R?
        get() = resultState.value

    public open fun dismiss(): Boolean {
        return closeInternal(null)
    }

    public fun close(result: R): Boolean {
        return closeInternal(result)
    }

    private fun closeInternal(result: R?): Boolean {
        if (!isOpen) return false
        resultState.value = result

        return tryClose()
    }
}
