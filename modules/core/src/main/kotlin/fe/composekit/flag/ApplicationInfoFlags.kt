package fe.composekit.flag

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

@JvmInline
public value class ApplicationInfoFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<ApplicationInfoFlags> {
        public val EMPTY: ApplicationInfoFlags = ApplicationInfoFlags(0)

        public val GET_META_DATA: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.GET_META_DATA)
        public val GET_SHARED_LIBRARY_FILES: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.GET_SHARED_LIBRARY_FILES)
        public val MATCH_UNINSTALLED_PACKAGES: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES)
        public val MATCH_SYSTEM_ONLY: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.MATCH_SYSTEM_ONLY)
        public val MATCH_DEBUG_TRIAGED_MISSING: ApplicationInfoFlags = ApplicationInfoFlags(0x10000000)
        public val MATCH_DISABLED_COMPONENTS: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.MATCH_DISABLED_COMPONENTS)
        public val MATCH_DISABLED_UNTIL_USED_COMPONENTS: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS)
        public val MATCH_INSTANT: ApplicationInfoFlags = ApplicationInfoFlags(0x00800000)
        public val MATCH_STATIC_SHARED_AND_SDK_LIBRARIES: ApplicationInfoFlags = ApplicationInfoFlags(0x04000000)
        @Suppress("DEPRECATION")
        public val GET_DISABLED_UNTIL_USED_COMPONENTS: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.GET_DISABLED_UNTIL_USED_COMPONENTS)
        @Suppress("DEPRECATION")
        public val GET_UNINSTALLED_PACKAGES: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.GET_UNINSTALLED_PACKAGES)
        public val MATCH_HIDDEN_UNTIL_INSTALLED_COMPONENTS: ApplicationInfoFlags = ApplicationInfoFlags(0x20000000)
        @RequiresApi(Build.VERSION_CODES.Q)
        public val MATCH_APEX: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.MATCH_APEX)
        // 1L << 32
//        public val MATCH_ARCHIVED_PACKAGES: ApplicationInfoFlags = ApplicationInfoFlags(PackageManager.MATCH_ARCHIVED_PACKAGES)

        override val new: (Long) -> ApplicationInfoFlags = { ApplicationInfoFlags(it) }
    }
}
