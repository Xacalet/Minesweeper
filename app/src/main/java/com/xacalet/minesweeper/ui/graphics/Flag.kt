package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Flag(modifier: Modifier = Modifier) {
    Canvas(modifier.fillMaxSize()) {
        drawRect(
            color = Color.Black,
            topLeft = Offset(size.width / 6f, size.height * 5f / 6f),
            size = Size(size.width * 2f / 3f, size.height / 12f)
        )
        drawRect(
            color = Color.Black,
            topLeft = Offset(size.width / 3f, size.height * 3f / 4f ),
            size = Size(size.width / 3f, size.height / 12f)
        )
        drawRect(
            color = Color.Black,
            topLeft = Offset(size.width * 0.5f, size.height * 0.25f ),
            size = Size(size.width / 12f, size.height * 0.5f)
        )
        drawPath(
            color = Color.Red,
            path = Path().apply {
                moveTo(size.width / 6f, size.height * 7f / 24f)
                lineTo(size.width * 7f / 12f, size.height / 12f)
                lineTo(size.width * 7f / 12f, size.height * 0.5f)
                lineTo(size.width / 6f, size.height * 7f / 24f)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FlagPreview() {
    Flag(Modifier.size(64.dp))
}
