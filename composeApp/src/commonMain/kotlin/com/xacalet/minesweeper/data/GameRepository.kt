package com.xacalet.minesweeper.data

import com.xacalet.minesweeper.model.Difficulty
import com.xacalet.minesweeper.model.GameData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameRepository {

    private lateinit var game: com.xacalet.minesweeper.engine.Game

    private val _gameData: MutableStateFlow<GameData?> = MutableStateFlow(null)
    val gameData: StateFlow<GameData?> = _gameData

    init {
        newGame(Difficulty.Beginner)
    }

    private fun newGame(difficulty: Difficulty) {
        game = com.xacalet.minesweeper.engine.Game(difficulty)
        _gameData.value = GameData(
            boardSize = difficulty.boardSize,
            state = game.state,
            cellStates = game.cellStates,
            elapsedSeconds = game.elapsedSeconds,
            minesLeftCounter = game.minesLeftCounter
        )
    }

    fun resetGame() {
        newGame(game.difficulty)
    }

    fun onCellClick(x: Int, y: Int) {
        game.uncoverCell(x, y)
    }

    /**
     * Manages a long click event made at cell ([x],[y]), and returns true if event was handled
     * or false otherwise.
     */
    fun onCellLongClick(x: Int, y: Int): Boolean {
        return game.toggleFlag(x, y)
    }

    fun resumeTimer() {
        game.resume()
    }

    fun pauseTimer() {
        game.pause()
    }
}
