package com.xacalet.minesweeper.common.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

val mineSweeperFont = Font(
    resource = "mine_sweeper.ttf",
    weight = FontWeight.Medium
)

actual val mineSweeperFontFamily = FontFamily(mineSweeperFont)
