package fe.android.compose.route.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDeepLinkDslBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

@Deprecated("Use androidx.navigation")
public fun <T : Any, A : Route.Arguments<T, U>, U> NavController.navigate(
    route: ArgumentRoute<T, A, U>,
    data: T
): Unit = navigate(route.buildNavigation(data))


@Deprecated("Use androidx.navigation")
public fun <T : RouteData, A : Route.Arguments<T, U>, U : Route.Unbundled<T>> route(
    base: String,
    buildRoute: (List<String>) -> String = { args ->
        args.joinToString(separator = "/") { it }
    },
    route: Route<T, A, U>
): ArgumentRoute<T, A, U> {
    val arguments = route.arguments
    val deepLinks = route.buildDeepLinks(arguments)

    return ArgumentRoute(
        base,
        buildRoute,
        arguments,
        route.createInstance,
        deepLinks.map { navDeepLink(it) })
}

@Deprecated("Use androidx.navigation")
public class ArgumentRoute<T : Any, A : Route.Arguments<T, U>, U>(
    private val base: String,
    private val buildRoute: (List<String>) -> String,
    private val arguments: A,
    private val createInstance: (U) -> T,
    public val navDeepLinks: List<NavDeepLink>
) {
    public val navArguments: List<NamedNavArgument> = arguments.items.map { it.toNavArgument() }

    public fun buildNavigation(instance: T): String = buildArgumentRoute(arguments.fromInstance(instance))

    public fun instance(bundle: Bundle): T = createInstance(arguments.unbundle(bundle))

    public val route: String by lazy {
        buildArgumentRoute(navArguments.map { it.asParameter() })
    }

    private fun buildArgumentRoute(arguments: List<String>) = "$base/${buildRoute(arguments)}"
}

@Deprecated("Use androidx.navigation")
public fun NamedNavArgument.asParameter(): String = "{${name}}"
@Deprecated("Use androidx.navigation")
public fun String.asParameter(): String = "{${this}}"

@Deprecated("Use androidx.navigation")
public interface RouteData

@Deprecated("Use androidx.navigation")
public abstract class Route<T : RouteData, A : Route.Arguments<T, U>, U : Route.Unbundled<T>>(
    public val arguments: A,
    public val createInstance: (U) -> T
) {
    public data class Argument<T, V>(
        val property: KProperty1<T, V>,
        val default: V? = null,
    ) {
        private val dataType = inferFromKClass(property.returnType.classifier as KClass<*>)

        @Suppress("UNCHECKED_CAST")
        public fun <R> unbundle(bundle: Bundle): R = dataType[bundle, property.name].let {
            (if (it == "null") null else it) as R
        }

        public fun toNavArgument(): NamedNavArgument = navArgument(property.name) {
            type = dataType
            nullable = property.returnType.isMarkedNullable
            if (default != null || nullable) {
                defaultValue = default
            }
        }
    }

    public interface Unbundled<T>
    public abstract class Arguments<T, U>(public val unbundle: (Bundle) -> U, public vararg val items: Argument<T, *>) {
        public fun fromInstance(instance: T): List<String> = items.map { it.property.get(instance).toString() }
    }

    public fun buildDeepLinks(arguments: A): List<NavDeepLinkDslBuilder.() -> Unit> = listOf()
}

@Deprecated("Use androidx.navigation")
public abstract class Route1<T : RouteData, R1>(
    argument: Argument<T, R1>,
    unbundle: (R1) -> T
) : Route<T, Route1.Arguments<T, R1>, Route1.Unbundled<T, R1>>(
    Arguments(argument), { unbundle(it.value) }
) {

    public data class Arguments<T, R1>(
        val argument: Argument<T, R1>
    ) : Route.Arguments<T, Unbundled<T, R1>>(
        { Unbundled(argument.unbundle(it)) }, argument
    )

    public data class Unbundled<T, R1>(val value: R1) : Route.Unbundled<T>
}

@Deprecated("Use androidx.navigation")
public abstract class Route2<T : RouteData, R1, R2>(
    argument1: Argument<T, R1>,
    argument2: Argument<T, R2>,
    unbundle: (R1, R2) -> T
) : Route<T, Route2.Arguments<T, R1, R2>, Route2.Unbundled<T, R1, R2>>(
    Arguments(argument1, argument2), { unbundle(it.value1, it.value2) }
) {
    public data class Arguments<T, R1, R2>(
        val argument1: Argument<T, R1>,
        val argument2: Argument<T, R2>
    ) : Route.Arguments<T, Unbundled<T, R1, R2>>(
        { Unbundled(argument1.unbundle(it), argument2.unbundle(it)) },
        argument1,
        argument2
    )

    public data class Unbundled<T, R1, R2>(val value1: R1, val value2: R2) : Route.Unbundled<T>
}

@Deprecated("Use androidx.navigation")
public abstract class Route3<T : RouteData, R1, R2, R3>(
    argument1: Argument<T, R1>,
    argument2: Argument<T, R2>,
    argument3: Argument<T, R3>,
    unbundle: (R1, R2, R3) -> T
) : Route<T, Route3.Arguments<T, R1, R2, R3>, Route3.Unbundled<T, R1, R2, R3>>(
    Arguments(argument1, argument2, argument3), { unbundle(it.value1, it.value2, it.value3) }
) {
    public data class Arguments<T, R1, R2, R3>(
        val argument1: Argument<T, R1>,
        val argument2: Argument<T, R2>,
        val argument3: Argument<T, R3>
    ) : Route.Arguments<T, Unbundled<T, R1, R2, R3>>(
        { Unbundled(argument1.unbundle(it), argument2.unbundle(it), argument3.unbundle(it)) },
        argument1, argument2
    )

    public data class Unbundled<T, R1, R2, R3>(
        val value1: R1,
        val value2: R2,
        val value3: R3
    ) : Route.Unbundled<T>
}


@Deprecated("Use androidx.navigation")
public fun <T : RouteData, A : Route.Arguments<T, U>, U> NavGraphBuilder.argumentRouteComposable(
    route: ArgumentRoute<T, A, U>,
    content: @Composable (NavBackStackEntry, T) -> Unit
) {
    composable(
        route.route,
        route.navArguments,
        route.navDeepLinks,
    ) { stack ->
        val bundle = stack.arguments ?: throw IllegalArgumentException("No bundle provided!")
        val data = route.instance(bundle)

        content(stack, data)
    }
}
