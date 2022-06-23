package com.xacalet.minesweeper.model

import kotlinx.coroutines.flow.MutableStateFlow

class Cell(x: Int, y: Int) {
    val position = Point(x, y)

    var contiguousCells: List<Cell> = emptyList()

    val state: MutableStateFlow<CellState> = MutableStateFlow(CellState.Covered)

    var isMine: Boolean = false
}
