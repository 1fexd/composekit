package fe.composekit.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import fe.android.compose.feedback.LocalHapticFeedbackInteraction
import fe.android.compose.feedback.PreviewHapticFeedbackInteraction

@Composable
public fun PreviewThemeNew(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalHapticFeedbackInteraction provides PreviewHapticFeedbackInteraction) {
        MaterialTheme(content = content)
    }
}
