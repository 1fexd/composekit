package fe.composekit.bundle

import android.os.Bundle
import android.os.Parcelable


public fun Bundle.put(key: String?, value: Any?) {
    when (value) {
        null -> putString(key, null)
        is Byte -> putByte(key, value)
        is Char -> putChar(key, value)
        is Short -> putShort(key, value)
        is Boolean -> putBoolean(key, value)
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
        is Double -> putDouble(key, value)
        is String -> putString(key, value)
        is CharSequence -> putCharSequence(key, value)
        is BooleanArray -> putBooleanArray(key, value)
        is ByteArray -> putByteArray(key, value)
        is CharArray -> putCharArray(key, value)
        is ShortArray -> putShortArray(key, value)
        is IntArray -> putIntArray(key, value)
        is FloatArray -> putFloatArray(key, value)
        is LongArray -> putLongArray(key, value)
        is DoubleArray -> putDoubleArray(key, value)
        is Parcelable -> putParcelable(key, value)
        is Array<*> -> {
            if (value.isArrayOf<String>()) {
                putStringArray(key, value as Array<String?>)
            }
            if(value.isArrayOf<Parcelable>()) {
                putParcelableArray(key, value as Array<Parcelable?>)
            }
            if(value.isArrayOf<CharSequence>()) {
                putCharSequenceArray(key, value as Array<CharSequence?>)
            }
        }
        is ArrayList<*> -> {
            if(value.isArrayListOf<Int>()) {
                putIntegerArrayList(key, value as ArrayList<Int?>)
            }
            if(value.isArrayListOf<String>()) {
                putStringArrayList(key, value as ArrayList<String?>)
            }
            if(value.isArrayListOf<Parcelable>()) {
                putParcelableArrayList(key, value as ArrayList<Parcelable?>)
            }
            if(value.isArrayListOf<CharSequence>()) {
                putCharSequenceArrayList(key, value as ArrayList<CharSequence?>)
            }
        }

        else -> throw IllegalArgumentException("Unsupported type " + value.javaClass)
    }
}
@Suppress("REIFIED_TYPE_PARAMETER_NO_INLINE")
public fun <reified T : Any> ArrayList<*>.isArrayListOf(): Boolean =
    T::class.java.isAssignableFrom(this::class.java.componentType)
