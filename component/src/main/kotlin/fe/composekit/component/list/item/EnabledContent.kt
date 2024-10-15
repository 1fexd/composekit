package fe.composekit.component.list.item

import java.util.*

typealias EnabledContentSet = EnumSet<EnabledContent>

enum class EnabledContent {
    Primary, Main;

    val set: EnabledContentSet by lazy {
        EnabledContentSet.of(this)
    }

    companion object {
        val all: EnabledContentSet by lazy {
            EnabledContentSet.allOf(EnabledContent::class.java)
        }

        val none: EnabledContentSet by lazy {
            EnabledContentSet.noneOf(EnabledContent::class.java)
        }
    }
}

fun Boolean.toEnabledContentSet(): EnabledContentSet {
    return if (this) EnabledContent.all else EnabledContent.none
}
