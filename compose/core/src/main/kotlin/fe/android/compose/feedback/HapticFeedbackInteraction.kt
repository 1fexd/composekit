package fe.android.compose.feedback

import android.content.ClipboardManager
import android.content.Context
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.AndroidUriHandler
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.UriHandler
import androidx.core.content.getSystemService
import fe.android.compose.extension.setPlainText

val LocalHapticFeedbackInteraction = staticCompositionLocalOf<HapticFeedbackInteraction> {
    error("LocalHapticFeedbackInteraction")
}

interface HapticFeedbackInteraction {
    fun copy(content: String, type: FeedbackType)
    fun openUri(uri: String, type: FeedbackType)

    fun perform(type: FeedbackType)
}

fun HapticFeedbackInteraction.wrap(type: FeedbackType, fn: () -> Unit): () -> Unit {
    return {
        fn()
        perform(type)
    }
}

@JvmInline
value class FeedbackType private constructor(val flag: Int) {
    companion object {
        val None = FeedbackType(-1)
        val LongPress = FeedbackType(HapticFeedbackConstants.LONG_PRESS)
        val TextHandleMove = FeedbackType(9)
        val Confirm = FeedbackType(16)
        val Decline = FeedbackType(17)
        val DragStart = FeedbackType(25)
        val GestureEnd = FeedbackType(13)
        val SegmentFrequentTick = FeedbackType(27)
    }
}

class DefaultHapticFeedbackInteraction(
    private val clipboardManager: ClipboardManager,
    private val uriHandler: UriHandler,
    private val view: View,
) : HapticFeedbackInteraction {

    override fun copy(content: String, type: FeedbackType) {
        clipboardManager.setPlainText(content)
        performHapticFeedback(type)
    }

    override fun openUri(uri: String, type: FeedbackType) {
        uriHandler.openUri(uri)
        performHapticFeedback(type)
    }

    override fun perform(type: FeedbackType) {
        performHapticFeedback(type)
    }

    private fun performHapticFeedback(type: FeedbackType) {
        view.performHapticFeedback(type.flag)
    }
}

@Composable
fun rememberHapticFeedbackInteraction(
    context: Context = LocalContext.current,
    view: View = LocalView.current,
): DefaultHapticFeedbackInteraction {
    return remember(context, view) {
        DefaultHapticFeedbackInteraction(
            clipboardManager = context.getSystemService<ClipboardManager>()!!,
            uriHandler = AndroidUriHandler(context),
            view = view
        )
    }
}



