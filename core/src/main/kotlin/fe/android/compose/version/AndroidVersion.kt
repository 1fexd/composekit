package fe.android.compose.version

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.Keep

@Keep
object AndroidVersion {
    @get:Keep
    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    inline val AT_LEAST_API_26_O: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    inline val AT_LEAST_API_28_P
        @Keep
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    inline val AT_LEAST_API_29_Q
        @Keep
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    inline val AT_LEAST_API_30_R
        @Keep
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    @get:Keep
    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    inline val AT_LEAST_API_31_S
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    inline val AT_LEAST_API_33_T
        @Keep
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    inline val AT_LEAST_API_34_U
        @Keep
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

    inline val AT_LEAST_API_35_V
        @Keep
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM

    @Keep
    @ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
    inline fun <T> atLeastApi(value: Int, block: () -> T): T? {
        return if (Build.VERSION.SDK_INT >= value) {
            block()
        } else null
    }
}
