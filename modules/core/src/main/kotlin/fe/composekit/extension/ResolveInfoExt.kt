package fe.composekit.extension

import android.content.pm.ComponentInfo
import android.content.pm.ResolveInfo
import android.util.StringBuilderPrinter

public val ResolveInfo.info: ComponentInfo
    get() = activityInfo ?: serviceInfo ?: providerInfo


public val ResolveInfo.packageName: String
    get() = resolvePackageName ?: info.packageName ?: info.applicationInfo.packageName

public fun ResolveInfo.toStringBuilder(sb: StringBuilder = StringBuilder()): StringBuilder {
    val sbp = StringBuilderPrinter(sb)
    dump(sbp, "")

    return sb
}
