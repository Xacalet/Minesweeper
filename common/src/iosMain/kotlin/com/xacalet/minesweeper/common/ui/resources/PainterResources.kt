package com.xacalet.minesweeper.common.ui.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter

private fun dummyIconCell(): Painter = BitmapPainter(
    image = ImageBitmap(width = 17, height = 17)
)

private fun dummyIconDisplay(): Painter = BitmapPainter(
    image = ImageBitmap(width = 29, height = 55)
)

private fun dummyIconSmiley(): Painter = BitmapPainter(
    image = ImageBitmap(width = 22, height = 22)
)

actual object PainterResources {
    @Composable
    actual fun iconCellFlag(): Painter = dummyIconCell()

    @Composable
    actual fun iconCellMine(): Painter = dummyIconCell()

    @Composable
    actual fun iconCellWrongFlag(): Painter = dummyIconCell()

    @Composable
    actual fun iconDisplayMinus(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay0(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay1(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay2(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay3(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay4(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay5(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay6(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay7(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay8(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconDisplay9(): Painter = dummyIconDisplay()

    @Composable
    actual fun iconSmileyDizzy(): Painter = dummyIconSmiley()

    @Composable
    actual fun iconSmileySmiling(): Painter = dummyIconSmiley()

    @Composable
    actual fun iconSmileySunglasses(): Painter = dummyIconSmiley()

    @Composable
    actual fun iconSmileySurprised(): Painter = dummyIconSmiley()
}
