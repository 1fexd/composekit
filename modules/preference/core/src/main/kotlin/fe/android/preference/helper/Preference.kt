package fe.android.preference.helper

import kotlin.reflect.KClass

public sealed class Preference<T : Any, NT> protected constructor(
    public val key: String,
    public val default: NT,
    @Suppress("MemberVisibilityCanBePrivate")
    public val clazz: KClass<T>
) {
    internal open val def: Any? = default
    internal open val type: KClass<*> = clazz

    public class Nullable<T : Any> internal constructor(
        key: String,
        default: T?,
        clazz: KClass<T>
    ) : Preference<T, T?>(key, default, clazz)

    public open class Default<T : Any> internal constructor(
        key: String,
        default: T,
        clazz: KClass<T>
    ) : Preference<T, T>(key, default, clazz)

    public class Boolean internal constructor(
        key: String,
        default: kotlin.Boolean
    ) : Default<kotlin.Boolean>(key, default, kotlin.Boolean::class)

    public class Int internal constructor(
        key: String,
        default: kotlin.Int
    ) : Default<kotlin.Int>(key, default, kotlin.Int::class)

    public class Long internal constructor(
        key: String,
        default: kotlin.Long
    ) : Default<kotlin.Long>(key, default, kotlin.Long::class)


    public class Mapped<T : Any, Storable : Any> internal constructor(
        key: String, default: T, private val mapper: TypeMapper<T, Storable>,
        clazz: KClass<T>, public val mappedClazz: KClass<Storable>,
    ) : Preference<T, T>(key, default, clazz) {
        public val defaultMapped: Storable = map(default)

        override val def: Storable = defaultMapped

        public fun canUnmap(mapped: Any): kotlin.Boolean {
            @Suppress("UNCHECKED_CAST")
            val storable = mapped as? Storable ?: return false
            return unmap(storable) != null
        }

        public fun unmap(mapped: Storable): T? = mapper.unmap(mapped)

        @Suppress("MemberVisibilityCanBePrivate")
        public fun map(value: T): Storable = mapper.map(value)
    }

    public class Init internal constructor(
        key: String, public val initial: () -> String
    ) : Preference<String, String?>(key, null, String::class)

//    public class Init<T : Any> internal constructor(
//        key: String, public val initial: () -> T, clazz: KClass<T>
//    ) : Preference<T, T?>(key, null, clazz)

    override fun equals(other: Any?): kotlin.Boolean {
        return (other != null && other::class == this::class) && (other as? Preference<*, *>)?.key == key
    }

    override fun hashCode(): kotlin.Int {
        return key.hashCode()
    }
}

public val Preference<*, *>.resolvedDefault: Any?
    get() = mappedOrNull?.defaultMapped ?: default

public val Preference<*, *>.resolvedClass: KClass<out Any>
    get() = mappedOrNull?.mappedClazz ?: clazz

public val Preference<*, *>.mappedOrNull: Preference.Mapped<*, *>?
    get() = this as? Preference.Mapped<*, *>

public fun Preference<*, *>.stringToAny(value: String): Any? {
    return when (resolvedClass) {
        String::class -> value
        Boolean::class -> value.toBooleanStrictOrNull()
        Int::class -> value.toIntOrNull()
        Long::class -> value.toLongOrNull()
        else -> null
    }
}
