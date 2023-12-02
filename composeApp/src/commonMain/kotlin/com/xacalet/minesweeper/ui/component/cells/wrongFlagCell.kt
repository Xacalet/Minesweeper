package com.xacalet.minesweeper.ui.component.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.ui.resources.PainterResources

@Composable
internal fun WrongFlagCell() {
    UncoveredCell {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            painter = PainterResources.iconCellWrongFlag(),
            contentDescription = null
        )
    }
}
