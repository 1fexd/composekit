package fe.android.compose.version

import android.os.Build

object AndroidVersion {
    inline val AT_LEAST_API_24_N get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    inline val AT_LEAST_API_26_O get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    inline val AT_LEAST_API_28_P get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    inline val AT_LEAST_API_29_Q get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    inline val AT_LEAST_API_30_R get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    inline val AT_LEAST_API_31_S get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    inline val AT_LEAST_API_33_T get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    inline val AT_LEAST_API_34_U get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
}
