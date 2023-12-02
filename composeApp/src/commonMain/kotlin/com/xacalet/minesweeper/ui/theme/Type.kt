package com.xacalet.minesweeper.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

var mineSweeperFontFamily: FontFamily? = null

object Type {
    val contiguousMineCountTextStyle: TextStyle
        @Composable get() {
            return TextStyle(
                fontFamily = mineSweeperFontFamily,
                fontSize = 18.sp,
                baselineShift = BaselineShift(-0.16f),
                textAlign = TextAlign.Center
            )
        }
}
