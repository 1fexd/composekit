package fe.composekit.flag

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

@JvmInline
public value class ComponentEnabledFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<ComponentEnabledFlags> {
        public val EMPTY: ComponentEnabledFlags = ComponentEnabledFlags(0)

        public val DONT_KILL_APP: ComponentEnabledFlags = ComponentEnabledFlags(PackageManager.DONT_KILL_APP)
        @RequiresApi(Build.VERSION_CODES.R)
        public val SYNCHRONOUS: ComponentEnabledFlags = ComponentEnabledFlags(PackageManager.SYNCHRONOUS)

        override val new: (Long) -> ComponentEnabledFlags = { ComponentEnabledFlags(it) }
    }
}
