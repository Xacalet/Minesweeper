package com.xacalet.minesweeper.common.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
internal actual fun SevenSegment(
    modifier: Modifier,
    displayValue: DisplayValue
) {
    val resourcePath: String = when (displayValue) {
        DisplayValue.Minus -> ""
        is DisplayValue.Number -> when (displayValue.number) {
            0 -> ""
            else -> throw IllegalArgumentException()
        }
    }
    Image(
        modifier = modifier,
        painter = painterResource(resourcePath),
        contentDescription = null
    )
}
