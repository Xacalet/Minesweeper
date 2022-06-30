package com.xacalet.minesweeper.common.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.xacalet.minesweeper.common.R
import com.xacalet.minesweeper.common.data.GameRepository
import com.xacalet.minesweeper.common.extensions.vibrate
import com.xacalet.minesweeper.common.model.GameState

@Composable
actual fun MainScreen() {
    // TODO: Provide this as parameter of Board() composable function.
    val gameRepository = remember { GameRepository() }
    val gameData by gameRepository.gameData.collectAsState()
    InitLifecycleEvents(
        onResume = gameRepository::resumeTimer,
        onPause = gameRepository::pauseTimer,
    )
    val context = LocalContext.current
    gameData?.let {
        GameBoard(it, gameRepository, onLongClickCell = { context.vibrate() })
    }
}

@Composable
internal actual fun GameStateImage(isPressed: Boolean, gameState: GameState) {
    @DrawableRes val resource = if (isPressed) {
        R.drawable.ic_smiley_surprised
    } else {
        when (gameState) {
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
