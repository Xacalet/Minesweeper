package com.xacalet.minesweeper.ui.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class BevelType {
    object Raised : BevelType()
    object Lowered : BevelType()
}

fun Modifier.bevel(
    width: Dp = 3.dp,
    type: BevelType = BevelType.Raised,
    shadeColor: Color = Color.Gray,
    shineColor: Color = Color.White,
) = this.then(
    Bevel(
        width = width,
        type = type,
        shadeColor = shadeColor,
        shineColor = shineColor,
        inspectorInfo = debugInspectorInfo {
            name = "bevel"
            properties["width"] = width
            properties["type"] = type
            properties["shadeColor"] = shadeColor
            properties["shineColor"] = shineColor
        }
    ).padding(width) // TODO: Find another way to do this padding
)

private class Bevel constructor(
    private val width: Dp = 1.dp,
    private val type: BevelType = BevelType.Raised,
    private val shadeColor: Color = Color.Gray,
    private val shineColor: Color = Color.White,
    inspectorInfo: InspectorInfo.() -> Unit
) : DrawModifier, InspectorValueInfo(inspectorInfo) {

    override fun ContentDrawScope.draw() {
        drawBevel(width, type, shadeColor, shineColor)
        drawContent()
    }

    override fun hashCode(): Int {
        var result = width.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + shadeColor.hashCode()
        result = 31 * result + shineColor.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        val otherModifier = other as? Bevel ?: return false
        return width == otherModifier.width &&
                type == otherModifier.type &&
                shadeColor == otherModifier.shadeColor &&
                shineColor == otherModifier.shineColor
    }

    override fun toString(): String =
        "Bevel(width=$width, type=$type, shadeColor=$shadeColor, shineColor=$shineColor)"
}

fun ContentDrawScope.drawBevel(
    width: Dp = 1.dp,
    type: BevelType = BevelType.Raised,
    shadeColor: Color = Color.Gray,
    shineColor: Color = Color.White
) {
    val bevelWidth = width.toPx()
    val bevelColor1 = if (type == BevelType.Raised) shineColor else shadeColor
    val bevelColor2 = if (type == BevelType.Raised) shadeColor else shineColor
    drawPath(
        color = bevelColor1,
        path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width - bevelWidth, bevelWidth)
            lineTo(bevelWidth, bevelWidth)
            lineTo(bevelWidth, size.height - bevelWidth)
            lineTo(0f, size.height)
            lineTo(0f, 0f)
        }
    )
    drawPath(
        color = bevelColor2,
        path = Path().apply {
            moveTo(size.width, size.height)
            lineTo(0f, size.height)
            lineTo(bevelWidth, size.height - bevelWidth)
            lineTo(size.width - bevelWidth, size.height - bevelWidth)
            lineTo(size.width - bevelWidth, bevelWidth)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RaisedBevelPanelPreview() {
    Box(
        modifier = Modifier
            .bevel(width = 4.dp)
            .size(32.dp)
            .background(Color.LightGray)
    )
}

@Preview(showBackground = true)
@Composable
fun LoweredBevelPanelPreview() {
    Box(
        modifier = Modifier
            .bevel(width = 4.dp, type = BevelType.Lowered)
            .size(32.dp)
            .background(Color.LightGray)
    )
}