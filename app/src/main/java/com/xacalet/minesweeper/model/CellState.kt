package com.xacalet.minesweeper.model

sealed class CellState {
    object Covered : CellState()
    object Flagged : CellState()
    class Safe(val contiguousMineCount: Int = 0) : CellState()
    class Mine(val exploded: Boolean = false) : CellState()
    object NotAMine : CellState()
}