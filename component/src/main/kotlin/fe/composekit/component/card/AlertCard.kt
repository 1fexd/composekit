package fe.composekit.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fe.composekit.component.PreviewThemeNew
import fe.composekit.component.icon.FilledIcon
import fe.composekit.component.shape.CustomShapeDefaults
import fe.android.compose.icon.IconPainter
import fe.android.compose.icon.iconPainter
import fe.android.compose.text.DefaultContent.Companion.text
import fe.android.compose.text.TextContent
import fe.android.compose.extension.atElevation
import fe.android.compose.padding.Top
import fe.android.compose.extension.optionalClickable
import fe.android.compose.padding.exclude

object AlertCardDefaults {
    val MinHeight = Modifier.heightIn(min = 90.dp)

    val Padding = 16.dp
    val InnerPadding = PaddingValues(all = Padding)

    val HorizontalArrangement = Arrangement.spacedBy(12.dp)

    val IconSize = 20.dp
    val IconContainerSize = 34.dp
}

@Composable
fun AlertCard(
    modifier: Modifier = AlertCardDefaults.MinHeight,
    colors: CardColors = CardDefaults.cardColors(),
    shape: Shape = CustomShapeDefaults.SingleShape,
    onClick: (() -> Unit)? = null,
    innerPadding: PaddingValues = AlertCardDefaults.InnerPadding,
    horizontalArrangement: Arrangement.Horizontal = AlertCardDefaults.HorizontalArrangement,
    headline: TextContent,
    subtitle: TextContent,
    icon: IconPainter,
    iconSize: Dp = AlertCardDefaults.IconSize,
    iconContainerSize: Dp = AlertCardDefaults.IconContainerSize,
    iconContentDescription: String?,
    content: @Composable (() -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .optionalClickable(onClick = onClick),
        colors = colors
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
                .padding(innerPadding),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val containerColor = colors.containerColor.atElevation(
                MaterialTheme.colorScheme.surfaceTint, 6.dp
            )

            FilledIcon(
                icon = icon,
                iconSize = iconSize,
                containerSize = iconContainerSize,
                contentDescription = iconContentDescription,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = containerColor,
                    contentColor = contentColorFor(backgroundColor = containerColor)
                )
            )

            AlertCardContentLayout(
                title = {
                    CompositionLocalProvider(
                        LocalTextStyle provides MaterialTheme.typography.titleMedium,
                        content = headline.content
                    )
                },
                subtitle = {
                    CompositionLocalProvider(
                        LocalTextStyle provides MaterialTheme.typography.bodyMedium,
                        content = subtitle.content
                    )
                }
            )
        }

        if (content != null) {
            Box(modifier = Modifier.padding(AlertCardDefaults.InnerPadding.exclude(Top))) {
                content()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ClickableAlertCard2Preview() {
    PreviewThemeNew {
        Column(modifier = Modifier.width(400.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            AlertCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                icon = Icons.Default.Warning.iconPainter,
                iconContentDescription = null,
                headline = text("Browser status"),
                subtitle = text("LinkSheet has been set as default browser!")
            )

            AlertCard(
                icon = Icons.Default.Warning.iconPainter,
                iconContentDescription = null,
                headline = text("Shizuku integration"),
                subtitle = text("LinkSheet has detected at least one app known to be actually be a browser.")
            )
        }
    }
}