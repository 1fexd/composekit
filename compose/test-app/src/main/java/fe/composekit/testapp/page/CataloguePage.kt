package fe.composekit.testapp.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fe.composekit.testapp.Route

@Composable
fun CataloguePage(modifier: Modifier = Modifier, items: List<Route>, navigateTo: (Route) -> Unit) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = items, key = { it::class.simpleName!! }) {
            CatalogueCard(
                text = "${it::class.simpleName}",
                onClick = { navigateTo(it) }
            )
        }
    }
}

@Composable
fun CatalogueCard(
    text: String,
    onClick: () -> Unit,
) {
    OutlinedCard(
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
            .clip(CardDefaults.outlinedShape)
            .clickable(onClick = onClick),
        shape = CardDefaults.outlinedShape
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CataloguePreview() {
    CataloguePage(navigateTo = {}, items = Route.entries)
}


@Preview(showBackground = true)
@Composable
private fun CatalogueCardPreview() {
    CatalogueCard(text = "SearchTopAppBar", onClick = {})
}
