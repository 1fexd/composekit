package fe.composekit.testapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHost() {
    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route.toString()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = currentRoute,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if(currentRoute != Catalogue::class.qualifiedName) {
                        IconButton(onClick = navController::popBackStack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = Catalogue) {
                    composable<Catalogue> {
                        Catalogue(
                            navigateTo = navController::navigate
                        )
                    }
                    composable<SearchTopAppBar> { SearchTopAppBarPage() }
                }
            }
        }
    )
}

@Composable
fun Catalogue(modifier: Modifier = Modifier, navigateTo: (Any) -> Unit) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            CatalogueCard(
                text = "SearchTopAppBar",
                onClick = { navigateTo(SearchTopAppBar) }
            )
        }

        item {
            CatalogueCard(text = "AlertCard", onClick = {})
        }

        item {
            CatalogueCard(text = "AppIconImage", onClick = {})
        }
    }
}

@Composable
fun CatalogueCard(
    text: String,
    onClick: () -> Unit
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
    Catalogue(navigateTo = {})
}


@Preview(showBackground = true)
@Composable
private fun CatalogueCardPreview() {
    CatalogueCard(text = "SearchTopAppBar", onClick = {})
}
