package fe.droidkit.koin

import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersHolder
import org.koin.core.qualifier.Qualifier

public typealias DefinitionParam0<T> = ExtendedScope<T>.(ParametersHolder) -> T
public typealias DefinitionParam1<T, P> = ExtendedScope<T>.(ParametersHolder, P) -> T
public typealias DefinitionParam2<T, P, P2> = ExtendedScope<T>.(ParametersHolder, P, P2) -> T
public typealias DefinitionParam3<T, P, P2, P3> = ExtendedScope<T>.(ParametersHolder, P, P2, P3) -> T

public inline fun <reified T : Any, reified P : Any> Module.single(
    qualifier: Qualifier? = null,
    createdAtStart: Boolean = false,
    noinline definition: DefinitionParam1<T, P>
): KoinDefinition<T> {
    return single(qualifier, createdAtStart) { params -> definition(ExtendedScope(this, T::class), params, get<P>()) }
}

public inline fun <reified T : Any, reified P : Any, reified P2 : Any> Module.single(
    qualifier: Qualifier? = null,
    createdAtStart: Boolean = false,
    noinline definition: DefinitionParam2<T, P, P2>
): KoinDefinition<T> {
    return single(qualifier, createdAtStart) { params ->
        definition(
            ExtendedScope(this, T::class),
            params,
            get<P>(),
            get<P2>()
        )
    }
}

public inline fun <reified T : Any, reified P : Any, reified P2 : Any, reified P3 : Any> Module.single(
    qualifier: Qualifier? = null,
    createdAtStart: Boolean = false,
    noinline definition: DefinitionParam3<T, P, P2, P3>
): KoinDefinition<T> {
    return single(qualifier, createdAtStart) { params ->
        definition(
            ExtendedScope(this, T::class),
            params,
            get<P>(),
            get<P2>(),
            get<P3>()
        )
    }
}

public inline fun <reified T : Any, reified P : Any> Module.factory(
    qualifier: Qualifier? = null,
    noinline definition: DefinitionParam1<T, P>
): KoinDefinition<T> {
    return factory(qualifier) { params -> definition(ExtendedScope(this, T::class), params, get<P>()) }
}

public inline fun <reified T : Any, reified P : Any, reified P2 : Any> Module.factory(
    qualifier: Qualifier? = null,
    noinline definition: DefinitionParam2<T, P, P2>
): KoinDefinition<T> {
    return factory(qualifier) { params -> definition(ExtendedScope(this, T::class), params, get<P>(), get<P2>()) }
}
