package fe.composekit.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.AndroidComposeUiTest
import androidx.compose.ui.test.AndroidComposeUiTestEnvironment
import androidx.compose.ui.test.ComposeUiTestConfig
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.AndroidComposeUiTestEnvironment
import androidx.test.core.app.ActivityScenario
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration.Companion.seconds


internal fun <A : ComponentActivity> ActivityScenario<A>.getActivity(): A? {
    var activity: A? = null
    onActivity { activity = it }
    return activity
}

@Deprecated("Use new config based API")
@OptIn(ExperimentalTestApi::class)
public fun <A : ComponentActivity> runAndroidComposeUiTest(
    activityLauncher: () -> ActivityScenario<A>,
    effectContext: EmptyCoroutineContext = EmptyCoroutineContext,
    block: AndroidComposeUiTest<A>.(ActivityScenario<A>) -> Unit
) {
    return runAndroidComposeUiTest(
        activityLauncher,
        ComposeUiTestConfig(
            effectContext = effectContext,
            runTestContext = EmptyCoroutineContext,
            testTimeout = 60.seconds
        ),
        block
    )
}

@ExperimentalTestApi
public fun <A : ComponentActivity> runAndroidComposeUiTest(
    activityLauncher: () -> ActivityScenario<A>,
    config: ComposeUiTestConfig = ComposeUiTestConfig(
        effectContext = EmptyCoroutineContext,
        runTestContext = EmptyCoroutineContext,
        testTimeout = 60.seconds
    ),
    block: AndroidComposeUiTest<A>.(ActivityScenario<A>) -> Unit
) {
    var scenario: ActivityScenario<A>? = null
    val environment = AndroidComposeUiTestEnvironment<A>(config) {
        requireNotNull(scenario) {
            "ActivityScenario has not yet been launched, or has already finished. Make sure that " +
                    "any call to ComposeUiTest.setContent() and AndroidComposeUiTest.getActivity() " +
                    "is made within the lambda passed to AndroidComposeUiTestEnvironment.runTest()"
        }.getActivity()
    }
    try {
        environment.runTest {
            scenario = activityLauncher()
            block(scenario)
        }
    } finally {
        scenario?.close()
    }
}
