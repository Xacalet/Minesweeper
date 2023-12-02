package com.xacalet.minesweeper.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.xacalet.minesweeper.ui.theme.background

@Composable
actual fun MainScreen() {
    val gameRepository = remember { com.xacalet.minesweeper.data.GameRepository() }
    val gameData by gameRepository.gameData.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        gameData?.let {
            GameBoard(it, gameRepository)
        }
    }
}
