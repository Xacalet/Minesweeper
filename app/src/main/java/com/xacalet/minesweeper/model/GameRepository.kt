package com.xacalet.minesweeper.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.math.max
import kotlin.math.min

//TODO: Create a repository interface from which this class must inherit from.
class GameRepository {

    private var difficulty: Difficulty = Difficulty.Beginner

    private val flags: MutableList<Point> = mutableListOf()

    private var mines: List<Point> = emptyList()

    private var uncoveredCellCount: Int = 0

    private var cells: Array<Array<CellData>> = emptyArray()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    var cellStates: Array<Array<MutableState<CellState>>> by mutableStateOf(emptyArray())
        private set

    var minesLeftCounter: Int by mutableStateOf(0)
        private set

    private val _timer = MutableStateFlow(0L)

    val timer: Flow<Int> = _timer.map { it.div(10).toInt() }

    var gameState: GameState by mutableStateOf(GameState.Uninitialized)
        private set

    var boardSize: Pair<Int, Int> by mutableStateOf(Pair(0, 0))

    init {
        startNewGame(Difficulty.Beginner)
    }

    fun startNewGame(difficulty: Difficulty) {
        // Reset values
        gameState = GameState.Uninitialized
        this.difficulty = difficulty
        _timer.value = 0
        flags.clear()
        mines = emptyList()
        uncoveredCellCount = difficulty.columns * difficulty.rows
        minesLeftCounter = difficulty.mineCount
        boardSize = difficulty.rows to difficulty.columns
        // Update board game
        cells = Array(difficulty.columns) { x ->
            Array(difficulty.rows) { y ->
                CellData(contiguousCells = getContiguousCells(x, y))
            }
        }
        cellStates = Array(difficulty.columns) { x ->
            Array(difficulty.rows) { y ->
                cells[x][y].state
            }
        }
        stopTimer()
    }

    fun onCellClick(x: Int, y: Int) {
        if (gameState == GameState.Lost || gameState == GameState.Won) {
            return
        }

        if (gameState == GameState.Uninitialized) {
            initializeGame(x, y)
            gameState = GameState.InProgress
            startTimer()
        }
        if (cells[x][y].isMine) {
            // Explode clicked cell.
            cells[x][y].state.value = CellState.Mine(true)
            // Mark undiscovered cells.
            mines.forEach { (x, y) ->
                if (cellStates[x][y].value == CellState.Covered) {
                    cellStates[x][y].value = CellState.Mine(false)
                }
            }
            // Mark wrongly flagged cells.
            flags.forEach { (x, y) ->
                if (!cells[x][y].isMine) {
                    cellStates[x][y].value = CellState.NotAMine
                }
            }
            // End game.
            gameState = GameState.Lost
            stopTimer()
        } else {
            when (cells[x][y].state.value) {
                CellState.Covered -> uncoverContiguousSafeCells(x, y)
                is CellState.Safe -> {
                    val contiguousFlagCount = cells[x][y].contiguousCells.count { (xC, yC) ->
                        cells[xC][yC].state.value == CellState.Flagged
                    }
                    val contiguousMineCount = cells[x][y].contiguousCells.count { (xC, yC) ->
                        cells[xC][yC].isMine
                    }
                    if (contiguousMineCount in 1..contiguousFlagCount) {
                        forceUncoverContiguousCells(x, y)
                    }
                }
                else -> Unit
            }
        }
        if (uncoveredCellCount == mines.count()) {
            gameState = GameState.Won
            stopTimer()
        }
    }

    /**
     * Manages a long click event made at cell ([x],[y]), and returns true if event was handled
     * or false otherwise.
     */
    fun onCellLongClick(x: Int, y: Int): Boolean {
        if (gameState == GameState.Lost || gameState == GameState.Won) {
            return false
        }

        if (cells[x][y].state.value == CellState.Flagged) {
            cells[x][y].state.value = CellState.Covered
            flags.remove(Point(x, y))
            minesLeftCounter++
        } else {
            cells[x][y].state.value = CellState.Flagged
            flags.add(Point(x, y))
            minesLeftCounter--
        }
        return true
    }

    private fun initializeGame(x0: Int, y0: Int) {
        val startingCellGroup = cells[x0][y0].contiguousCells.map { (x, y) ->
            x + (y * difficulty.columns)
        }
        mines = (0 until difficulty.rows * difficulty.columns)
            .asSequence()
            .shuffled()
            .filter { index -> !startingCellGroup.contains(index) }
            .take(difficulty.mineCount)
            .map { index ->
                val x = index.rem(difficulty.columns)
                val y = index.div(difficulty.columns)
                cells[x][y].isMine = true
                Point(x, y)
            }
            .toList()
    }

    private fun uncoverContiguousSafeCells(x0: Int, y0: Int) {
        val contiguousMineCount = cells[x0][y0].contiguousCells.count { (x, y) ->
            cells[x][y].isMine
        }
        cells[x0][y0].state.value = CellState.Safe(contiguousMineCount)
        uncoveredCellCount--
        if (contiguousMineCount == 0) {
            cells[x0][y0].contiguousCells.forEach { (x, y) ->
                if (cells[x][y].state.value == CellState.Covered && !cells[x][y].isMine) {
                    uncoverContiguousSafeCells(x, y)
                }
            }
        }
    }

    private fun forceUncoverContiguousCells(x0: Int, y0: Int) {
        cells[x0][y0].contiguousCells.forEach { (x, y) ->
            if (cells[x][y].state.value == CellState.Covered) {
                onCellClick(x, y)
            }
        }
    }

    private fun getContiguousCells(x0: Int, y0: Int, contiguity: Int = 1): List<Point> {
        val maxColumnIndex = difficulty.columns - 1
        val maxRowIndex = difficulty.rows - 1
        return (max(x0 - contiguity, 0)..min(x0 + contiguity, maxColumnIndex)).map { x ->
            (max(y0 - contiguity, 0)..min(y0 + contiguity, maxRowIndex)).map { y ->
                Point(x, y)
            }
        }.flatten()
    }

    private fun getTicker(): Flow<Unit> {
        return flow {
            while (true) {
                emit(Unit)
            }
        }.onEach { delay(100) }
    }

    fun startTimer() {
        if (gameState == GameState.InProgress) {
            coroutineScope.launch {
                getTicker().collect { _timer.value = _timer.value + 1 }
            }
        }
    }

    fun stopTimer() {
        coroutineScope.coroutineContext.job.cancelChildren()
    }


}

data class Point(val x: Int, val y: Int)

sealed class CellState {
    object Covered : CellState()
    object Flagged : CellState()
    class Safe(val contiguousMineCount: Int = 0) : CellState()
    class Mine(val exploded: Boolean = false) : CellState()
    object NotAMine : CellState()
}

sealed class Difficulty(val rows: Int, val columns: Int, val mineCount: Int) {
    object Beginner : Difficulty(12, 9, 20)
    object Intermediate : Difficulty(16, 16, 40)
    object Expert : Difficulty(16, 30, 99)
    class Custom(rows: Int, columns: Int, bombs: Int) : Difficulty(rows, columns, bombs)
}

sealed class GameState {
    object Uninitialized : GameState()
    object InProgress : GameState()
    object Won : GameState()
    object Lost : GameState()
}

data class CellData(
    val state: MutableState<CellState> = mutableStateOf(CellState.Covered),
    var contiguousCells: List<Point> = emptyList(),
    var isMine: Boolean = false
)
