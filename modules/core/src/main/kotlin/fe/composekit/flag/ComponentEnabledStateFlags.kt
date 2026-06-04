package fe.composekit.flag

import android.content.pm.PackageManager

@JvmInline
public value class ComponentEnabledStateFlags(override val value: Long) : LongFlags {
    public constructor(value: Int) : this(value.toLong())

    public companion object : LongFlagCompanion<ComponentEnabledStateFlags> {
        public val EMPTY: ComponentEnabledStateFlags = ComponentEnabledStateFlags(0)

        public val COMPONENT_ENABLED_STATE_DEFAULT: ComponentEnabledStateFlags = ComponentEnabledStateFlags(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT)
        public val COMPONENT_ENABLED_STATE_ENABLED: ComponentEnabledStateFlags = ComponentEnabledStateFlags(PackageManager.COMPONENT_ENABLED_STATE_ENABLED)
        public val COMPONENT_ENABLED_STATE_DISABLED: ComponentEnabledStateFlags = ComponentEnabledStateFlags(PackageManager.COMPONENT_ENABLED_STATE_DISABLED)
        public val COMPONENT_ENABLED_STATE_DISABLED_USER: ComponentEnabledStateFlags = ComponentEnabledStateFlags(PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER)
        public val COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED: ComponentEnabledStateFlags = ComponentEnabledStateFlags(PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED)

        override val new: (Long) -> ComponentEnabledStateFlags = { ComponentEnabledStateFlags(it) }
    }
}
