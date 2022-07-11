package com.xacalet.minesweeper.common.ui.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

expect object PainterResources {

    @Composable
    fun iconCellFlag(): Painter

    @Composable
    fun iconCellMine(): Painter

    @Composable
    fun iconCellWrongFlag(): Painter

    @Composable
    fun iconDisplayMinus(): Painter

    @Composable
    fun iconDisplay0(): Painter

    @Composable
    fun iconDisplay1(): Painter

    @Composable
    fun iconDisplay2(): Painter

    @Composable
    fun iconDisplay3(): Painter

    @Composable
    fun iconDisplay4(): Painter

    @Composable
    fun iconDisplay5(): Painter

    @Composable
    fun iconDisplay6(): Painter

    @Composable
    fun iconDisplay7(): Painter

    @Composable
    fun iconDisplay8(): Painter

    @Composable
    fun iconDisplay9(): Painter

    @Composable
    fun iconSmileyDizzy(): Painter

    @Composable
    fun iconSmileySmiling(): Painter

    @Composable
    fun iconSmileySunglasses(): Painter

    @Composable
    fun iconSmileySurprised(): Painter
}
