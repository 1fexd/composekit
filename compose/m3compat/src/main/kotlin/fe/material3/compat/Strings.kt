package fe.material3.compat

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.R as MaterialR

@Composable
@ReadOnlyComposable
public fun getString(stringId: Int): String {
    val resources = LocalContext.current.resources
    return resources.getString(stringId)
}

public object Strings {
    @SuppressLint("PrivateResource")
    public val Dialog: Int = MaterialR.string.m3c_dialog
}
