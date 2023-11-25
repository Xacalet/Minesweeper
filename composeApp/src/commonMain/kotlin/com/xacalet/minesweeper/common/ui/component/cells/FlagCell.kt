package com.xacalet.minesweeper.common.ui.component.cells

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.common.ui.component.BevelType
import com.xacalet.minesweeper.common.ui.component.bevel
import com.xacalet.minesweeper.common.ui.resources.PainterResources
import com.xacalet.minesweeper.common.ui.theme.unpressedGray
import com.xacalet.minesweeper.common.ui.utils.NoIndication

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FlagCell(
    modifier: Modifier = Modifier,
    onLongClick: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .bevel(width = 3.dp, type = BevelType.Raised)
            .background(unpressedGray)
            .combinedClickable(
                indication = NoIndication,
                interactionSource = interactionSource,
                onClick = { },
                onLongClick = onLongClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            painter = PainterResources.iconCellFlag(),
            contentDescription = null
        )
    }
}
