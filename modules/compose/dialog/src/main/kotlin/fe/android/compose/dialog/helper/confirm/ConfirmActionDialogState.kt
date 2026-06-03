package fe.android.compose.dialog.helper.confirm

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import fe.android.compose.dialog.helper.ComposeSaver
import fe.android.compose.dialog.helper.base.BaseResultDialogState
import fe.android.compose.dialog.helper.base.DialogState
import fe.android.compose.dialog.helper.createSaver

@Stable
public class ConfirmActionDialogState<T : Any> internal constructor(
    data: T?
) : BaseResultDialogState<Boolean>(DialogState.Closed, null) {

    public constructor() : this(null)

    private val dataState = mutableStateOf(data)

    internal val data: T
        get() = dataState.value!!

    public fun tryGetResult(): Pair<T, Boolean>? {
        if (isOpen || result == null) return null
        return data to result!!
    }

    override fun dismiss(): Boolean {
        return super.close(false)
    }

    public fun confirm() {
        super.close(true)
    }

    public fun open(data: T): Boolean {
        if (isOpen) return false
        dataState.value = data

        return super.tryOpen()
    }

    public companion object {
        public fun <T : Any> Saver(): ComposeSaver<ConfirmActionDialogState<T>, T> {
            return createSaver(
                save = { it.dataState.value },
                restore = { ConfirmActionDialogState(it) }
            )
        }
    }
}

