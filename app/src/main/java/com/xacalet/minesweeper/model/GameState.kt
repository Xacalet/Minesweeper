package com.xacalet.minesweeper.model

sealed interface GameState {
    object Uninitialized : GameState
    object InProgress : GameState
    object Won : GameState
    object Lost : GameState
}
