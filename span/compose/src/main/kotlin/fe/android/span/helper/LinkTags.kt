package fe.android.span.helper

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
public data class DefaultLinkTags(
    public val urlAnnotationKey: String = "url",
    public val urlIdAnnotationKey: String = "url-id",
    private val urlIds: Map<String, String> = emptyMap(),
) : LinkTags {

    public override fun get(key: String, value: String): String? {
        val link = when (key) {
            urlAnnotationKey -> value
            urlIdAnnotationKey -> getById(value)
            else -> return null
        }

        return link
    }

    override fun getById(id: String): String? {
        return urlIds[id]
    }
}

public interface LinkTags {
    public fun get(key: String, value: String): String?
    public fun getById(id: String): String?
}

public val LocalLinkTags: ProvidableCompositionLocal<LinkTags> = staticCompositionLocalOf {
    error("CompositionLocal LocalLinkTags not present")
}
