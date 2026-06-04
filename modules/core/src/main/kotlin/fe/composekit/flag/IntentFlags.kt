package fe.composekit.flag

import android.content.Intent

@JvmInline
public value class IntentFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<IntentFlags> {
        public val EMPTY: IntentFlags = IntentFlags(0)

        public val ACTIVITY_EXCLUDE_FROM_RECENTS: IntentFlags = IntentFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        public val ACTIVITY_FORWARD_RESULT: IntentFlags = IntentFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)

        override val new: (Long) -> IntentFlags = { IntentFlags(it) }
    }
}
