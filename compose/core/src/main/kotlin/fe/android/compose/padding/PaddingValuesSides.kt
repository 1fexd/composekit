package fe.android.compose.padding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

public sealed interface PaddingValuesSides {
    public fun only(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues
    public fun exclude(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues
}

public data object Horizontal : PaddingValuesSides {
    override fun only(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection),
            end = paddingValues.calculateEndPadding(layoutDirection)
        )
    }

    override fun exclude(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return Vertical.only(paddingValues, layoutDirection)
    }
}

public data object Vertical : PaddingValuesSides {
    override fun only(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
    }

    override fun exclude(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return Horizontal.only(paddingValues, layoutDirection)
    }
}

public data object Start : PaddingValuesSides {
    override fun only(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(start = paddingValues.calculateStartPadding(layoutDirection))
    }

    override fun exclude(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDirection),
            bottom = paddingValues.calculateBottomPadding()
        )
    }
}

public data object End : PaddingValuesSides {
    override fun only(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(end = paddingValues.calculateEndPadding(layoutDirection))
    }

    override fun exclude(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection),
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
    }
}

public data object Top : PaddingValuesSides {
    override fun only(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(top = paddingValues.calculateTopPadding())
    }

    override fun exclude(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection),
            end = paddingValues.calculateEndPadding(layoutDirection),
            bottom = paddingValues.calculateBottomPadding()
        )
    }
}

public data object Bottom : PaddingValuesSides {
    override fun only(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(bottom = paddingValues.calculateBottomPadding())
    }

    override fun exclude(paddingValues: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDirection),
        )
    }
}



@Composable
public fun PaddingValues.exclude(side: PaddingValuesSides): PaddingValues {
    return exclude(side = side, LocalLayoutDirection.current)
}

public fun PaddingValues.exclude(side: PaddingValuesSides, layoutDirection: LayoutDirection): PaddingValues {
    return side.exclude(this, layoutDirection)
}

@Composable
public fun PaddingValues.only(side: PaddingValuesSides): PaddingValues {
    return only(side = side, LocalLayoutDirection.current)
}

public fun PaddingValues.only(side: PaddingValuesSides, layoutDirection: LayoutDirection): PaddingValues {
    return side.only(this, layoutDirection)
}

@Composable
public fun PaddingValues.exclude(side: PaddingSide): PaddingValues {
    return exclude(side = side.value, LocalLayoutDirection.current)
}

public fun PaddingValues.exclude(side: PaddingSide, layoutDirection: LayoutDirection): PaddingValues {
    return side.value.exclude(this, layoutDirection)
}

@Composable
public fun PaddingValues.only(side: PaddingSide): PaddingValues {
    return only(side = side.value, LocalLayoutDirection.current)
}

public fun PaddingValues.only(side: PaddingSide, layoutDirection: LayoutDirection): PaddingValues {
    return side.value.only(this, layoutDirection)
}
