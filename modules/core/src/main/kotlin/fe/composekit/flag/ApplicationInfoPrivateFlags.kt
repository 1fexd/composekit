package fe.composekit.flag

import android.content.pm.ApplicationInfo

@JvmInline
public value class ApplicationInfoPrivateFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<ApplicationInfoPrivateFlags> {
        public val SYSTEM: ApplicationInfoPrivateFlags = ApplicationInfoPrivateFlags(ApplicationInfo.FLAG_SYSTEM)
        public val UPDATED_SYSTEM_APP: ApplicationInfoPrivateFlags = ApplicationInfoPrivateFlags(ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)

        override val new: (Long) -> ApplicationInfoPrivateFlags = { ApplicationInfoPrivateFlags(it) }
    }
}


