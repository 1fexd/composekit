package fe.composekit.flag

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

@JvmInline
public value class ResolveInfoFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<ResolveInfoFlags> {
        public val EMPTY: ResolveInfoFlags = ResolveInfoFlags(0)

        public val GET_META_DATA: ResolveInfoFlags = ResolveInfoFlags(PackageManager.GET_META_DATA)
        public val GET_RESOLVED_FILTER: ResolveInfoFlags = ResolveInfoFlags(PackageManager.GET_RESOLVED_FILTER)
        public val GET_SHARED_LIBRARY_FILES: ResolveInfoFlags = ResolveInfoFlags(PackageManager.GET_SHARED_LIBRARY_FILES)
        public val MATCH_ALL: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_ALL)

        public val MATCH_DISABLED_COMPONENTS: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_DISABLED_COMPONENTS)
        public val MATCH_DISABLED_UNTIL_USED_COMPONENTS: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS)
        public val MATCH_DEFAULT_ONLY: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_DEFAULT_ONLY)

        @RequiresApi(Build.VERSION_CODES.Q)
        public val MATCH_DIRECT_BOOT_AUTO: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_DIRECT_BOOT_AUTO)
        public val MATCH_DIRECT_BOOT_AWARE: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_DIRECT_BOOT_AWARE)
        public val MATCH_DIRECT_BOOT_UNAWARE: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_DIRECT_BOOT_UNAWARE)
        public val MATCH_SYSTEM_ONLY: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_SYSTEM_ONLY)
        public val MATCH_UNINSTALLED_PACKAGES: ResolveInfoFlags = ResolveInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES)

        override val new: (Long) -> ResolveInfoFlags = { ResolveInfoFlags(it) }
    }
}
