package fe.composekit.preference

import android.content.Context
import fe.android.preference.helper.Preference
import fe.android.preference.helper.PreferenceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


public open class FlowPreferenceRepository(
    context: Context,
    fileName: String = "preferences",
    private val cache: FlowStateCache = FlowStateCache()
) : PreferenceRepository(context, fileName) {

    public fun <T : Any> asViewModelState(
        preference: Preference.Mapped<T, String>,
        scope: CoroutineScope,
    ): ViewModelStatePreference<T, T, Preference.Mapped<T, String>> {
        val state = cache.get(preference.key)
        if (state != null) {
            @Suppress("UNCHECKED_CAST")
            return state as ViewModelStatePreference<T, T, Preference.Mapped<T, String>>
        }

        val mutableStateFlow = MutableStateFlow<T>(preference.default)
        scope.launch(Dispatchers.IO) { mutableStateFlow.emit(get(preference)) }

        val flow = mutableStateFlow.asStateFlow()
        val newState = ViewModelStatePreference<T, T, Preference.Mapped<T, String>>(flow) { newValue ->
            scope.launch { mutableStateFlow.emit(newValue) }
            scope.launch(Dispatchers.IO) {
                put(preference, newValue)
            }
        }

        cache.put(preference.key, newState)
        return newState
    }

    public fun asViewModelState(
        preference: Preference.Boolean,
        scope: CoroutineScope
    ): ViewModelStatePreference<Boolean, Boolean, Preference.Default<Boolean>> {
        val state = cache.get(preference.key)
        if (state != null) {
            @Suppress("UNCHECKED_CAST")
            return state as ViewModelStatePreference<Boolean, Boolean, Preference.Default<Boolean>>
        }

        val mutableStateFlow = MutableStateFlow<Boolean>(preference.default)
        scope.launch(Dispatchers.IO) { mutableStateFlow.emit(get(preference)) }

        val flow = mutableStateFlow.asStateFlow()
        val newState = ViewModelStatePreference<Boolean, Boolean, Preference.Default<Boolean>>(flow) { newValue ->
            scope.launch { mutableStateFlow.emit(newValue) }
            scope.launch(Dispatchers.IO) {
                put(preference, newValue)
            }
        }

        cache.put(preference.key, newState)
        return newState
    }

    public fun asViewModelState(
        preference: Preference.Int,
        scope: CoroutineScope
    ): ViewModelStatePreference<Int, Int, Preference.Int> {
        val state = cache.get(preference.key)
        if (state != null) {
            @Suppress("UNCHECKED_CAST")
            return state as ViewModelStatePreference<Int, Int, Preference.Int>
        }

        val mutableStateFlow = MutableStateFlow<Int>(preference.default)
        scope.launch(Dispatchers.IO) { mutableStateFlow.emit(get(preference)) }

        val flow = mutableStateFlow.asStateFlow()
        val newState = ViewModelStatePreference<Int, Int, Preference.Int>(flow) { newValue ->
            scope.launch { mutableStateFlow.emit(newValue) }
            scope.launch(Dispatchers.IO) {
                put(preference, newValue)
            }
        }

        cache.put(preference.key, newState)
        return newState
    }
}

