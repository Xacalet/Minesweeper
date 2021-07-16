package com.xacalet.minesweeper.engine

import com.xacalet.minesweeper.model.Cell

class RandomMinePlanter : MinePlanter {

    override fun plantMines(
        rows: Int,
        columns: Int,
        mineCount: Int,
        pivotCell: Cell,
        cells: Array<Array<Cell>>
    ): List<Cell> {
        val nonEligibleCells = pivotCell.contiguousCells.map { contiguousCell ->
            contiguousCell.position.x + (contiguousCell.position.y * columns)
        }

        return (0 until rows * columns)
            .filter { index -> !nonEligibleCells.contains(index) }
            .shuffled()
            .take(mineCount)
            .map { index ->
                cells[index.rem(columns)][index.div(columns)].apply { isMine = true }
            }
    }
}
