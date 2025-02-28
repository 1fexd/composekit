package fe.android.compose.version

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object AndroidVersion {
    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    val AT_LEAST_API_26_O: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    val AT_LEAST_API_28_P
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    val AT_LEAST_API_29_Q
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    val AT_LEAST_API_30_R
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    val AT_LEAST_API_31_S
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    val AT_LEAST_API_33_T
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    val AT_LEAST_API_34_U
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    val AT_LEAST_API_35_V
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM

    @ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
    inline fun <T> atLeastApi(value: Int, block: () -> T): T? {
        return if (Build.VERSION.SDK_INT >= value) {
            block()
        } else null
    }
}
