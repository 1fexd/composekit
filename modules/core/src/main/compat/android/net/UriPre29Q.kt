package android.net

import fe.composekit.core.AndroidVersion
import java.net.URI

@JvmInline
public value class CompatUriHost(public val value: String)

public inline val Uri.compatHost: CompatUriHost?
    get() = (if (AndroidVersion.isAtLeastApi29Q()) host else URI(this.toString()).host)?.let { CompatUriHost(it) }
