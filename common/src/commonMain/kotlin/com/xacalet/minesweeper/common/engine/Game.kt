package com.xacalet.minesweeper.common.engine

import com.xacalet.minesweeper.common.extensions.forEach
import com.xacalet.minesweeper.common.extensions.matrixOf
import com.xacalet.minesweeper.common.model.Cell
import com.xacalet.minesweeper.common.model.CellState
import com.xacalet.minesweeper.common.model.Difficulty
import com.xacalet.minesweeper.common.model.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.max
import kotlin.math.min

class Game(
    val difficulty: Difficulty,
    private val minePlanter: MinePlanter = RandomMinePlanter()
) {
    private val mineCount: Int = difficulty.mineCount

    private val rows: Int = difficulty.boardSize.rows

    private val columns: Int = difficulty.boardSize.columns

    private val mineCells: MutableList<Cell> = mutableListOf()

    private val flaggedCells: MutableList<Cell> = mutableListOf()

    private val timer: Timer = Timer()

    private var uncoveredCellCount: Int = 0

    private var _minesLeftCounter = MutableStateFlow(0)
    val minesLeftCounter: StateFlow<Int> = _minesLeftCounter

    private val _state: MutableStateFlow<GameState> = MutableStateFlow(GameState.Uninitialized)
    val state: StateFlow<GameState> = _state

    val elapsedSeconds: StateFlow<Int> = timer.seconds

    private val cells: Array<Array<Cell>> = matrixOf(rows, columns) { x, y -> Cell(x, y) }
    val cellStates: Array<Array<StateFlow<CellState>>> =
        matrixOf(rows, columns) { x, y -> cells[x][y].state }

    init {
        cells.forEach { x, y, cell ->
            cell.contiguousCells = getContiguousCellsAtCoordinates(x, y)
        }
        _minesLeftCounter.value = difficulty.mineCount
    }

    fun uncoverCell(x: Int, y: Int) {
        uncoverCell(cells[x][y])
    }

    fun toggleFlag(x: Int, y: Int) = toggleFlag(cells[x][y])

    private fun uncoverCell(cell: Cell) {
        if (state.value == GameState.Lost || state.value == GameState.Won) {
            return
        }

        if (state.value == GameState.Uninitialized) {
            mineCells.addAll(minePlanter.plantMines(rows, columns, mineCount, cell, cells))
            _state.value = GameState.InProgress
            timer.start()
        }

        if (cell.isMine) {
            uncoverMineCell(cell)
        } else {
            uncoverSafeCell(cell)
        }
    }

    private fun toggleFlag(cell: Cell): Boolean {
        if (state.value == GameState.Lost || state.value == GameState.Won) {
            return false
        }

        if (cell.state.value == CellState.Flagged) {
            cell.state.value = CellState.Covered
            flaggedCells.remove(cell)
            _minesLeftCounter.value++
        } else {
            cell.state.value = CellState.Flagged
            flaggedCells.add(cell)
            _minesLeftCounter.value--
        }
        return true
    }

    private fun uncoverSafeCell(cell: Cell) {
        when (cell.state.value) {
            CellState.Covered -> uncoverContiguousSafeCells(cell)
            is CellState.Safe -> {
                val contiguousFlagCount = cell.contiguousCells.count { contiguousCell ->
                    contiguousCell.state.value == CellState.Flagged
                }
                val contiguousMineCount =
                    cell.contiguousCells.count { contiguousCell -> contiguousCell.isMine }
                if (contiguousMineCount in 1..contiguousFlagCount) {
                    forceUncoverContiguousCells(cell)
                }
            }
            else -> Unit
        }
        if (uncoveredCellCount == (rows * columns) - mineCells.count()) {
            finishGame(true)
        }
    }

    private fun uncoverMineCell(cell: Cell) {
        cell.state.value = CellState.Mine(true)
        uncoverMineCells()
        markWronglyFlaggedCells()
        finishGame(false)
    }

    private fun uncoverContiguousSafeCells(cell: Cell) {
        val contiguousMineCount = cell.contiguousCells.count { it.isMine }
        cell.state.value = CellState.Safe(contiguousMineCount)
        uncoveredCellCount++
        if (contiguousMineCount == 0) {
            cell.contiguousCells.forEach { contiguousCell ->
                if (contiguousCell.state.value == CellState.Covered && !contiguousCell.isMine) {
                    uncoverContiguousSafeCells(contiguousCell)
                }
            }
        }
    }

    private fun forceUncoverContiguousCells(cell: Cell) {
        cell.contiguousCells.forEach { contiguousCell ->
            if (contiguousCell.state.value == CellState.Covered) {
                uncoverCell(contiguousCell)
            }
        }
    }

    private fun uncoverMineCells() {
        mineCells.forEach { mineCell ->
            if (mineCell.state.value == CellState.Covered) {
                mineCell.state.value = CellState.Mine(false)
            }
        }
    }

    private fun markWronglyFlaggedCells() {
        flaggedCells.forEach { flaggedCell ->
            if (!flaggedCell.isMine) {
                flaggedCell.state.value = CellState.NotAMine
            }
        }
    }

    private fun finishGame(hasWon: Boolean) {
        _state.value = if (hasWon) GameState.Won else GameState.Lost
        timer.stop()
    }

    fun pause() {
        timer.stop()
    }

    fun resume() {
        if (_state.value == GameState.InProgress) {
            timer.start()
        }
    }

    private fun getContiguousCellsAtCoordinates(x0: Int, y0: Int): List<Cell> =
        (max(x0 - 1, 0)..min(x0 + 1, columns - 1)).map { x ->
            (max(y0 - 1, 0)..min(y0 + 1, rows - 1)).map { y ->
                cells[x][y]
            }
        }.flatten()
}
