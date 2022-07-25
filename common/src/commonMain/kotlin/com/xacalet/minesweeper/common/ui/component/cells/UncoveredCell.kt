package com.xacalet.minesweeper.common.ui.component.cells

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.common.ui.theme.pressedGray

@Composable
internal fun UncoveredCell(
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
