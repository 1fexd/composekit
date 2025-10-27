package fe.composekit.preference

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fe.android.preference.helper.Preference
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public typealias Pref<Type, NullableType> = Preference<Type, NullableType>
public typealias BooleanVmPref = ViewModelStatePreference<Boolean, Boolean, Preference.Default<Boolean>>

public class ViewModelStatePreference<Type : Any, NullableType, Preference : Pref<Type, NullableType>>(
    private val preference: Preference,
    private val get: Get<Preference, NullableType>,
    private val put: Put<Preference, NullableType>,
    private val coroutineScope: CoroutineScope,
    private val context: CoroutineContext = Dispatchers.IO + CoroutineName("VMStatePref"),
) : (NullableType) -> Unit {

    private val mutableStateFlow: MutableStateFlow<NullableType> = MutableStateFlow(preference.default)
    public val stateFlow: StateFlow<NullableType> = mutableStateFlow.asStateFlow()

    public val value: NullableType
        get() = stateFlow.value

    private suspend fun reloadInternal() = withContext(context) {
        val value = get(preference)
        mutableStateFlow.emit(value)
    }

    private suspend fun updateInternal(newValue: NullableType) = withContext(context) {
        mutableStateFlow.emit(newValue)
        put(preference, newValue)
    }

    public fun reload() {
        coroutineScope.launch { reloadInternal() }
    }

    override fun invoke(newValue: NullableType) {
        coroutineScope.launch { updateInternal(newValue) }
    }

    @Deprecated(message = "Replace with invoke", replaceWith = ReplaceWith("this(newValue)"))
    public fun update(newValue: NullableType) {
        invoke(newValue)
    }
}

public fun threadName(): String {
    return Thread.currentThread().name
}

@Composable
public fun <Type : Any, NullableType, Preference : Pref<Type, NullableType>> ViewModelStatePreference<Type, NullableType, Preference>.collectAsStateWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext,
): State<NullableType> {
    val state = stateFlow.collectAsStateWithLifecycle(lifecycleOwner, minActiveState, context)
    return state
}

@Composable
public fun <Type : Any, NullableType, Preference : Pref<Type, NullableType>> ViewModelStatePreference<Type, NullableType, Preference>.collectAsStateWithLifecycle(
    initialValue: NullableType,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext,
): State<NullableType> {
    val state = stateFlow.collectAsStateWithLifecycle(initialValue, lifecycleOwner, minActiveState, context)
    return state
}
