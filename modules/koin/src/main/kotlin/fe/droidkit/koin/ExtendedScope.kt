package fe.droidkit.koin

import android.app.Application
import org.koin.core.scope.Scope
import kotlin.reflect.KClass

public class ExtendedScope<T : Any>(public val scope: Scope, public val clazz: KClass<T>) {
    public val applicationContext: Application by scope.inject<Application>()
}
