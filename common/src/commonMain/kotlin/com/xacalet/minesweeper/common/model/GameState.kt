package com.xacalet.minesweeper.common.model

sealed interface GameState {
    object Uninitialized : GameState
    object InProgress : GameState
    object Won : GameState
    object Lost : GameState
}
