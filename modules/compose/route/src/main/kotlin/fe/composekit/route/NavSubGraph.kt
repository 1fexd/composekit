@file:OptIn(ExperimentalUuidApi::class)

package fe.composekit.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navigation
import kotlin.reflect.KType
import kotlin.reflect.full.starProjectedType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

public interface NavSubGraph<out R : Route> {
    public val startDestination: R
    public val graph: NavGraphBuilder.(NavHostController) -> Unit
}

public interface Nav {
    public val graph: NavGraphBuilder.(NavHostController) -> Unit
}

public inline fun <R : Route, reified G : NavSubGraph<R>> NavGraphBuilder.attachSubGraph(
    page: G,
    navController: NavHostController,
) {
    navigation<G>(
        startDestination = page.startDestination,
        typeMap = NavTypes.Types
    ) {
        page.graph(this, navController)
    }
}


public inline fun <reified N : Nav> NavGraphBuilder.attachNav(nav: N, navController: NavHostController) {
    nav.graph(this, navController)
}

public object NavTypes {
    public val UuidType: NavType<Uuid> = UuidNavType
    public val Types: Map<KType, NavType<*>> = mapOf(Uuid::class.starProjectedType to UuidType)
}
