package com.xacalet.minesweeper.common.ui.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
object PainterResources {

    @Composable
    fun iconCellFlag(): Painter = painterResource("drawable/ic_cell_flag.xml")

    @Composable
    fun iconCellMine(): Painter = painterResource("drawable/ic_cell_mine.xml")

    @Composable
    fun iconCellWrongFlag(): Painter = painterResource("drawable/ic_cell_wrong_flag.xml")

    @Composable
    fun iconDisplayMinus(): Painter = painterResource("drawable/ic_display_minus.xml")

    @Composable
    fun iconDisplay0(): Painter = painterResource("drawable/ic_display_0.xml")

    @Composable
    fun iconDisplay1(): Painter = painterResource("drawable/ic_display_1.xml")

    @Composable
    fun iconDisplay2(): Painter = painterResource("drawable/ic_display_2.xml")

    @Composable
    fun iconDisplay3(): Painter = painterResource("drawable/ic_display_3.xml")

    @Composable
    fun iconDisplay4(): Painter = painterResource("drawable/ic_display_4.xml")

    @Composable
    fun iconDisplay5(): Painter = painterResource("drawable/ic_display_5.xml")

    @Composable
    fun iconDisplay6(): Painter = painterResource("drawable/ic_display_6.xml")

    @Composable
    fun iconDisplay7(): Painter = painterResource("drawable/ic_display_7.xml")

    @Composable
    fun iconDisplay8(): Painter = painterResource("drawable/ic_display_8.xml")

    @Composable
    fun iconDisplay9(): Painter = painterResource("drawable/ic_display_9.xml")

    @Composable
    fun iconSmileyDizzy(): Painter = painterResource("drawable/ic_smiley_dizzy.xml")

    @Composable
    fun iconSmileySmiling(): Painter = painterResource("drawable/ic_smiley_smiling.xml")

    @Composable
    fun iconSmileySunglasses(): Painter = painterResource("drawable/ic_smiley_sunglasses.xml")

    @Composable
    fun iconSmileySurprised(): Painter = painterResource("drawable/ic_smiley_surprised.xml")
}
