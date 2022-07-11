package com.xacalet.minesweeper.common.ui.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

actual object PainterResources {

    @Composable
    actual fun iconCellFlag(): Painter = painterResource("cell_flag.svg")

    @Composable
    actual fun iconCellMine(): Painter = painterResource("cell_mine.svg")

    @Composable
    actual fun iconCellWrongFlag(): Painter = painterResource("cell_wrong_flag.svg")

    @Composable
    actual fun iconDisplayMinus(): Painter = painterResource("display_minus.svg")

    @Composable
    actual fun iconDisplay0(): Painter = painterResource("display_0.svg")

    @Composable
    actual fun iconDisplay1(): Painter = painterResource("display_1.svg")

    @Composable
    actual fun iconDisplay2(): Painter = painterResource("display_2.svg")

    @Composable
    actual fun iconDisplay3(): Painter = painterResource("display_3.svg")

    @Composable
    actual fun iconDisplay4(): Painter = painterResource("display_4.svg")

    @Composable
    actual fun iconDisplay5(): Painter = painterResource("display_5.svg")

    @Composable
    actual fun iconDisplay6(): Painter = painterResource("display_6.svg")

    @Composable
    actual fun iconDisplay7(): Painter = painterResource("display_7.svg")

    @Composable
    actual fun iconDisplay8(): Painter = painterResource("display_8.svg")

    @Composable
    actual fun iconDisplay9(): Painter = painterResource("display_9.svg")

    @Composable
    actual fun iconSmileyDizzy(): Painter = painterResource("smiley_dizzy.svg")

    @Composable
    actual fun iconSmileySmiling(): Painter = painterResource("smiley_smiling.svg")

    @Composable
    actual fun iconSmileySunglasses(): Painter = painterResource("smiley_sunglasses.svg")

    @Composable
    actual fun iconSmileySurprised(): Painter = painterResource("smiley_surprised.svg")
}
