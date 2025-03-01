package fe.droidkit.koin

import android.app.Application
import android.content.Context
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.logger.Level
import org.koin.dsl.binds
import org.koin.dsl.module

@OptIn(KoinInternalApi::class)
public inline fun <reified App : Application> KoinApplication.androidApplicationContext(application: App): KoinApplication {
    if (koin.logger.isAt(Level.INFO)) {
        koin.logger.info("[init] declare Android Context")
    }

    val module = module {
        single { application } binds arrayOf(Context::class, Application::class, App::class)
    }

    koin.loadModules(listOf(module))
    return this
}
