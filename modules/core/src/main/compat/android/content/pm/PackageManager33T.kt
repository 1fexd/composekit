package android.content.pm

import android.content.ComponentName
import android.content.Intent
import fe.composekit.flag.ApplicationInfoFlags
import fe.composekit.flag.ComponentEnabledFlags
import fe.composekit.flag.ComponentEnabledStateFlags
import fe.composekit.flag.ComponentInfoFlags
import fe.composekit.flag.PackageInfoFlags
import fe.composekit.flag.ResolveInfoFlags
import fe.composekit.core.AndroidVersion


public fun PackageManager.resolveActivityCompat(
    intent: Intent,
    flags: ResolveInfoFlags = ResolveInfoFlags.EMPTY,
): ResolveInfo? {
    return when {
        AndroidVersion.isAtLeastApi33T() -> resolveActivity(
            intent, PackageManager.ResolveInfoFlags.of(flags.value)
        )

        else -> resolveActivity(intent, flags.value.toInt())
    }
}

public fun PackageManager.queryIntentActivitiesCompat(
    intent: Intent,
    flags: ResolveInfoFlags = ResolveInfoFlags.EMPTY,
): List<ResolveInfo> {
    return queryIntentActivitiesCompat(intent, flags.value)
}

private fun PackageManager.queryIntentActivitiesCompat(intent: Intent, flags: Long = 0L): List<ResolveInfo> {
    return when {
        AndroidVersion.isAtLeastApi33T() -> queryIntentActivities(
            intent, PackageManager.ResolveInfoFlags.of(flags)
        )
        else -> queryIntentActivities(intent, flags.toInt())
    }
}

public fun PackageManager.getInstalledPackagesCompat(flags: Long = 0): List<PackageInfo> {
    return when {
        AndroidVersion.isAtLeastApi33T() -> getInstalledPackages(PackageManager.PackageInfoFlags.of(flags))
        else -> getInstalledPackages(flags.toInt())
    }
}

public fun PackageManager.getApplicationInfoCompatOrNull(
    packageName: String,
    flags: ApplicationInfoFlags = ApplicationInfoFlags.EMPTY,
): ApplicationInfo? {
    return runCatching { getApplicationInfoCompat(packageName, flags) }.getOrNull()
}

@Deprecated("Use getApplicationInfoCompatOrNull instead")
public fun PackageManager.getApplicationInfoCompat(
    packageName: String,
    flags: ApplicationInfoFlags = ApplicationInfoFlags.EMPTY,
): ApplicationInfo {
    return when {
        AndroidVersion.isAtLeastApi33T() -> getApplicationInfo(
            packageName, PackageManager.ApplicationInfoFlags.of(flags.value)
        )
        else -> getApplicationInfo(packageName, flags.value.toInt())
    }
}

public fun PackageManager.getActivityInfoCompatOrNull(
    componentName: ComponentName,
    flags: ComponentInfoFlags = ComponentInfoFlags.EMPTY
): ActivityInfo? {
    return runCatching { getActivityInfoCompat(componentName, flags) }.getOrNull()
}

@Deprecated("Use getActivityInfoCompatOrNull instead")
public fun PackageManager.getActivityInfoCompat(
    componentName: ComponentName,
    flags: ComponentInfoFlags = ComponentInfoFlags.EMPTY
): ActivityInfo {
    return when {
        AndroidVersion.isAtLeastApi33T() -> getActivityInfo(
            componentName, PackageManager.ComponentInfoFlags.of(flags.value)
        )
        else -> getActivityInfo(componentName, flags.value.toInt())
    }
}

public fun PackageManager.getPackageInfoCompatOrNull(
    packageName: String,
    flags: PackageInfoFlags = PackageInfoFlags.EMPTY
): PackageInfo? {
    return runCatching { getPackageInfoCompat(packageName, flags) }.getOrNull()
}

@Deprecated("Use getPackageInfoCompatOrNull instead")
public fun PackageManager.getPackageInfoCompat(
    packageName: String,
    flags: PackageInfoFlags = PackageInfoFlags.EMPTY
): PackageInfo {
    return when {
        AndroidVersion.isAtLeastApi33T() -> getPackageInfo(
            packageName, PackageManager.PackageInfoFlags.of(flags.value)
        )

        else -> getPackageInfo(packageName, flags.value.toInt())
    }
}

public fun PackageManager.setComponentEnabledSettingCompat(
    componentName: ComponentName,
    newState: ComponentEnabledStateFlags = ComponentEnabledStateFlags.EMPTY,
    flags: ComponentEnabledFlags = ComponentEnabledFlags.EMPTY,
) {
    setComponentEnabledSetting(componentName, newState.value.toInt(), flags.value.toInt())
}
