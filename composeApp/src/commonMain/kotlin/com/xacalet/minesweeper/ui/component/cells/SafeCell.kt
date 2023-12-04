package com.xacalet.minesweeper.ui.component.cells

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.xacalet.minesweeper.ui.theme.Type.contiguousMineCountTextStyle
import com.xacalet.minesweeper.ui.theme.color1
import com.xacalet.minesweeper.ui.theme.color2
import com.xacalet.minesweeper.ui.theme.color3
import com.xacalet.minesweeper.ui.theme.color4
import com.xacalet.minesweeper.ui.theme.color5
import com.xacalet.minesweeper.ui.theme.color6
import com.xacalet.minesweeper.ui.theme.color7
import com.xacalet.minesweeper.ui.theme.color8

@Composable
internal fun SafeCell(
    contiguousMineCount: Int,
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    UncoveredCell {
        if (contiguousMineCount > 0) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    ),
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
