package com.xacalet.minesweeper.ui.component.cells

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.model.CellState

@Preview(showBackground = true)
@Composable
private fun ButtonPreview() {
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

    Cell(
        modifier = Modifier.size(32.dp),
        state = cellState,
        onClick = onClick,
        onLongClick = onDoubleClick,
        onPressed = { }
    )
}
