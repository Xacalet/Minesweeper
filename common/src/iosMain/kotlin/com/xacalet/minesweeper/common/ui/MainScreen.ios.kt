package com.xacalet.minesweeper.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.xacalet.minesweeper.common.data.GameRepository

@Composable
actual fun MainScreen() {
    val gameRepository = remember { GameRepository() }
    val gameData by gameRepository.gameData.collectAsState()
    gameData?.let {
        GameBoard(it, gameRepository)
    }
}
