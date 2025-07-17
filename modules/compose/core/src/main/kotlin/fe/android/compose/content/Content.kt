package fe.android.compose.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

public typealias Content = @Composable () -> Unit
public typealias OptionalContent = (@Composable () -> Unit)?

@Composable
public inline fun rememberOptionalContent(condition: Boolean, crossinline content: @Composable () -> Unit): OptionalContent {
    return remember(key1 = condition) {
        if (condition) ({ content() }) else null
    }
}

@Composable
public inline fun <T : Any?> rememberOptionalContent(key1: T?, crossinline content: @Composable (T) -> Unit): OptionalContent {
    return remember(key1 = key1) {
        key1?.let {
            { content(it) }
        }
    }
}

@Composable
public inline fun rememberContent(crossinline content: @Composable () -> Unit): Content {
    return remember {
        { content() }
    }
}
