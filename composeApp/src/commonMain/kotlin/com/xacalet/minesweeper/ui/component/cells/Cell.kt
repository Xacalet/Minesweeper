package com.xacalet.minesweeper.ui.component.cells

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.xacalet.minesweeper.model.CellState
import com.xacalet.minesweeper.ui.component.ClassicButton

@Composable
internal fun Cell(
    modifier: Modifier = Modifier,
    state: CellState,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onPressed: (Boolean) -> Unit
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed.value) {
        onPressed(isPressed.value)
    }

    Box(modifier = modifier) {
        when (state) {
            CellState.Covered -> ClassicButton(
                onClick = onClick,
                onLongClick = onLongClick,
                interactionSource = interactionSource
            )
            CellState.Flagged -> FlagCell(
                onLongClick = onLongClick,
                interactionSource = interactionSource
            )
            is CellState.Safe -> SafeCell(
                contiguousMineCount = state.contiguousMineCount,
                onClick = onClick,
                interactionSource = interactionSource
            )
            is CellState.Mine -> MineCell(hasExploded = state.exploded)
            CellState.NotAMine -> WrongFlagCell()
        }
    }
}

