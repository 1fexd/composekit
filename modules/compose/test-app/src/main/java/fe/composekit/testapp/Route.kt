package fe.composekit.testapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import fe.composekit.testapp.page.CataloguePage
import kotlinx.serialization.Serializable

interface Route {
    companion object {
        val entries: List<Route> = listOf(CatalogueRoute, SearchTopAppBarRoute, DialogRoute)
    }

    @Composable
    fun Content(controller: NavHostController)
}

@Serializable
object CatalogueRoute : Route {
    private val pages = Route.entries - this

    @Composable
    override fun Content(controller: NavHostController) {
        CataloguePage(items = pages, navigateTo = controller::navigate)
    }
}

@Serializable
object SearchTopAppBarRoute : Route {
    @Composable
    override fun Content(controller: NavHostController) {
        SearchTopAppBarPage()
    }
}

@Serializable
object DialogRoute : Route {
    @Composable
    override fun Content(controller: NavHostController) {
        DialogPage()
    }
}
