package fe.android.span.helper

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

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

private val StubLocalLinkTags = object : LinkTags {
    override fun get(key: String, value: String): String? = "CompositionLocal LocalLinkTags not present"
    override fun getById(id: String): String? = "CompositionLocal LocalLinkTags not present"
}

public val LocalLinkTags: ProvidableCompositionLocal<LinkTags> = compositionLocalOf {
    StubLocalLinkTags
}
