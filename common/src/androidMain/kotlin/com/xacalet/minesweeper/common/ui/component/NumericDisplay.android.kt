package com.xacalet.minesweeper.common.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.xacalet.minesweeper.common.R

@Composable
internal actual fun SevenSegment(
    modifier: Modifier,
    displayValue: DisplayValue
) {
    @DrawableRes val resource: Int = when (displayValue) {
        DisplayValue.Minus -> R.drawable.ic_display_minus
        is DisplayValue.Number -> when (displayValue.number) {
            0 -> R.drawable.ic_display_0
            1 -> R.drawable.ic_display_1
            2 -> R.drawable.ic_display_2
            3 -> R.drawable.ic_display_3
            4 -> R.drawable.ic_display_4
            5 -> R.drawable.ic_display_5
            6 -> R.drawable.ic_display_6
            7 -> R.drawable.ic_display_7
            8 -> R.drawable.ic_display_8
            9 -> R.drawable.ic_display_9
            else -> throw IllegalArgumentException()
        }
    }
    Image(
        modifier = modifier,
        painter = painterResource(resource),
        contentDescription = null
    )
}

@Composable
@Preview
private fun NumericDisplayPreview() {
    NumericDisplay(Modifier, -84)
}
