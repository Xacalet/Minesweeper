package com.xacalet.minesweeper.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import com.xacalet.minesweeper.data.GameRepository
import com.xacalet.minesweeper.ui.theme.mineSweeperFontFamily
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun MainScreen() {
    // TODO: Provide this as parameter of Board() composable function.
    val gameRepository = remember { GameRepository() }
    val gameData by gameRepository.gameData.collectAsState()

    LaunchedEffect(Unit) {
        val byteArray = resource("font/mine_sweeper.ttf").readBytes()
        mineSweeperFontFamily = FontFamily(Font("MineSweeper", byteArray))
    }

    gameData?.let {
        GameBoard(it, gameRepository)
    }
}
