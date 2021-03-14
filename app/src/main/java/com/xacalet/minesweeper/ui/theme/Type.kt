package com.xacalet.minesweeper.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.xacalet.minesweeper.R

val Digital7 = FontFamily(
        Font(R.font.digital_7)
)
val DSEG7MiniClassic = FontFamily(
        Font(R.font.dseg7_classic_mini_bold)
)
val Segment7 = FontFamily(
        Font(R.font.segment_7)
)
val MineSweeperFont = FontFamily(
        Font(R.font.mine_sweeper)
)

val numericDisplayTextStyle = TextStyle(
        color = Color.Red,
        fontFamily = FontFamily(Font(R.font.segment_7)),
        fontSize = 24.sp,
        textAlign = TextAlign.Center
)

val contiguousMineCountTextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.mine_sweeper)),
        fontSize = 18.sp,
        baselineShift = BaselineShift(-0.16f),
        textAlign = TextAlign.Center
)

// Set of Material typography styles to start with
val typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)