package com.xacalet.minesweeper.common.ui.component.cells

import androidx.compose.runtime.Composable

@Composable
internal expect fun WrongFlagCellImage()

@Composable
internal fun WrongFlagCell() {
    UncoveredCell {
        WrongFlagCellImage()
    }
}
