package fe.android.compose.dialog.helper.stateful

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import fe.android.compose.dialog.helper.ComposeSaver
import fe.android.compose.dialog.helper.base.BaseResultDialogState
import fe.android.compose.dialog.helper.base.DialogState
import fe.android.compose.dialog.helper.createSaver

@Stable
public class StatefulDialogState<T : Any, R : Any>(
    private val defaultData: T,
    result: R? = null,
) : BaseResultDialogState<R>(DialogState.Closed, result) {
    private val dataState = mutableStateOf(defaultData)

    internal val data: T
        get() = dataState.value

    public fun tryGetResult(): Pair<T, R?>? {
        if (isOpen) return null
        return data to result
    }

    public fun open(data: T = defaultData): Boolean {
        if (isOpen) return false
        dataState.value = data

        return super.tryOpen()
    }

    public companion object {
        public fun <T : Any, R : Any> Saver(): ComposeSaver<StatefulDialogState<T, R>, T> {
            return createSaver(
                save = { it.dataState.value },
                restore = { StatefulDialogState(it) }
            )
        }
    }
}
