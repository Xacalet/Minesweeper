package com.xacalet.minesweeper.common.ui.component.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
internal actual fun WrongFlagCellImage() {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        painter = painterResource("cell_wrong_flag.svg"),
        contentDescription = null
    )
}
