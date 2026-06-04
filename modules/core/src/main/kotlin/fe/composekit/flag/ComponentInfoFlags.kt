package fe.composekit.flag

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

@JvmInline
public value class ComponentInfoFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<ComponentInfoFlags> {
        public val EMPTY: ComponentInfoFlags = ComponentInfoFlags(0)

        public val GET_META_DATA: ComponentInfoFlags = ComponentInfoFlags(PackageManager.GET_META_DATA)
        public val GET_SHARED_LIBRARY_FILES: ComponentInfoFlags = ComponentInfoFlags(PackageManager.GET_SHARED_LIBRARY_FILES)
        public val MATCH_ALL: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_ALL)
        public val MATCH_DEFAULT_ONLY: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_DEFAULT_ONLY)
        public val MATCH_DISABLED_COMPONENTS: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_DISABLED_COMPONENTS)
        public val MATCH_DISABLED_UNTIL_USED_COMPONENTS: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS)

        @RequiresApi(Build.VERSION_CODES.Q)
        public val MATCH_DIRECT_BOOT_AUTO: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_DIRECT_BOOT_AUTO)
        public val MATCH_DIRECT_BOOT_AWARE: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_DIRECT_BOOT_AWARE)
        public val MATCH_DIRECT_BOOT_UNAWARE: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_DIRECT_BOOT_UNAWARE)
        public val MATCH_SYSTEM_ONLY: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_SYSTEM_ONLY)
        public val MATCH_UNINSTALLED_PACKAGES: ComponentInfoFlags = ComponentInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES)

        override val new: (Long) -> ComponentInfoFlags = { ComponentInfoFlags(it) }
    }
}
