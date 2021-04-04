package com.xacalet.minesweeper.ui.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
fun Mine(
    modifier: Modifier = Modifier,
    hasExploded: Boolean
) {
    Canvas(
        modifier
            .fillMaxSize()
            .background(if (hasExploded) Color.Red else Color.Transparent)
            .padding(4.dp)) {
        mineDrawable()
    }
}

@Composable
fun NotAMine(modifier: Modifier = Modifier) {
    Canvas(
        modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        mineDrawable()
        val width = size.minDimension * 0.10f
        drawLine(Color.Red, Offset(0f, 0f), Offset(size.minDimension, size.minDimension), width)
        drawLine(Color.Red, Offset(0f, size.minDimension), Offset(size.minDimension, 0f), width)
    }
}

@Preview
@Composable
fun FlagPreview() {
    Row {
      Mine(Modifier.size(32.dp), false)
      Spacer(Modifier.size(4.dp))
      Mine(Modifier.size(32.dp), true)
      Spacer(Modifier.size(4.dp))
      NotAMine(Modifier.size(32.dp))
    }
}
