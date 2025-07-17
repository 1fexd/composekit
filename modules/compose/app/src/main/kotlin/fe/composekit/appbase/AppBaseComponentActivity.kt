package fe.composekit.appbase

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.KoinAndroidContext

public open class AppBaseComponentActivity : ComponentActivity() {
    public var edgeToEdge: Boolean = false
        private set

    public fun updateEdgeToEdge(status: SystemBarStyle, navigation: SystemBarStyle) {
        return enableEdgeToEdge(statusBarStyle = status, navigationBarStyle = navigation)
    }

    public fun initPadding(): AppBaseComponentActivity {
        enableEdgeToEdge()
        edgeToEdge = true
        return this
    }

    public fun setContent(edgeToEdge: Boolean, content: @Composable () -> Unit) {
        if (edgeToEdge) initPadding()

        return setContent {
            KoinAndroidContext(content = content)
        }
    }
}
