package fe.android.lifecycle.koin.extension

import fe.android.lifecycle.LifecycleServiceRegistry
import org.koin.core.KoinApplication
import org.koin.dsl.binds
import org.koin.dsl.module

public inline fun <reified Registry : LifecycleServiceRegistry> KoinApplication.applicationLifecycle(registry: Registry) {
    val module = module {
        single { registry } binds arrayOf(LifecycleServiceRegistry::class, Registry::class)
    }

    koin.loadModules(listOf(module))
}
