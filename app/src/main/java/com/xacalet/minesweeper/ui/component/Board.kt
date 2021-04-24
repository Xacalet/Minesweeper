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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.R
import com.xacalet.minesweeper.model.GameRepository
import com.xacalet.minesweeper.ui.foundation.BevelType
import com.xacalet.minesweeper.ui.foundation.bevel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
@Preview
fun Board() {
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
        // TODO: Connect to a timer
        NumericDisplay(0)
        ClassicButton(
            modifier = Modifier
                .background(Color.Gray)
                .padding(2.dp)
                .size(44.dp),
            onClick = { repo.startNewGame(GameRepository.Difficulty.Beginner) }
        ) {
            val resource = when {
                // TODO: Respond to cell click event
                //isPressed -> R.drawable.ic_surprised_face
                repo.gameState == GameRepository.GameState.Won -> R.drawable.ic_face_with_sunglasses
                repo.gameState == GameRepository.GameState.Lost -> R.drawable.ic_dizzy_face
                else -> R.drawable.ic_smiling_face
            }
            Image(
                modifier = Modifier.padding(6.dp),
                painter = painterResource(resource),
                contentDescription = null
            )
        }
        NumericDisplay(repo.minesLeftCounter)
    }
}

@ExperimentalFoundationApi
@Composable
fun CellGrid(repo: GameRepository) {
    val context = LocalContext.current
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
                                vibrate(context)
                                repo.onCellLongClick(x, y)
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
