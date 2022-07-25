package com.xacalet.minesweeper.common.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.dp
import com.xacalet.minesweeper.common.ui.theme.pressedGray
import com.xacalet.minesweeper.common.ui.theme.unpressedGray

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ClassicButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .combinedClickable(
                indication = ClassicButtonIndication,
                interactionSource = interactionSource,
                onClick = onClick,
                onLongClick = onLongClick
            ),
        content = content
    )
}

/**
 * Indication subclass that prevents ripple effect.
 */
object ClassicButtonIndication : Indication {

    private class ClassicButtonIndicationInstance(
        private val isPressed: State<Boolean>
    ) : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            if (isPressed.value) {
                inset(1.dp.toPx(), 1.dp.toPx(), 0f, 0f) {
                    drawRect(pressedGray)
                }
            } else {
                drawRect(unpressedGray)
                drawBevel(3.dp)
            }
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        return remember(interactionSource) { ClassicButtonIndicationInstance(isPressed) }
    }
}
