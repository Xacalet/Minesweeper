package com.xacalet.minesweeper.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.common.data.GameRepository
import com.xacalet.minesweeper.common.model.GameData
import com.xacalet.minesweeper.common.model.GameState
import com.xacalet.minesweeper.common.ui.component.BevelType
import com.xacalet.minesweeper.common.ui.component.ClassicButton
import com.xacalet.minesweeper.common.ui.component.NumericDisplay
import com.xacalet.minesweeper.common.ui.component.bevel
import com.xacalet.minesweeper.common.ui.component.cells.Cell
import com.xacalet.minesweeper.common.ui.resources.DimenResources
import com.xacalet.minesweeper.common.ui.resources.PainterResources

@Composable
expect fun MainScreen()

@Composable
internal fun GameBoard(
    gameData: GameData,
    gameRepository: GameRepository,
    onLongClickCell: () -> Unit = {}
) {
    Box(Modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .background(Color.LightGray)
                .bevel()
                .padding(8.dp)
        ) {
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
                        onLongClickCell()
                    }
                },
                cellPressed = { isPressed -> isAnyCellPressed.value = isPressed }
            )
        }
    }
}

@Composable
internal fun GameStateImage(
    isPressed: Boolean,
    gameState: GameState,
    modifier: Modifier = Modifier
) {
    val painter = if (isPressed) {
        PainterResources.iconSmileySurprised()
    } else {
        when (gameState) {
            GameState.Won -> PainterResources.iconSmileySunglasses()
            GameState.Lost -> PainterResources.iconSmileyDizzy()
            else -> PainterResources.iconSmileySmiling()
        }
    }

    Image(
        modifier = modifier.padding(6.dp),
        painter = painter,
        contentDescription = null
    )
}

@Composable
internal fun ControlPanel(
    gameData: GameData,
    clickRestartButton: () -> Unit,
    isPressed: State<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .bevel(type = BevelType.Lowered)
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val timer = gameData.elapsedSeconds.collectAsState(0)
        NumericDisplay(
            value = timer.value,
            modifier = Modifier.height(DimenResources.NumericDisplayHeight)
        )
        ClassicButton(
            modifier = Modifier
                .background(Color.Gray)
                .padding(2.dp)
                .size(44.dp),
            onClick = clickRestartButton
        ) {
            GameStateImage(
                isPressed.value,
                gameData.state.value,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        NumericDisplay(
            value = gameData.minesLeftCounter.collectAsState().value,
            modifier = Modifier.height(DimenResources.NumericDisplayHeight)
        )
    }
}

@Composable
internal fun CellGrid(
    gameData: GameData,
    clickCell: (Int, Int) -> Unit,
    longClickCell: (Int, Int) -> Unit,
    cellPressed: (Boolean) -> Unit
) {
    Box(Modifier.bevel(5.dp, BevelType.Lowered)) {
        val (rows, columns) = remember { gameData.boardSize }
        Column(Modifier.background(Color.Gray)) {
            (0 until rows).forEach { y ->
                Row {
                    (0 until columns).forEach { x ->
                        Cell(
                            modifier = Modifier.size(32.dp),
                            state = gameData.cellStates[x][y].collectAsState().value,
                            onClick = { clickCell(x, y) },
                            onLongClick = { longClickCell(x, y) },
                            onPressed = cellPressed
                        )
                    }
                }
            }
        }
    }
}
