package fe.composekit.appbase

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

open class AppBaseComponentActivity : ComponentActivity() {
    var edgeToEdge: Boolean = false
        private set

    fun updateEdgeToEdge(status: SystemBarStyle, navigation: SystemBarStyle) {
        return enableEdgeToEdge(statusBarStyle = status, navigationBarStyle = navigation)
    }

    fun initPadding(): AppBaseComponentActivity {
        enableEdgeToEdge()
        edgeToEdge = true
        return this
    }

    fun setContent(edgeToEdge: Boolean, content: @Composable () -> Unit) {
        if (edgeToEdge) initPadding()

        return setContent {
            KoinAndroidContext(content = content)
        }
    }
}
