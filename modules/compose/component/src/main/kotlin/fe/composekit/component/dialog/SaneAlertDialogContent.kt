package fe.composekit.component.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import fe.android.compose.content.OptionalContent
import my.nanihadesuka.compose.LazyColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings

public enum class ContentSize {
    WrapContent, Max
}

public interface ContentSettings {
    public fun buildModifier(): Modifier
}

@Immutable
public data class SaneDialogContentSettings(
    public val innerPadding: PaddingValues,
    public val contentSize: ContentSize
) : ContentSettings {

    override fun buildModifier(): Modifier {
        val base = when(contentSize) {
            ContentSize.WrapContent -> Modifier.wrapContentSize()
            ContentSize.Max -> Modifier.fillMaxSize()
        }
        return base.padding(innerPadding)
    }
}

@Immutable
public data class CustomSaneDialogContentSettings(val modifier: Modifier) : ContentSettings {
    override fun buildModifier(): Modifier = modifier
}

@Deprecated("Use new settings-based API")
@Composable
public fun SaneAlertDialogContent(
    contentModifier: Modifier = SaneDialogDefaults.SaneDialogContentModifier,
    innerModifier: Modifier = SaneDialogDefaults.SaneDialogInnerModifier,
    state: LazyListState = rememberLazyListState(),
    settings: ScrollbarSettings = DialogDefaults.DefaultScrollbarSettings,
    dividerTop: OptionalContent = { HorizontalDivider() },
    dividerBottom: OptionalContent = { HorizontalDivider() },
    content: @Composable BoxScope.() -> Unit
) {
    Column(modifier = contentModifier) {
        dividerTop?.invoke()

        LazyColumnScrollbar(modifier = Modifier, state = state, settings = settings) {
            Box(
                modifier = innerModifier,
                content = content
            )
        }

        dividerTop?.invoke()
    }
}

@Composable
public fun SaneAlertDialogContent2(
    modifier: Modifier = SaneDialogDefaults.SaneDialogContentModifier,
    contentSettings: ContentSettings = SaneDialogDefaults.ContentSettings,
    state: LazyListState = rememberLazyListState(),
    settings: ScrollbarSettings = DialogDefaults.DefaultScrollbarSettings,
    dividerTop: OptionalContent = { HorizontalDivider() },
    dividerBottom: OptionalContent = { HorizontalDivider() },
    content: @Composable BoxScope.() -> Unit
) {
    Column(modifier = modifier) {
        dividerTop?.invoke()

        LazyColumnScrollbar(modifier = Modifier, state = state, settings = settings) {
            Box(
                modifier = contentSettings.buildModifier(),
                content = content
            )
        }

        dividerTop?.invoke()
    }
}
