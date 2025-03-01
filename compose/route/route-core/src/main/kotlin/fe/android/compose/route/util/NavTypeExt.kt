package fe.android.compose.route.util

import android.os.Parcelable
import androidx.navigation.NavType
import java.io.Serializable
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
fun inferFromKClass(value: KClass<*>?): NavType<Any> {
    return when {
        value == Int::class -> NavType.IntType as NavType<Any>
        value == IntArray::class -> NavType.IntArrayType as NavType<Any>
        value == Long::class -> NavType.LongType as NavType<Any>
        value == LongArray::class -> NavType.LongArrayType as NavType<Any>
        value == Float::class -> NavType.FloatType as NavType<Any>
        value == FloatArray::class -> NavType.FloatArrayType as NavType<Any>
        value == Boolean::class -> NavType.BoolType as NavType<Any>
        value == BooleanArray::class -> NavType.BoolArrayType as NavType<Any>
        value == String::class || value == null -> NavType.StringType as NavType<Any>
        value == Array<String>::class -> NavType.StringArrayType as NavType<Any>
        value.java.isArray &&
                Parcelable::class.java.isAssignableFrom(value.java.componentType!!) -> {
            NavType.ParcelableArrayType(
                value.java.componentType as Class<Parcelable>
            ) as NavType<Any>
        }

        value.java.isArray &&
                Serializable::class.java.isAssignableFrom(value.java.componentType!!) -> {
            NavType.SerializableArrayType(
                value.java.componentType as Class<Serializable>
            ) as NavType<Any>
        }

        value == Parcelable::class -> NavType.ParcelableType(value.java) as NavType<Any>
        value == Enum::class -> NavType.EnumType((value as Enum<*>).javaClass) as NavType<Any>
        value == Serializable::class -> NavType.SerializableType((value as Serializable).javaClass) as NavType<Any>
        else -> {
            throw IllegalArgumentException(
                "Object of type ${value.java.name} is not supported for navigation " +
                        "arguments."
            )
        }
    }
}
