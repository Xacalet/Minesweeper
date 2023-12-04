package com.xacalet.minesweeper.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.xacalet.minesweeper.extensions.vibrate
import com.xacalet.minesweeper.ui.theme.background

@Composable
actual fun MainScreen() {
    // TODO: Provide this as parameter of Board() composable function.
    val gameRepository = remember { com.xacalet.minesweeper.data.GameRepository() }
    val gameData by gameRepository.gameData.collectAsState()
    InitLifecycleEvents(
        onResume = gameRepository::resumeTimer,
        onPause = gameRepository::pauseTimer,
    )
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        gameData?.let {
            GameBoard(it, gameRepository, onLongClickCell = { context.vibrate() })
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
