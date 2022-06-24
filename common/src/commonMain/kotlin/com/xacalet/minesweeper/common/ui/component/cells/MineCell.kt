package com.xacalet.minesweeper.common.ui.component.cells

import androidx.compose.runtime.Composable

@Composable
internal expect fun MineCellImage(hasExploded: Boolean)

@Composable
internal fun MineCell(hasExploded: Boolean) {
    UncoveredCell {
        MineCellImage(hasExploded)
    }
}
