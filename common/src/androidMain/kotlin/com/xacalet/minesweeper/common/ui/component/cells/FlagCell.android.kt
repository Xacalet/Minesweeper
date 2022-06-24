package com.xacalet.minesweeper.common.ui.component.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.common.R

@Composable
internal actual fun FlagCellImage(modifier: Modifier) {
    Image(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        painter = painterResource(id = R.drawable.ic_cell_flag),
        contentDescription = null
    )
}
