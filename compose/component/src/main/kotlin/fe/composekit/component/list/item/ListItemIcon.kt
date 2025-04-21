package fe.composekit.component.list.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fe.android.compose.icon.IconPainter
import fe.composekit.component.CommonDefaults
import fe.composekit.component.icon.IconDefaults

@Composable
public fun ListItemIcon(
    iconPainter: IconPainter,
    contentDescription: String? = null,
) {
    val painter = iconPainter.rememberPainter()

    Box(modifier = CommonDefaults.BaseContentModifier, contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier.size(IconDefaults.IconSize),
            painter = painter,
            contentDescription = contentDescription
        )
    }
}
