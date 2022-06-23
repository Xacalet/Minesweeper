package com.xacalet.minesweeper.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.R
import kotlin.math.absoluteValue

sealed class DisplayValue {
    object Minus : DisplayValue()
    class Number(val number: Int) : DisplayValue()
}

@Composable
fun SevenSegment(
    modifier: Modifier = Modifier,
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
fun NumericDisplay(
    modifier: Modifier = Modifier,
    value: Int
) {
    val rangedValue = value.coerceIn(-99, 999)
    val displayValues = listOf(
        if (rangedValue < 0) {
            DisplayValue.Minus
        } else {
            DisplayValue.Number(rangedValue.absoluteValue.div(100).rem(10))
        },
        DisplayValue.Number(rangedValue.absoluteValue.div(10).rem(10)),
        DisplayValue.Number(rangedValue.absoluteValue % 10)
    )
    Row(
        modifier = modifier
            .background(Color.Black)
            .bevel(1.dp, type = BevelType.Lowered)
            .padding(2.dp)
    ) {
        SevenSegment(displayValue = displayValues[0])
        Spacer(modifier = Modifier.size(4.dp))
        SevenSegment(displayValue = displayValues[1])
        Spacer(modifier = Modifier.size(4.dp))
        SevenSegment(displayValue = displayValues[2])
    }
}

@Composable
@Preview
fun NumericDisplayPreview() {
    NumericDisplay(Modifier, -84)
}
