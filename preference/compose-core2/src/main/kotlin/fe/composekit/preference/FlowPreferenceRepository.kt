package fe.composekit.preference

import android.content.Context
import fe.android.preference.helper.Preference
import fe.android.preference.helper.PreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

public typealias Put<P, NT> = (P, NT) -> Unit
public typealias Get<P, NT> = (P) -> NT

public open class FlowPreferenceRepository(
    context: Context,
    fileName: String = "preferences",
    public val cache: FlowStateCache = FlowStateCache(),
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
) : PreferenceRepository(context, fileName) {
    private val coroutineScope = CoroutineScope(dispatcher + SupervisorJob() + CoroutineName("FlowPreferenceRepository"))

    private fun <T : Any, NT, P : Preference<T, NT>, M : ViewModelStatePreference<T, NT, P>> createCachedState(
        preference: P,
        put: Put<P, NT>,
        get: Get<P, NT>,
    ): M {
        val state = cache.get(preference.key)
        if (state != null) {
            @Suppress("UNCHECKED_CAST") return state as M
        }

        val newState = ViewModelStatePreference(preference, get, put, coroutineScope)
        newState.reload()

        cache.put(preference.key, newState)
        @Suppress("UNCHECKED_CAST") return newState as M
    }

    @JvmName("asStringState")
    public fun asViewModelState(preference: Preference.Nullable<String>): ViewModelStatePreference<String, String?, Preference.Nullable<String>> {
        return createCachedState(preference, ::put, ::get)
    }

    @JvmName("asMappedBooleanState")
    public fun <T : Any> asViewModelState(preference: Preference.Mapped<T, Boolean>): ViewModelStatePreference<T, T, Preference.Mapped<T, Boolean>> {
        return createCachedState(preference, ::put, ::get)
    }

    @JvmName("asMappedStringState")
    public fun <T : Any> asViewModelState(preference: Preference.Mapped<T, String>): ViewModelStatePreference<T, T, Preference.Mapped<T, String>> {
        return createCachedState(preference, ::put, ::get)
    }

    @JvmName("asMappedIntState")
    public fun <T : Any> asViewModelState(preference: Preference.Mapped<T, Int>): ViewModelStatePreference<T, T, Preference.Mapped<T, Int>> {
        return createCachedState(preference, ::put, ::get)
    }

    @JvmName("asMappedLongState")
    public fun <T : Any> asViewModelState(preference: Preference.Mapped<T, Long>): ViewModelStatePreference<T, T, Preference.Mapped<T, Long>> {
        return createCachedState(preference, ::put, ::get)
    }

    @JvmName("asBooleanState")
    public fun asViewModelState(preference: Preference.Default<Boolean>): ViewModelStatePreference<Boolean, Boolean, Preference.Default<Boolean>> {
        return createCachedState(preference, ::put, ::get)
    }

    @JvmName("asIntState")
    public fun asViewModelState(preference: Preference.Default<Int>): ViewModelStatePreference<Int, Int, Preference.Default<Int>> {
        return createCachedState(preference, ::put, ::get)
    }

    @JvmName("asLongState")
    public fun asViewModelState(preference: Preference.Default<Long>): ViewModelStatePreference<Long, Long, Preference.Default<Long>> {
        return createCachedState(preference, ::put, ::get)
    }
}


@JvmName("asBooleanFunction")
public fun FlowPreferenceRepository.asFunction(
    preference: Preference.Default<Boolean>,
): () -> Boolean {
    val state = asViewModelState(preference)
    return state::value
}

@JvmName("asIntFunction")
public fun FlowPreferenceRepository.asFunction(preference: Preference.Default<Int>): () -> Int {
    val state = asViewModelState(preference)
    return state::value
}

@JvmName("asLongFunction")
public fun FlowPreferenceRepository.asFunction(preference: Preference.Default<Long>): () -> Long {
    val state = asViewModelState(preference)
    return state::value
}

@JvmName("asStringFunction")
public fun FlowPreferenceRepository.asFunction(preference: Preference.Nullable<String>): () -> String? {
    val state = asViewModelState(preference)
    return state::value
}

@JvmName("asMappedStringFunction")
public fun <T : Any> FlowPreferenceRepository.asFunction(
    preference: Preference.Mapped<T, String>,
): () -> T {
    val state = asViewModelState(preference)
    return state::value
}

@JvmName("asMappedIntFunction")
public fun <T : Any> FlowPreferenceRepository.asFunction(
    preference: Preference.Mapped<T, Int>,
): () -> T {
    val state = asViewModelState(preference)
    return state::value
}

@JvmName("asMappedLongFunction")
public fun <T : Any> FlowPreferenceRepository.asFunction(
    preference: Preference.Mapped<T, Long>,
): () -> T {
    val state = asViewModelState(preference)
    return state::value
}

@JvmName("asMappedBooleanFunction")
public fun <T : Any> FlowPreferenceRepository.asFunction(
    preference: Preference.Mapped<T, Boolean>,
): () -> T {
    val state = asViewModelState(preference)
    return state::value
}

