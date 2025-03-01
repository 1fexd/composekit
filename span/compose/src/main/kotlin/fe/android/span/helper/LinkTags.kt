package fe.android.span.helper

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
public data class LinkTags(
    public val urlAnnotationKey: String = "url",
    public val urlIdAnnotationKey: String = "url-id",
    public val urlIds: Map<String, String> = emptyMap(),
)

public val LocalLinkTags: ProvidableCompositionLocal<LinkTags> = staticCompositionLocalOf {
    error("CompositionLocal LocalLinkTags not present")
}
