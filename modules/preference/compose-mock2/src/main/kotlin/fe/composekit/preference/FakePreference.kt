package fe.composekit.preference

import fe.android.preference.helper.AbstractPreferenceDefinition
import fe.android.preference.helper.Preference
import fe.android.preference.helper.TypeMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlin.reflect.KClass


public class FakePreference<Type : Any, NullableType, Preference : Pref<Type, NullableType>>(
    private val preference: Preference,
    private var value: NullableType,
    private val coroutineScope: CoroutineScope = MainScope(),
) {
   public val vm: ViewModelStatePreference<Type, NullableType, Preference> by lazy {
       ViewModelStatePreference(
           preference,
           get = { value },
           put = { _, newValue -> value = newValue },
           coroutineScope
       )
   }
}

public object FakePreferences : AbstractPreferenceDefinition() {
    public fun boolean(
        value: Boolean,
        key: String = "mock_preference"
    ): FakePreference<Boolean, Boolean, Preference.Default<Boolean>> {
        return FakePreference(
            boolean(key, value),
            value
        )
    }

    public fun int(
        value: Int,
        key: String = "mock_preference"
    ): FakePreference<Int, Int, Preference.Default<Int>> {
        return FakePreference(
            int(key, value),
            value
        )
    }

    public fun long(
        value: Long,
        key: String = "mock_preference"
    ): FakePreference<Long, Long, Preference.Default<Long>> {
        return FakePreference(
            long(key, value),
            value
        )
    }

    public fun string(
        value: String?,
        key: String = "mock_nullable_preference"
    ): FakePreference<String, String?, Preference.Nullable<String>> {
        return FakePreference(
            string(key, value),
            value
        )
    }

    internal inline fun <reified T : Any, reified M : Any> mapped(
        default: T,
        mapper: TypeMapper<T, M>,
        key: String = "mock_mapped_preference"
    ): FakePreference<T, T, Preference.Mapped<T, M>> {
        return FakePreference(
            `access$mapped`(key, default, mapper, T::class, M::class),
           default
        )
    }

    @PublishedApi
    internal fun <T : Any, M : Any> `access$mapped`(
        key: String,
        default: T,
        mapper: TypeMapper<T, M>,
        t: KClass<T>,
        m: KClass<M>
    ): Preference.Mapped<T, M> = mapped(key, default, mapper, t, m)
}
