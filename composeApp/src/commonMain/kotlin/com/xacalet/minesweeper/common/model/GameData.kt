package com.xacalet.minesweeper.common.model

import kotlinx.coroutines.flow.StateFlow

@Suppress("ArrayInDataClass")
data class GameData(
    val boardSize: BoardDimension,
    val state: StateFlow<GameState>,
    val cellStates: Array<Array<StateFlow<CellState>>>,
    val elapsedSeconds: StateFlow<Int>,
    val minesLeftCounter: StateFlow<Int>
)
