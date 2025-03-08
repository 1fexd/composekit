package fe.composekit.route

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
import fe.android.compose.icon.IconPainter
import fe.android.compose.text.TextContent
import fe.composekit.component.CommonDefaults
import fe.composekit.component.list.column.group.ListItemData
import fe.composekit.component.list.item.default.DefaultTwoLineIconClickableShapeListItem
import fe.composekit.component.shape.CustomShapeDefaults

@Stable
public class RouteNavItemNew(
    public val route: Route,
    icon: IconPainter,
    headline: TextContent,
    subtitle: TextContent,
) : ListItemData<Any?>(icon, headline, subtitle)

@Composable
public fun RouteNavigateListItem(
    data: RouteNavItemNew,
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    navigate: (Route) -> Unit,
) {
    DefaultTwoLineIconClickableShapeListItem(
        shape = shape,
        padding = padding,
        headlineContent = data.headlineContent,
        supportingContent = data.subtitleContent,
        icon = data.icon,
        onClick = { navigate(data.route) }
    )
}
