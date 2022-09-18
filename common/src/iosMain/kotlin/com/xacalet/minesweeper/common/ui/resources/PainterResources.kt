package com.xacalet.minesweeper.common.ui.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter

actual object PainterResources {
    @Composable
    actual fun iconCellFlag(): Painter = BitmapPainter(
        image = ImageBitmap(width = 17, height = 17)
    )

    @Composable
    actual fun iconCellMine(): Painter = BitmapPainter(
        image = ImageBitmap(width = 17, height = 17)
    )

    @Composable
    actual fun iconCellWrongFlag(): Painter = BitmapPainter(
        image = ImageBitmap(width = 17, height = 17)
    )

    @Composable
    actual fun iconDisplayMinus(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay0(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay1(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay2(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay3(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay4(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay5(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay6(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay7(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay8(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconDisplay9(): Painter = BitmapPainter(
        image = ImageBitmap(width = 29, height = 55)
    )

    @Composable
    actual fun iconSmileyDizzy(): Painter = BitmapPainter(
        image = ImageBitmap(width = 22, height = 22)
    )

    @Composable
    actual fun iconSmileySmiling(): Painter = BitmapPainter(
        image = ImageBitmap(width = 22, height = 22)
    )

    @Composable
    actual fun iconSmileySunglasses(): Painter = BitmapPainter(
        image = ImageBitmap(width = 22, height = 22)
    )

    @Composable
    actual fun iconSmileySurprised(): Painter = BitmapPainter(
        image = ImageBitmap(width = 22, height = 22)
    )
}
