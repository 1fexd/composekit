package fe.material3.compat

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import fe.material3.compat.end
import fe.material3.compat.tokens.ShapeKeyTokens
import androidx.compose.material3.Shapes as M3Shapes


internal fun CornerBasedShape.top(
    bottomSize: CornerSize = CornerSize(0.0.dp)
): CornerBasedShape {
    return copy(bottomStart = bottomSize, bottomEnd = bottomSize)
}

internal fun CornerBasedShape.bottom(
    topSize: CornerSize = CornerSize(0.0.dp)
): CornerBasedShape {
    return copy(topStart = topSize, topEnd = topSize)
}

internal fun CornerBasedShape.start(
    endSize: CornerSize = CornerSize(0.0.dp)
): CornerBasedShape {
    return copy(topEnd = endSize, bottomEnd = endSize)
}

internal fun CornerBasedShape.end(
    startSize: CornerSize = CornerSize(0.0.dp)
): CornerBasedShape {
    return copy(topStart = startSize, bottomStart = startSize)
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
internal fun M3Shapes.fromToken(value: ShapeKeyTokens): Shape {
    return when (value) {
        ShapeKeyTokens.CornerExtraLarge -> extraLarge
        ShapeKeyTokens.CornerExtraLargeIncreased -> extraLargeIncreased
        ShapeKeyTokens.CornerExtraExtraLarge -> extraExtraLarge
        ShapeKeyTokens.CornerExtraLargeTop -> extraLarge.top()
        ShapeKeyTokens.CornerExtraSmall -> extraSmall
        ShapeKeyTokens.CornerExtraSmallTop -> extraSmall.top()
        ShapeKeyTokens.CornerFull -> CircleShape
        ShapeKeyTokens.CornerLarge -> large
        ShapeKeyTokens.CornerLargeIncreased -> largeIncreased
        ShapeKeyTokens.CornerLargeEnd -> large.end()
        ShapeKeyTokens.CornerLargeTop -> large.top()
        ShapeKeyTokens.CornerMedium -> medium
        ShapeKeyTokens.CornerNone -> RectangleShape
        ShapeKeyTokens.CornerSmall -> small
        ShapeKeyTokens.CornerLargeStart -> large.start()
    }
}

public val ShapeKeyTokens.value: Shape
    @Composable @ReadOnlyComposable get() = MaterialTheme.shapes.fromToken(this)
