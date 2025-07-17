package fe.android.compose.padding

import fe.android.compose.padding.Horizontal as HorizontalPadding
import fe.android.compose.padding.Vertical as VerticalPadding
import fe.android.compose.padding.Start as StartPadding
import fe.android.compose.padding.End as EndPadding
import fe.android.compose.padding.Top as TopPadding
import fe.android.compose.padding.Bottom as BottomPadding

public enum class PaddingSide(public val value: PaddingValuesSides) {
    Horizontal(HorizontalPadding),
    Vertical(VerticalPadding),
    Start(StartPadding),
    End(EndPadding),
    Top(TopPadding),
    Bottom(BottomPadding),
}
