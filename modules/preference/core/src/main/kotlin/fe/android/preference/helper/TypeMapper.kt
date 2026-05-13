package fe.android.preference.helper

public typealias Unmapper<Storable, T> = (Storable) -> T?
public typealias Mapper<T, Storable> = (T) -> Storable

// Maps a non-primitive type T to a "primitive" type (String, Int, Long, Boolean) Storable which can be stored
public interface TypeMapper<T, Storable> {
    public val unmap: Unmapper<Storable, T>
    public val map: Mapper<T, Storable>
}

public abstract class OptionTypeMapper<T, Storable>(key: (T) -> Storable, options: () -> Array<T>) : TypeMapper<T, Storable> {
    private val readerOptions by lazy {
        options().associateBy { key(it) }
    }

    override val unmap: Unmapper<Storable, T> = { readerOptions[it] }
    override val map: Mapper<T, Storable> = key
}

public abstract class EnumTypeMapper<T : Enum<T>>(values: Array<T>) : TypeMapper<T, Int> {
    override val unmap: Unmapper<Int, T> = { values[it] }
    override val map: Mapper<T, Int> = { it.ordinal }
}
