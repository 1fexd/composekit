package fe.android.lifecycle.koin.extension

import fe.android.lifecycle.LifecycleAwareService
import fe.droidkit.koin.DefinitionParam0
import fe.droidkit.koin.DefinitionParam1
import fe.droidkit.koin.DefinitionParam2
import fe.droidkit.koin.ExtendedScope
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

public inline fun <reified T : LifecycleAwareService> Module.service(
    qualifier: Qualifier? = null,
    noinline definition: DefinitionParam0<T>
): KoinDefinition<T> {
    return single(qualifier, true) { params ->
        val scope = ExtendedScope(this, T::class)
        val service = definition(scope, params)

        scope.applicationLifecycle.register(service)
        service
    }
}

public inline fun <reified T : LifecycleAwareService, reified P : Any> Module.service(
    qualifier: Qualifier? = null,
    noinline definition: DefinitionParam1<T, P>
): KoinDefinition<T> {
    return single(qualifier, true) { params ->
        val scope = ExtendedScope(this, T::class)
        val service = definition(scope, params, get<P>())

        scope.applicationLifecycle.register(service)
        service
    }
}

public inline fun <reified T : LifecycleAwareService, reified P : Any, reified P2 : Any> Module.service(
    qualifier: Qualifier? = null,
    noinline definition: DefinitionParam2<T, P, P2>
): KoinDefinition<T> {
    return single(qualifier, true) { params ->
        val scope = ExtendedScope(this, T::class)
        val service = definition(scope, params, get<P>(), get<P2>())

        scope.applicationLifecycle.register(service)
        service
    }
}
