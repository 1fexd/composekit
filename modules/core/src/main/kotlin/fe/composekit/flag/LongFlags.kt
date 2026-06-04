package fe.composekit.flag

public interface LongFlags {
    public val value: Long
    public val inv: Long
        get() = value.inv()

    public operator fun contains(flag: Long): Boolean {
        return (value and flag) != 0L
    }

    public operator fun contains(flag: Int): Boolean {
        return (value.toInt() and flag) != 0
    }

    public fun Long.remove(): Long {
        return this.and(inv)
    }

    public fun Int.remove(): Int {
        return this.and(inv.toInt())
    }
}

public interface LongFlagCompanion<T : LongFlags> {
    public val new: (Long) -> T

    public fun select(vararg flags: T): T {
        val sum = flags.sumOf { it.value }
        return new(sum)
    }

    public fun T.inv(): T {
        return new(this@inv.inv)
    }
}
