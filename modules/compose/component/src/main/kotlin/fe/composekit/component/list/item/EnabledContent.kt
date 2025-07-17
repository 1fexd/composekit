package fe.composekit.component.list.item

import java.util.*

public typealias EnabledContentSet = EnumSet<EnabledContent>

public enum class EnabledContent {
    Primary, Main;

    public val set: EnabledContentSet by lazy {
        EnabledContentSet.of(this)
    }

    public companion object {
        public val all: EnabledContentSet by lazy {
            EnabledContentSet.allOf(EnabledContent::class.java)
        }

        public val none: EnabledContentSet by lazy {
            EnabledContentSet.noneOf(EnabledContent::class.java)
        }
    }
}

public fun Boolean.toEnabledContentSet(): EnabledContentSet {
    return if (this) EnabledContent.all else EnabledContent.none
}
