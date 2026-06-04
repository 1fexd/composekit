package fe.composekit.flag

public interface IntFlags {
    public val value: Int
    public val inv: Int
        get() = value.inv()

    public operator fun contains(flag: Int): Boolean {
        return (value and flag) != 0
    }

    public fun Int.remove(): Int {
        return this.and(inv)
    }
}

public interface IntFlagCompanion<T : IntFlags> {
    public val new: (Int) -> T

    public fun select(vararg flags: T): T {
        val sum = flags.sumOf { it.value }
        return new(sum)
    }

    public fun T.inv(): T {
        return new(this@inv.inv)
    }
}
