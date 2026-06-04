package android.content.pm

import fe.composekit.core.AndroidVersion

public fun PackageManager.getInstallerFor(packageName: String): String? {
    @Suppress("DEPRECATION")
    return when {
        AndroidVersion.isAtLeastApi30R() -> getInstallSourceInfo(packageName).initiatingPackageName
        else -> getInstallerPackageName(packageName)
    }
}
