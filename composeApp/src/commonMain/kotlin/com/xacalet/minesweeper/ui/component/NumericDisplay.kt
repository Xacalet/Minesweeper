package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.ui.resources.PainterResources
import kotlin.math.absoluteValue

sealed class DisplayValue {
    object Minus : DisplayValue()
    class Number(val number: Int) : DisplayValue()
}

@Composable
internal fun SevenSegment(
    modifier: Modifier = Modifier,
    displayValue: DisplayValue
) {
    val painter: Painter = when (displayValue) {
        DisplayValue.Minus -> PainterResources.iconDisplayMinus()
        is DisplayValue.Number -> when (displayValue.number) {
            0 -> PainterResources.iconDisplay0()
            1 -> PainterResources.iconDisplay1()
            2 -> PainterResources.iconDisplay2()
            3 -> PainterResources.iconDisplay3()
            4 -> PainterResources.iconDisplay4()
            5 -> PainterResources.iconDisplay5()
            6 -> PainterResources.iconDisplay6()
            7 -> PainterResources.iconDisplay7()
            8 -> PainterResources.iconDisplay8()
            9 -> PainterResources.iconDisplay9()
            else -> throw IllegalArgumentException()
        }
    }
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = null
    )
}

@Composable
internal fun NumericDisplay(
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
