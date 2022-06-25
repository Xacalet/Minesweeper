package com.xacalet.minesweeper.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.common.data.GameRepository
import com.xacalet.minesweeper.common.model.GameData
import com.xacalet.minesweeper.common.model.GameState
import com.xacalet.minesweeper.common.ui.component.bevel

// TODO: Join fun Screen in common
@Composable
fun MainScreen() {
    // TODO: Provide this as parameter of Board() composable function.
    val gameRepository = remember { GameRepository() }
    val gameDataState: State<GameData?> = gameRepository.gameData.collectAsState()
    Box(Modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .background(Color.LightGray)
                .bevel()
                .padding(8.dp)
        ) {
            gameDataState.value?.let { gameData ->
                val isAnyCellPressed = remember { mutableStateOf(false) }
                ControlPanel(
                    gameData = gameData,
                    clickRestartButton = gameRepository::resetGame,
                    isPressed = isAnyCellPressed
                )
                Spacer(Modifier.size(8.dp))
                CellGrid(
                    gameData = gameData,
                    clickCell = { x, y -> gameRepository.onCellClick(x, y) },
                    longClickCell = { x, y ->
                        if (gameRepository.onCellLongClick(x, y)) {
                            // TODO
                        }
                    },
                    cellPressed = { isPressed -> isAnyCellPressed.value = isPressed }
                )
            }
        }
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
