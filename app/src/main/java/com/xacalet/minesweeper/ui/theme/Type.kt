package com.xacalet.minesweeper.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.xacalet.minesweeper.R

val contiguousMineCountTextStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.mine_sweeper)),
    fontSize = 18.sp,
    baselineShift = BaselineShift(-0.16f),
    textAlign = TextAlign.Center
)
