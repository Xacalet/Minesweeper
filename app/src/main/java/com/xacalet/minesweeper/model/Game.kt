package com.xacalet.minesweeper.model

import kotlinx.coroutines.flow.MutableStateFlow

data class Point(val x: Int, val y: Int)

class Game(
    private val difficulty: Difficulty
) {

    private val bombCells: List<Point>

    private val _boardState: Array<Array<CellState>>
    val boardState: MutableStateFlow<Array<Array<CellState>>>

    init {
        val cells = difficulty.rows * difficulty.columns
        _boardState = Array(difficulty.columns) { Array(difficulty.rows) { CellState.Covered() } }
        boardState = MutableStateFlow(_boardState)

        val bombs = (0 until cells).shuffled().take(difficulty.bombs)
        bombCells = bombs.map(::arrayIndexToPoint)
    }

    fun uncoverCell(position: Point) {
        if (bombCells.contains(position)) {
            _boardState[position.x][position.y] = CellState.Mine(true)
            // TODO: Endgame
        } else {
            val contiguousBombs = position.getContiguousCells().count { point ->
                bombCells.contains(point)
            }
            _boardState[position.x][position.y] = CellState.Safe(contiguousBombs)
            if (contiguousBombs == 0) {

            }

            // TODO: Check if there are any more cells left to reveal

            // TODO: Check massive reveal of cells
        }
        boardState.value = _boardState.clone()
    }

    private fun Point.getContiguousCells(contiguity: Int = 1): List<Point> =
        ((x - contiguity).coerceAtLeast(0)..(x + contiguity).coerceAtMost(difficulty.columns - 1)).map { x ->
            ((y - contiguity).coerceAtLeast(0)..(y + contiguity).coerceAtMost(difficulty.rows - 1)).map { y ->
                Point(x, y)
            }
        }.flatten().filter { it != this }

    private fun arrayIndexToPoint(arrayIndex: Int) =
        Point(arrayIndex.rem(difficulty.columns), arrayIndex.div(difficulty.columns))

    sealed class Difficulty(val rows: Int, val columns: Int, val bombs: Int) {
        object Beginner : Difficulty(9, 9, 10)
        object Intermediate : Difficulty(16, 16, 40)
        object Expert : Difficulty(16, 30, 99)
        class Custom(rows: Int, columns: Int, bombs: Int) : Difficulty(rows, columns, bombs)
    }

    sealed class CellState {
        class Covered(val flagged: Boolean = false) : CellState()
        class Safe(val contiguousMineCount: Int = 0) : CellState()
        class Mine(val exploded: Boolean = false) : CellState()
        object WrongFlag : CellState()
    }
}
