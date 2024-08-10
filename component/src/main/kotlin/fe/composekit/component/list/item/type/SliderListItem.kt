package fe.composekit.component.list.item.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.composekit.component.CommonDefaults
import fe.composekit.component.list.column.shape.ShapeListItem
import fe.composekit.component.shape.CustomShapeDefaults
import fe.android.compose.text.ComposableTextContent.Companion.content
import fe.android.compose.text.DefaultContent.Companion.text
import fe.android.compose.text.TextContent
import fe.android.compose.extension.enabled

@Composable
fun SliderListItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    valueRangeStep: Float = 1f,
    onValueChange: (Float) -> Unit,
    valueFormatter: (Float) -> String = { it.toString() },
    shape: Shape = CustomShapeDefaults.SingleShape,
    padding: PaddingValues = CommonDefaults.EmptyPadding,
    headlineContent: TextContent,
    supportingContent: TextContent? = null,
    overlineContent: TextContent? = null,
) {
    ShapeListItem(
        modifier = Modifier
            .enabled(enabled)
            .then(modifier),
        shape = shape,
        padding = padding,
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = content {
            Column {
                supportingContent?.content?.invoke()

                DefaultSliderListItem(
                    enabled = enabled,
                    value = value,
                    valueRange = valueRange,
                    valueRangeStep = valueRangeStep,
                    valueFormatter = valueFormatter,
                    onValueChange = onValueChange
                )
            }
        }
    )
}

@Composable
private fun DefaultSliderListItem(
    enabled: Boolean = true,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    valueRangeStep: Float = 1f,
    onValueChange: (Float) -> Unit,
    valueFormatter: (Float) -> String = { it.toString() },
) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Slider(
            modifier = Modifier.weight(1.0f),
            enabled = enabled,
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = (((valueRange.endInclusive - valueRange.start) / valueRangeStep) - 1).toInt()
        )

        Text(text = valueFormatter(value))
    }
}

@Preview
@Composable
fun SliderListItemPreview() {
    SliderListItem(
        shape = CustomShapeDefaults.SingleShape,
        padding = CommonDefaults.EmptyPadding,
        headlineContent = text("Preview headline"),
        supportingContent = text("Supprting\ncontent\nOkay?"),
        value = 1.0f,
        onValueChange = {},
    )
}
