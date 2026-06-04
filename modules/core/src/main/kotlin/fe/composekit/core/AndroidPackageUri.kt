package fe.composekit.core

import android.content.Context
import android.net.Uri

public object AndroidPackageUri {
    public fun get(scheme: Scheme, uri: Uri?): AndroidAppPackage? {
        return if (uri?.scheme == scheme.scheme) uri.host?.let { AndroidAppPackage(it) } else null
    }

    public fun create(scheme: Scheme, context: Context): Uri {
        return Uri.fromParts(scheme.scheme, context.packageName, null)
    }

    public fun create(scheme: Scheme, packageName: String): Uri {
        return Uri.fromParts(scheme.scheme, packageName, null)
    }
}

public enum class Scheme(public val scheme: String) {
    Package("package"),
    AppScheme("android-app")
}

@JvmInline
public value class AndroidAppPackage(public val packageName: String)

public fun Scheme.create(packageName: String): Uri {
    return AndroidPackageUri.create(this, packageName)
}

public fun Uri.getAndroidAppPackage(scheme: Scheme): AndroidAppPackage? {
    return AndroidPackageUri.get(scheme, this)
}
