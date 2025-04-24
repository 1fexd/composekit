package fe.android.compose.feedback

import android.content.ClipboardManager
import android.content.Context
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.AndroidUriHandler
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.UriHandler
import androidx.core.content.getSystemService
import fe.android.compose.extension.setPlainText

public val LocalHapticFeedbackInteraction: ProvidableCompositionLocal<HapticFeedbackInteraction> =
    staticCompositionLocalOf<HapticFeedbackInteraction> {
        error("LocalHapticFeedbackInteraction")
    }

public interface HapticFeedbackInteraction {
    public fun copy(content: String, type: FeedbackType)
    public fun openUri(uri: String, type: FeedbackType)
    public fun perform(type: FeedbackType)
}

public fun HapticFeedbackInteraction.wrap(type: FeedbackType, fn: () -> Unit): () -> Unit {
    return {
        fn()
        perform(type)
    }
}

public fun <T> HapticFeedbackInteraction.wrap(type: FeedbackType, fn: (T) -> Unit): (T) -> Unit {
    return {
        val result = fn(it)
        perform(type)
        result
    }
}

@JvmInline
public value class FeedbackType private constructor(public val flag: Int) {
    public companion object {
        public val None: FeedbackType = FeedbackType(-1)
        public val LongPress: FeedbackType = FeedbackType(HapticFeedbackConstants.LONG_PRESS)
        public val TextHandleMove: FeedbackType = FeedbackType(9)
        public val Confirm: FeedbackType = FeedbackType(16)
        public val Decline: FeedbackType = FeedbackType(17)
        public val DragStart: FeedbackType = FeedbackType(25)
        public val GestureEnd: FeedbackType = FeedbackType(13)
        public val SegmentFrequentTick: FeedbackType = FeedbackType(27)
    }
}

public class DefaultHapticFeedbackInteraction(
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
public fun rememberHapticFeedbackInteraction(
    context: Context = LocalContext.current,
    view: View = LocalView.current,
): DefaultHapticFeedbackInteraction {
    return remember(key1 = context, key2 = view) {
        DefaultHapticFeedbackInteraction(
            clipboardManager = context.getSystemService<ClipboardManager>()!!,
            uriHandler = AndroidUriHandler(context),
            view = view
        )
    }
}



