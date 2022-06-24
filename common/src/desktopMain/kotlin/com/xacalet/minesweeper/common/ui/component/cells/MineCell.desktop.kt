package com.xacalet.minesweeper.common.ui.component.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
internal actual fun MineCellImage(hasExploded: Boolean) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .background(if (hasExploded) Color.Red else Color.Transparent)
            .padding(4.dp),
        painter = painterResource("cell_mine.svg"),
        contentDescription = null
    )
}
