package com.xacalet.minesweeper.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.common.data.GameRepository
import com.xacalet.minesweeper.common.model.GameState

// TODO: Join fun Screen in common
@Composable
actual fun MainScreen() {
    // TODO: Provide this as parameter of Board() composable function.
    val gameRepository = remember { GameRepository() }
    val gameData by gameRepository.gameData.collectAsState()
    gameData?.let {
        GameBoard(it, gameRepository)
    }
}

@Composable
internal actual fun GameStateImage(isPressed: Boolean, gameState: GameState) {
    val resourcePath: String = if (isPressed) {
        "smiley_surprised.svg"
    } else {
        when (gameState) {
            GameState.Won -> "smiley_sunglasses.svg"
            GameState.Lost -> "smiley_dizzy.svg"
            else -> "smiley_smiling.svg"
        }
    }

    Image(
        modifier = Modifier.padding(6.dp),
        painter = painterResource(resourcePath),
        contentDescription = null
    )
}
