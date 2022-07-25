package com.xacalet.minesweeper.common.ui.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.xacalet.minesweeper.common.R

actual object PainterResources {

    @Composable
    actual fun iconCellFlag(): Painter = painterResource(R.drawable.ic_cell_flag)

    @Composable
    actual fun iconCellMine(): Painter = painterResource(R.drawable.ic_cell_mine)

    @Composable
    actual fun iconCellWrongFlag(): Painter = painterResource(R.drawable.ic_cell_wrong_flag)

    @Composable
    actual fun iconDisplayMinus(): Painter = painterResource(R.drawable.ic_display_minus)

    @Composable
    actual fun iconDisplay0(): Painter = painterResource(R.drawable.ic_display_0)

    @Composable
    actual fun iconDisplay1(): Painter = painterResource(R.drawable.ic_display_1)

    @Composable
    actual fun iconDisplay2(): Painter = painterResource(R.drawable.ic_display_2)

    @Composable
    actual fun iconDisplay3(): Painter = painterResource(R.drawable.ic_display_3)

    @Composable
    actual fun iconDisplay4(): Painter = painterResource(R.drawable.ic_display_4)

    @Composable
    actual fun iconDisplay5(): Painter = painterResource(R.drawable.ic_display_5)

    @Composable
    actual fun iconDisplay6(): Painter = painterResource(R.drawable.ic_display_6)

    @Composable
    actual fun iconDisplay7(): Painter = painterResource(R.drawable.ic_display_7)

    @Composable
    actual fun iconDisplay8(): Painter = painterResource(R.drawable.ic_display_8)

    @Composable
    actual fun iconDisplay9(): Painter = painterResource(R.drawable.ic_display_9)

    @Composable
    actual fun iconSmileyDizzy(): Painter = painterResource(R.drawable.ic_smiley_dizzy)

    @Composable
    actual fun iconSmileySmiling(): Painter = painterResource(R.drawable.ic_smiley_smiling)

    @Composable
    actual fun iconSmileySunglasses(): Painter = painterResource(R.drawable.ic_smiley_sunglasses)

    @Composable
    actual fun iconSmileySurprised(): Painter = painterResource(R.drawable.ic_smiley_surprised)
}
