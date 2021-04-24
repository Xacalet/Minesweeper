package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.R
import com.xacalet.minesweeper.model.CellState
import com.xacalet.minesweeper.ui.foundation.BevelType
import com.xacalet.minesweeper.ui.foundation.bevel
import com.xacalet.minesweeper.ui.theme.*
import com.xacalet.minesweeper.ui.utils.NoIndication

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
            CellState.Covered -> ClassicButton(onClick = onClick, onLongClick = onLongClick)
            CellState.Flagged -> FlagCell(onLongClick = onLongClick)
            is CellState.Safe -> SafeCell(
                contiguousMineCount = state.contiguousMineCount,
                onClick = onClick
            )
            is CellState.Mine -> MineCell(hasExploded = state.exploded)
            CellState.NotAMine -> WrongFlagCell()
        }
    }
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
        Image(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp),
            painter = painterResource(id = R.drawable.ic_flag),
            contentDescription = null
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun SafeCell(contiguousMineCount: Int, onClick: () -> Unit) {
    UncoveredCell {
        if (contiguousMineCount > 0) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = onClick),
                style = contiguousMineCountTextStyle,
                color = getTextColorByContiguousMineCount(contiguousMineCount),
                text = "$contiguousMineCount"
            )
        }
    }
}

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

@ExperimentalFoundationApi
@Composable
fun MineCell(hasExploded: Boolean) {
    UncoveredCell {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(if (hasExploded) Color.Red else Color.Transparent)
                .padding(4.dp),
            painter = painterResource(id = R.drawable.ic_mine),
            contentDescription = null
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun WrongFlagCell() {
    UncoveredCell {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            painter = painterResource(id = R.drawable.ic_wrong_flag),
            contentDescription = null
        )
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
