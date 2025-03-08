package fe.composekit.test

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.robolectric.RuntimeEnvironment

public interface BaseUnitTest : KoinTest {
    @After
    public fun teardown() {
        stopKoin()
    }
}

public interface UnitTest : BaseUnitTest {
    public val instrumentation: Instrumentation
        get() = InstrumentationRegistry.getInstrumentation()

    public val targetContext: Context
        get() = instrumentation.targetContext

    public val applicationContext: Context
        get() = targetContext.applicationContext

}

public interface RobolectricTest : BaseUnitTest {
    public val context: Context
        get() = RuntimeEnvironment.getApplication().applicationContext
}
