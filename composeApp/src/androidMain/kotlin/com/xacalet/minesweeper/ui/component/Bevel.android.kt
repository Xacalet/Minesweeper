package com.xacalet.minesweeper.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun RaisedBevelPanelPreview() {
    Box(
        modifier = Modifier
            .bevel(width = 4.dp)
            .size(32.dp)
            .background(Color.LightGray)
    )
}

@Preview(showBackground = true)
@Composable
private fun LoweredBevelPanelPreview() {
    Box(
        modifier = Modifier
            .bevel(width = 4.dp, type = BevelType.Lowered)
            .size(32.dp)
            .background(Color.LightGray)
    )
}
