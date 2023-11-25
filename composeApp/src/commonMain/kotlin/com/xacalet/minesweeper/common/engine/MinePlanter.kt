package com.xacalet.minesweeper.common.engine

import com.xacalet.minesweeper.common.model.Cell

interface MinePlanter {

    fun plantMines(
        rows: Int,
        columns: Int,
        mineCount: Int,
        pivotCell: Cell,
        cells: Array<Array<Cell>>
    ): List<Cell>
}
