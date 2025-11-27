package fe.composekit.extension

import android.content.pm.ComponentInfo
import android.content.pm.ResolveInfo

public val ResolveInfo.info: ComponentInfo
    get() = activityInfo ?: providerInfo ?: serviceInfo


public val ResolveInfo.packageName: String?
    get() = resolvePackageName ?: info.packageName ?: info.applicationInfo.packageName

