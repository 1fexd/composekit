package fe.composekit.test

import android.app.Activity
import android.app.Instrumentation
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import kotlin.jvm.java

public class ActivityInvoker(public val instrumentation: Instrumentation) {
//    public val instrumentation: Instrumentation by lazy { InstrumentationRegistry.getInstrumentation() }

    public inline fun <reified A : Activity> makeMainActivityIntent(context: Context, block: Intent.() -> Unit = {}): Intent {
        return Intent.makeMainActivity(ComponentName(context, A::class.java)).apply(block)
    }

    public inline fun <reified A : Activity> getIntentForActivity(block: Intent.() -> Unit = {}): Intent {
        val intent = makeMainActivityIntent<A>(instrumentation.targetContext, block)
        return when {
            instrumentation.targetContext.packageManager.resolveActivity(intent, 0) != null -> {
                intent
            }

            else -> makeMainActivityIntent<A>(instrumentation.context, block)
        }
    }
}
