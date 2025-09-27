package fe.composekit.extension

import android.content.ComponentName
import android.content.pm.ComponentInfo

public val ComponentInfo.componentName: ComponentName
    get() = ComponentName(packageName, name)
