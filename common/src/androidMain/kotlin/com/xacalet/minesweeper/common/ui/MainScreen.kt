package com.xacalet.minesweeper.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.xacalet.minesweeper.common.R
import com.xacalet.minesweeper.common.data.GameRepository
import com.xacalet.minesweeper.common.extensions.vibrate
import com.xacalet.minesweeper.common.model.GameData
import com.xacalet.minesweeper.common.model.GameState
import com.xacalet.minesweeper.common.ui.component.BevelType
import com.xacalet.minesweeper.common.ui.component.ClassicButton
import com.xacalet.minesweeper.common.ui.component.NumericDisplay
import com.xacalet.minesweeper.common.ui.component.bevel
import com.xacalet.minesweeper.common.ui.component.cells.Cell
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
fun Screen() {
    // TODO: Provide this as parameter of Board() composable function.
    val gameRepository = remember { GameRepository() }
    val gameDataState: State<GameData?> = gameRepository.gameData.collectAsState()
    InitLifecycleEvents(
        onResume = gameRepository::resumeTimer,
        onPause = gameRepository::pauseTimer,
    )
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
                val context = LocalContext.current
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
                            context.vibrate()
                        }
                    },
                    cellPressed = { isPressed -> isAnyCellPressed.value = isPressed }
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ControlPanel(
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
        NumericDisplay(value = timer.value)
        ClassicButton(
            modifier = Modifier
                .background(Color.Gray)
                .padding(2.dp)
                .size(44.dp),
            onClick = clickRestartButton
        ) {
            val state = gameData.state.collectAsState().value
            val resource = if (isPressed.value) {
                R.drawable.ic_smiley_surprised
            } else {
                when (state) {
                    GameState.Won -> R.drawable.ic_smiley_sunglasses
                    GameState.Lost -> R.drawable.ic_smiley_dizzy
                    else -> R.drawable.ic_smiley_smiling
                }
            }

            Image(
                modifier = Modifier.padding(6.dp),
                painter = painterResource(resource),
                contentDescription = null
            )
        }
        NumericDisplay(value = gameData.minesLeftCounter.collectAsState().value)
    }
}

@ExperimentalFoundationApi
@Composable
fun CellGrid(
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

@Composable
private fun InitLifecycleEvents(
    onResume: () -> Unit,
    onPause: () -> Unit,
) {
    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> onResume()
                Lifecycle.Event.ON_PAUSE -> onPause()
                else -> Unit
            }
        }
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
}
