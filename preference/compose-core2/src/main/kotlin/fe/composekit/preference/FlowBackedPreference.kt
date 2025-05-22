package fe.composekit.preference

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fe.android.preference.helper.Preference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


public typealias Pref<Type, NullableType> = Preference<Type, NullableType>

public class ViewModelStatePreference<Type : Any, NullableType, Preference : Pref<Type, NullableType>>(
    private val preference: Preference,
    private val get: Get<Preference, NullableType>,
    private val put: Put<Preference, NullableType>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : (NullableType) -> Unit {
    private val scope: CoroutineScope = CoroutineScope(dispatcher)

    private val mutableStateFlow: MutableStateFlow<NullableType> = MutableStateFlow(preference.default)
    public val stateFlow: StateFlow<NullableType> = mutableStateFlow.asStateFlow()

    public val value: NullableType
        get() = stateFlow.value

//    public operator fun invoke(): NullableType {
//        return value
//    }

    public fun reload() {
        scope.launch { mutableStateFlow.emit(get(preference)) }
    }

    private suspend fun _update(newValue: NullableType) {
        mutableStateFlow.emit(newValue)
        put(preference, newValue)
    }

    override fun invoke(newValue: NullableType) {
        scope.launch { _update(newValue) }
    }

    @Deprecated(message = "Replace with invoke", replaceWith = ReplaceWith("this(newValue)"))
    public fun update(newValue: NullableType) {
        invoke(newValue)
    }
}

@Composable
public fun <Type : Any, NullableType, Preference : Pref<Type, NullableType>> ViewModelStatePreference<Type, NullableType, Preference>.collectAsStateWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext
): State<NullableType> {
    val state = stateFlow.collectAsStateWithLifecycle(lifecycleOwner, minActiveState, context)
    return state
}

@Composable
public fun <Type : Any, NullableType, Preference : Pref<Type, NullableType>> ViewModelStatePreference<Type, NullableType, Preference>.collectAsStateWithLifecycle(
    initialValue: NullableType,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext
): State<NullableType> {
    val state = stateFlow.collectAsStateWithLifecycle(initialValue, lifecycleOwner, minActiveState, context)
    return state
}
