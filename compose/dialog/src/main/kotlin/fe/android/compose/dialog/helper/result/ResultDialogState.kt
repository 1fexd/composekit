package fe.android.compose.dialog.helper.result

import androidx.compose.runtime.Stable
import fe.android.compose.dialog.helper.ComposeSaver
import fe.android.compose.dialog.helper.createSaver
import fe.android.compose.dialog.helper.base.DialogState
import fe.android.compose.dialog.helper.base.BaseResultDialogState


@Stable
public class ResultDialogState<R : Any>(
    result: R? = null,
) : BaseResultDialogState<R>(DialogState.Closed, result) {

    public fun open(): Boolean {
        return super.tryOpen()
    }

    public companion object {
        @Suppress("FunctionName")
        public fun <R : Any> Saver(): ComposeSaver<ResultDialogState<R>, R> {
            return createSaver(
                save = { it.result },
                restore = { ResultDialogState(it) }
            )
        }
    }
}
