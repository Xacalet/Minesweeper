package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.ui.foundation.BevelType
import com.xacalet.minesweeper.ui.foundation.bevel

@Composable
// TODO: Use font instead of canvas
fun NumericDisplay(value: Int) {
    val coercedValue = value.coerceIn(-99, 999)
    Row(modifier = Modifier
            .background(Color.Black)
            .bevel(1.dp, type = BevelType.Lowered)
            .padding(2.dp)
    ) {
        SevenSegment(
            modifier = Modifier
                .height(36.dp)
                .width(20.dp),
            number = coercedValue.div(100).rem(10)
        )
        Spacer(modifier = Modifier.size(4.dp))
        SevenSegment(
            modifier = Modifier
                .height(36.dp)
                .width(20.dp),
            number = coercedValue.div(10).rem(10)
        )
        Spacer(modifier = Modifier.size(4.dp))
        SevenSegment(
            modifier = Modifier
                .height(36.dp)
                .width(20.dp),
            number = coercedValue % 10
        )
    }
}

@Composable
@Preview
fun NumericDisplayPreview() {
    NumericDisplay(12)
}