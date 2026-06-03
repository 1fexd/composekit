package fe.android.preference.helper

import android.content.SharedPreferences

@DslMarker
public annotation class EditorScopeDsl

@EditorScopeDsl
public sealed class PreferenceEditor {
    @JvmName("putMappedToString")
    @OptIn(UnsafePreferenceInteraction::class)
    public fun <T : Any> put(preference: Preference.Mapped<T, String>, newState: T) {
        unsafePut(preference.key, preference.map(newState))
    }

    @OptIn(UnsafePreferenceInteraction::class)
    public fun put(preference: Preference.Nullable<String>, newState: String?) {
        unsafePut(preference.key, newState)
    }

    @JvmName("putMappedToBoolean")
    @OptIn(UnsafePreferenceInteraction::class)
    public fun <T : Any> put(preference: Preference.Mapped<T, Boolean>, newState: T) {
        unsafePut(preference.key, preference.map(newState))
    }

    @OptIn(UnsafePreferenceInteraction::class)
    public fun put(preference: Preference.Default<Boolean>, newState: Boolean) {
        unsafePut(preference.key, newState)
    }

    @JvmName("putMappedToLong")
    @OptIn(UnsafePreferenceInteraction::class)
    public fun <T : Any> put(preference: Preference.Mapped<T, Long>, newState: T) {
        unsafePut(preference.key, preference.map(newState))
    }

    @OptIn(UnsafePreferenceInteraction::class)
    public fun put(preference: Preference.Default<Long>, newState: Long) {
        unsafePut(preference.key, newState)
    }

    @JvmName("putMappedToInt")
    @OptIn(UnsafePreferenceInteraction::class)
    public fun <T : Any> put(preference: Preference.Mapped<T, Int>, newState: T) {
        unsafePut(preference.key, preference.map(newState))
    }


    @OptIn(UnsafePreferenceInteraction::class)
    public fun put(preference: Preference.Default<Int>, newState: Int) {
        unsafePut(preference.key, newState)
    }

    /**
     * Unsafe writes/reads (do not do check type of Property before writing, use with caution!)
     */
    @UnsafePreferenceInteraction
    protected fun unsafePut(key: String, newState: String?) {
        return withEditor { putString(key, newState) }
    }

    @UnsafePreferenceInteraction
    protected fun unsafePut(key: String, newState: Int) {
        return withEditor { putInt(key, newState) }
    }

    @UnsafePreferenceInteraction
    protected fun unsafePut(key: String, newState: Long) {
        return withEditor { putLong(key, newState) }
    }

    @UnsafePreferenceInteraction
    protected fun unsafePut(key: String, newState: Boolean) {
        return withEditor { putBoolean(key, newState) }
    }

    @OptIn(UnsafePreferenceInteraction::class)
    public fun unsafePut(
        preference: Preference<*, *>,
        value: Any?
    ): Boolean {
        val resolvedClass = preference.resolvedClass
        if (value == null && resolvedClass != String::class) return false
        when (resolvedClass) {
            String::class -> {
                if(value == null) {
                    unsafePut(preference.key, null)
                } else {
                    if(value !is String) return false
                    unsafePut(preference.key, value)
                }
            }
            Boolean::class -> {
                if(value !is Boolean) return false
                unsafePut(preference.key, value)
            }
            Int::class -> {
                if (value !is Int) return false
                unsafePut(preference.key, value)
            }
            Long::class -> {
                if (value !is Long) return false
                unsafePut(preference.key, value)
            }
            else -> {
                return false
            }
        }
        return true
    }

    @OptIn(UnsafePreferenceInteraction::class)
    public fun setStringValueToPreference(preference: Preference<*, *>, value: String): Boolean {
        val any = preference.stringToAny(value) ?: return false
        if (preference is Preference.Mapped<*, *> && !preference.canUnmap(any)) return false

        return unsafePut(preference, any)
    }

    protected abstract fun withEditor(action: PreferenceEditAction)

    public class Scope(private val editor: SharedPreferences.Editor) : PreferenceEditor() {
        override fun withEditor(action: PreferenceEditAction) {
            editor.apply(action)
        }
    }
}



