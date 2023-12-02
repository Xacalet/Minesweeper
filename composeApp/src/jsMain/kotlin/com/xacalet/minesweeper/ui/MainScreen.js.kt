package com.xacalet.minesweeper.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import com.xacalet.minesweeper.ui.theme.background
import com.xacalet.minesweeper.ui.theme.mineSweeperFontFamily
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun MainScreen() {
    val gameRepository = remember { com.xacalet.minesweeper.data.GameRepository() }
    val gameData by gameRepository.gameData.collectAsState()

    LaunchedEffect(Unit) {
        val byteArray = resource("font/mine_sweeper.ttf").readBytes()
        mineSweeperFontFamily = FontFamily(Font("MineSweeper", byteArray))
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        gameData?.let {
            GameBoard(it, gameRepository)
        }
    }
}
