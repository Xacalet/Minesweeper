package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.ui.foundation.BevelType
import com.xacalet.minesweeper.ui.foundation.bevel
import com.xacalet.minesweeper.ui.theme.numericDisplayTextStyle

@Composable
fun NumericDisplay(value: Int) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .bevel(1.dp, type = BevelType.Lowered)
            .height(22.dp)
            .padding(1.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            modifier = Modifier.alpha(0.2f),
            text = "000",
            style = numericDisplayTextStyle
        )
        BasicText(
            text = "%03d".format(value.coerceIn(0, 999)),
            style = numericDisplayTextStyle
        )
    }
}

@Composable
@Preview
fun NumericDisplayPreview() {
    NumericDisplay(12)
}