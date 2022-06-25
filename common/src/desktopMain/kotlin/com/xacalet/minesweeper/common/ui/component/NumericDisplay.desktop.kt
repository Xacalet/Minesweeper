package com.xacalet.minesweeper.common.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
internal actual fun SevenSegment(
    modifier: Modifier,
    displayValue: DisplayValue
) {
    val resourcePath: String = when (displayValue) {
        DisplayValue.Minus -> "display_minus.svg"
        is DisplayValue.Number -> when (displayValue.number) {
            0 -> "display_0.svg"
            1 -> "display_1.svg"
            2 -> "display_2.svg"
            3 -> "display_3.svg"
            4 -> "display_4.svg"
            5 -> "display_5.svg"
            6 -> "display_6.svg"
            7 -> "display_7.svg"
            8 -> "display_8.svg"
            9 -> "display_9.svg"
            else -> throw IllegalArgumentException()
        }
    }
    Image(
        modifier = Modifier.height(65.dp).width(24.dp),
        painter = painterResource(resourcePath),
        contentDescription = null
    )
}
