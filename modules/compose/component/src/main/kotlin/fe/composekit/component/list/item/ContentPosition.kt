package fe.composekit.component.list.item

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import fe.android.compose.content.OptionalContent

@Immutable
public enum class ContentPosition {
    Leading, Trailing;

    @Stable
    public fun pick(position: ContentPosition, primary: OptionalContent, other: OptionalContent): OptionalContent {
        return if (position == this) primary else other
    }
}
