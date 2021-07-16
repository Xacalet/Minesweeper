package com.xacalet.minesweeper.engine

import com.xacalet.minesweeper.model.Cell

interface MinePlanter {

    fun plantMines(
        rows: Int,
        columns: Int,
        mineCount: Int,
        pivotCell: Cell,
        cells: Array<Array<Cell>>
    ): List<Cell>
}
