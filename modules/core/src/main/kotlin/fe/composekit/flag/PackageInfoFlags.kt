package fe.composekit.flag

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

@JvmInline
public value class PackageInfoFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<PackageInfoFlags> {
        public val EMPTY: PackageInfoFlags = PackageInfoFlags(0)

        public val GET_ACTIVITIES: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_ACTIVITIES)
        public val GET_CONFIGURATIONS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_CONFIGURATIONS)
        public val GET_GIDS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_GIDS)
        public val GET_INSTRUMENTATION: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_INSTRUMENTATION)
        public val GET_META_DATA: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_META_DATA)
        public val GET_PERMISSIONS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_PERMISSIONS)
        public val GET_PROVIDERS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_PROVIDERS)
        public val GET_RECEIVERS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_RECEIVERS)
        public val GET_SERVICES: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_SERVICES)
        public val GET_SHARED_LIBRARY_FILES: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_SHARED_LIBRARY_FILES)
        @Suppress("DEPRECATION")
        public val GET_SIGNATURES: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_SIGNATURES)
        @RequiresApi(Build.VERSION_CODES.P)
        public val GET_SIGNING_CERTIFICATES: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_SIGNING_CERTIFICATES)
        public val GET_URI_PERMISSION_PATTERNS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_URI_PERMISSION_PATTERNS)
        public val MATCH_UNINSTALLED_PACKAGES: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES)
        public val MATCH_DISABLED_COMPONENTS: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_DISABLED_COMPONENTS)
        public val MATCH_DISABLED_UNTIL_USED_COMPONENTS: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS)
        public val MATCH_SYSTEM_ONLY: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_SYSTEM_ONLY)
        @RequiresApi(Build.VERSION_CODES.Q)
        public val MATCH_APEX: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_APEX)
        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        public val MATCH_ARCHIVED_PACKAGES: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_ARCHIVED_PACKAGES)

        @Suppress("DEPRECATION")
        public val GET_DISABLED_COMPONENTS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_DISABLED_COMPONENTS)
        @Suppress("DEPRECATION")
        public val GET_DISABLED_UNTIL_USED_COMPONENTS: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_DISABLED_UNTIL_USED_COMPONENTS)
        @Suppress("DEPRECATION")
        public val GET_UNINSTALLED_PACKAGES: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_UNINSTALLED_PACKAGES)
        public val MATCH_DIRECT_BOOT_AWARE: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_DIRECT_BOOT_AWARE)
        public val MATCH_DIRECT_BOOT_UNAWARE: PackageInfoFlags = PackageInfoFlags(PackageManager.MATCH_DIRECT_BOOT_UNAWARE)
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        public val GET_ATTRIBUTIONS_LONG: PackageInfoFlags = PackageInfoFlags(PackageManager.GET_ATTRIBUTIONS_LONG)

        override val new: (Long) -> PackageInfoFlags = { PackageInfoFlags(it) }
    }
}
