package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.model.CellState
import com.xacalet.minesweeper.ui.foundation.BevelType
import com.xacalet.minesweeper.ui.foundation.bevel
import com.xacalet.minesweeper.ui.graphics.NotAMine
import com.xacalet.minesweeper.ui.graphics.Mine
import com.xacalet.minesweeper.ui.theme.*


private fun getTextColorByContiguousMineCount(contiguousMineCount: Int): Color =
    when (contiguousMineCount) {
        1 -> color1
        2 -> color2
        3 -> color3
        4 -> color4
        5 -> color5
        6 -> color6
        7 -> color7
        8 -> color8
        else -> Color.Transparent
    }

/**
 *
 */
object NoIndication : Indication {
    private object NoIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    val (cellState, setCellState) = remember { mutableStateOf<CellState>(CellState.Covered) }
    val onClick = {
        if (cellState == CellState.Covered)
            setCellState(CellState.Safe(4))
    }
    val onDoubleClick = {
        if (cellState == CellState.Covered)
            setCellState(CellState.Flagged)
        else if (cellState == CellState.Flagged)
            setCellState(CellState.Covered)
    }

    Cell(Modifier.size(32.dp), cellState, onClick, onDoubleClick)
}


@ExperimentalFoundationApi
@Composable
fun Cell(
    modifier: Modifier = Modifier,
    state: CellState,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    Box(modifier = modifier) {
        when (state) {
            CellState.Covered -> CoveredCell(onClick = onClick, onLongClick = onLongClick)
            CellState.Flagged -> FlagCell(onLongClick = onLongClick)
            is CellState.Safe -> SafeCell(contiguousMineCount = state.contiguousMineCount)
            is CellState.Mine -> MineCell(hasExploded = state.exploded)
            CellState.NotAMine -> Unit
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun CoveredCell(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value
    Box(
        modifier = modifier
            .fillMaxSize()
            .let { innerModifier ->
                if (isPressed) {
                    innerModifier
                        .padding(start = 1.dp, top = 1.dp)
                        .background(pressedGray)
                } else {
                    innerModifier
                        .bevel(width = 3.dp, type = BevelType.Raised)
                        .background(unpressedGray)
                }
            }
            .combinedClickable(
                indication = NoIndication,
                interactionSource = interactionSource,
                onClick = onClick,
                onLongClick = onLongClick
            ),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@ExperimentalFoundationApi
@Composable
fun UncoveredCell(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 1.dp, top = 1.dp)
            .background(pressedGray),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@ExperimentalFoundationApi
@Composable
fun FlagCell(
    modifier: Modifier = Modifier,
    onLongClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .bevel(width = 3.dp, type = BevelType.Raised)
            .background(unpressedGray)
            .combinedClickable(
                indication = NoIndication,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { },
                onLongClick = onLongClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Flag()
    }
}

@ExperimentalFoundationApi
@Composable
fun SafeCell(contiguousMineCount: Int) {
    UncoveredCell {
        if (contiguousMineCount > 0) {
            Text(
                modifier = Modifier.fillMaxSize(),
                style = contiguousMineCountTextStyle,
                color = getTextColorByContiguousMineCount(contiguousMineCount),
                text = "$contiguousMineCount"
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MineCell(hasExploded: Boolean) {
    UncoveredCell {
        Mine(hasExploded = hasExploded)
    }
}

@ExperimentalFoundationApi
@Composable
fun NotAMineCell() {
    UncoveredCell {
        NotAMine()
    }
}
