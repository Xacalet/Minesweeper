package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.ui.foundation.BevelType
import com.xacalet.minesweeper.ui.foundation.bevel
import com.xacalet.minesweeper.ui.theme.contiguousMineCountTextStyle




@Composable
fun UncoveredCell(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .padding(start = 1.dp, top = 1.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun CoveredCell(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .bevel(width = 1.dp, type = BevelType.Raised)
            .background(Color.LightGray)
            .clickable { onClick() }
    )
}

@Composable
fun SafeCell(contiguousMineCount: Int) {
    UncoveredCell {
        if (contiguousMineCount > 0) {
            Text(
                modifier = Modifier.fillMaxSize(),
                style = contiguousMineCountTextStyle,
                color = getTextColorByContiguousMineCount(contiguousMineCount),
                text = contiguousMineCount.toString()
            )
        }
    }
}

private fun getTextColorByContiguousMineCount(contiguousMineCount: Int): Color =
    when (contiguousMineCount) {
        1 -> Color(0xFF0000FF)
        2 -> Color(0xFF007F00)
        3 -> Color(0xFFFF0000)
        4 -> Color(0xFF00007F)
        5 -> Color(0xFF7F0000)
        6 -> Color(0xFF007F7F)
        7 -> Color(0xFF000000)
        8 -> Color(0xFF7F7F7F)
        else -> Color(0x00000000)
    }

private fun DrawScope.mineDrawable() {
    val radius: Float = size.minDimension * 0.4f
    val xy1: Float = size.minDimension * 0.15f
    val xy2: Float = size.minDimension * 0.85f
    val width = size.minDimension * 0.10f

    drawCircle(Color.Black, center = size.center, radius = radius)
    drawLine(Color.Black, Offset(xy1, xy1), Offset(xy2, xy2), width)
    drawLine(Color.Black, Offset(xy2, xy1), Offset(xy1, xy2), width)
    drawLine(Color.Black, Offset(0f, size.center.y), Offset(size.width, size.center.y), width)
    drawLine(Color.Black, Offset(size.center.x, 0f), Offset(size.center.x, size.height), width)
    drawCircle(
        Color.White,
        center = size.center - Offset(radius * 0.4f, radius * 0.4f),
        radius = radius.div(5)
    )
}

@Composable
fun MineCell(exploded: Boolean) {
    UncoveredCell {
        Canvas(Modifier
            .fillMaxSize()
            .background(if (exploded) Color.Red else Color.Transparent)
            .padding(2.dp)) {
            mineDrawable()
        }
    }
}

@Composable
fun BadFlaggedMine(modifier: Modifier = Modifier) {
    UncoveredCell {
        Canvas(modifier = modifier.padding(4.dp)) {
            mineDrawable()
            val width = size.minDimension * 0.10f
            drawLine(Color.Red, Offset(0f, 0f), Offset(size.minDimension, size.minDimension), width)
            drawLine(Color.Red, Offset(0f, size.minDimension), Offset(size.minDimension, 0f), width)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MinePreview() {
    Column {
        Row {
            MineCell(false)
            MineCell(true)
            BadFlaggedMine()
        }
        Row {
            SafeCell(0)
            SafeCell(1)
            SafeCell(2)
        }
        Row {
            SafeCell(3)
            SafeCell(4)
            SafeCell(5)
        }
        Row {
            SafeCell(6)
            SafeCell(7)
            SafeCell(8)
        }
    }
}
