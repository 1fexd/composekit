package fe.composekit.preference

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fe.android.preference.helper.Preference
import kotlinx.coroutines.flow.StateFlow


public class ViewModelStatePreference<T : Any, NT, P : Preference<T, NT>>(
    private val flow: StateFlow<NT>,
    private val update: (NT) -> Unit,
) : (NT) -> Unit by update {

    @Composable
    public fun collectAsStateWithLifecycle(): NT {
        val state = flow.collectAsStateWithLifecycle()
        return state.value
    }

    public val value: NT
        get() = flow.value

    public operator fun invoke(): NT {
        return value
    }

    @Deprecated(message = "Replace with invoke", replaceWith = ReplaceWith("this(newValue)"))
    public fun update(newValue: NT) {
        invoke(newValue)
    }
}

