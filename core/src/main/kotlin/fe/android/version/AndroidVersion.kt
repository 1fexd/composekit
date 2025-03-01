@file:Suppress("NOTHING_TO_INLINE")

package fe.android.version

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

public object AndroidVersion {
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    public inline fun isAtLeastApi26O(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    public inline fun isAtLeastApi28P(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    public inline fun isAtLeastApi29Q(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    public inline fun isAtLeastApi30R(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    public inline fun isAtLeastApi31S(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    public inline fun isAtLeastApi33T(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public inline fun isAtLeastApi34U(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public inline fun isAtLeastApi35V(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM
    }

    @ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
    public inline fun <T> atLeastApi(value: Int, block: () -> T): T? {
        return if (Build.VERSION.SDK_INT >= value) {
            block()
        } else null
    }
}
