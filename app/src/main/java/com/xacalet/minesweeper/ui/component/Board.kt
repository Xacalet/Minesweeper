package com.xacalet.minesweeper.ui.component

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.xacalet.minesweeper.R
import com.xacalet.minesweeper.model.Difficulty
import com.xacalet.minesweeper.model.GameRepository
import com.xacalet.minesweeper.model.GameState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
@Preview
fun Board() {
    // TODO: Provide this as parameter of Board() composable function.
    val repo = remember { GameRepository() }
    Box(Modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .background(Color.LightGray)
                .bevel()
                .padding(8.dp)
        ) {
            ControlPanel(repo = repo)
            Spacer(Modifier.size(8.dp))
            CellGrid(repo = repo)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ControlPanel(repo: GameRepository) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .bevel(type = BevelType.Lowered)
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val timer = repo.timer.collectAsState(0)
        NumericDisplay(value = timer.value)
        ClassicButton(
            modifier = Modifier
                .background(Color.Gray)
                .padding(2.dp)
                .size(44.dp),
            onClick = { repo.startNewGame(Difficulty.Beginner) }
        ) {
            val resource = when {
                // TODO: Respond to cell click event
                //isPressed -> R.drawable.ic_surprised_face
                repo.gameState == GameState.Won -> R.drawable.ic_smiley_sunglasses
                repo.gameState == GameState.Lost -> R.drawable.ic_smiley_dizzy
                else -> R.drawable.ic_smiley_smiling
            }
            Image(
                modifier = Modifier.padding(6.dp),
                painter = painterResource(resource),
                contentDescription = null
            )
        }
        NumericDisplay(value = repo.minesLeftCounter)
    }
}

@ExperimentalFoundationApi
@Composable
fun CellGrid(repo: GameRepository) {
    val context = LocalContext.current

    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> repo.startTimer()
                Lifecycle.Event.ON_PAUSE -> repo.stopTimer()
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

    Box(Modifier.bevel(5.dp, BevelType.Lowered)) {
        val (rows, columns) = repo.boardSize
        Column(Modifier.background(Color.Gray)) {
            (0 until rows).forEach { y ->
                Row {
                    (0 until columns).forEach { x ->
                        Cell(
                            modifier = Modifier.size(32.dp),
                            state = repo.cellStates[x][y].value,
                            onClick = { repo.onCellClick(x, y) },
                            onLongClick = {
                                if (repo.onCellLongClick(x, y)) {
                                    vibrate(context)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun vibrate(context: Context) {
    val vibrator = context.getSystemService(VIBRATOR_SERVICE) as? Vibrator
    val duration = 100L
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator?.vibrate(duration)
    }
}
