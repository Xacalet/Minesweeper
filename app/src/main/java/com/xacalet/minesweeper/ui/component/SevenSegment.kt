/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xacalet.minesweeper.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SevenSegment(
    modifier: Modifier = Modifier,
    number: Int,
    color: Color = Color.Red
) {
    val colors = ('A'..'G').map {
        animateColorAsState(if (isSegmentActivatedByNumber(it, number)) color else color.copy(alpha = 0.2f))
    }

    Canvas(modifier) {
        val xUnit: Float = size.width / 20f
        val yUnit: Float = size.height / 36f
        val toX: (Int) -> Float = { it * xUnit }
        val toY: (Int) -> Float = { it * yUnit }

        // Segment A
        translate(toX(2), toY(0)) {
            topBottomSegment(colors[0].value, toX, toY)
        }

        // Segment B
        translate(toX(16), toY(2)) {
            sideSegment(colors[1].value, toX, toY)
        }

        // Segment C
        translate(toX(16), toY(-2)) {
            scale(1f, -1f) {
                sideSegment(colors[2].value, toX, toY)
            }
        }

        // Segment D
        translate(toX(2), toY(0)) {
            scale(1f, -1f) {
                topBottomSegment(colors[3].value, toX, toY)
            }
        }

        // Segment E
        translate(toX(-16), toY(-2)) {
            scale(-1f, -1f) {
                sideSegment(colors[4].value, toX, toY)
            }
        }

        // Segment F
        translate(toX(-16), toY(2)) {
            scale(-1f, 1f) {
                sideSegment(colors[5].value, toX, toY)
            }
        }

        // Segment G
        translate(toX(3), toY(16)) {
            middleSegment(colors[6].value, toX, toY)
        }
    }
}

private fun DrawScope.topBottomSegment(
    color: Color,
    toX: (Int) -> Float,
    toY: (Int) -> Float
) = run {
    drawPath(
        color = color,
        path = Path().apply {
            moveTo(toX(0), toY(1))
            lineTo(toX(1), toY(0))
            lineTo(toX(15), toY(0))
            lineTo(toX(16), toY(1))
            lineTo(toX(13), toY(4))
            lineTo(toX(3), toY(4))
        }
    )
}

private fun DrawScope.sideSegment(
    color: Color,
    toX: (Int) -> Float,
    toY: (Int) -> Float
) = run {
    drawPath(
        color = color,
        path = Path().apply {
            moveTo(toX(3), toY(0))
            lineTo(toX(4), toY(1))
            lineTo(toX(4), toY(14))
            lineTo(toX(3), toY(15))
            lineTo(toX(2), toY(15))
            lineTo(toX(0), toY(13))
            lineTo(toX(0), toY(3))
            lineTo(toX(3), toY(0))
        }
    )
}

private fun DrawScope.middleSegment(
    color: Color,
    toX: (Int) -> Float,
    toY: (Int) -> Float
) = run {
    drawPath(
        color = color,
        path = Path().apply {
            moveTo(toX(0), toY(2))
            lineTo(toX(2), toY(0))
            lineTo(toX(12), toY(0))
            lineTo(toX(14), toY(2))
            lineTo(toX(12), toY(4))
            lineTo(toX(2), toY(4))
            lineTo(toX(0), toY(2))
        }
    )
}

fun isSegmentActivatedByNumber(segment: Char, number: Int): Boolean =
    number.coerceIn(0, 9).let {
        when (segment) {
            'A' -> it !in listOf(1, 4)
            'B' -> it !in listOf(5, 6)
            'C' -> it != 2
            'D' -> it !in listOf(1, 4, 7)
            'E' -> it !in listOf(1, 3, 4, 5, 7, 9)
            'F' -> it !in listOf(1, 2, 3, 7)
            'G' -> it !in listOf(0, 1, 7)
            else -> false
        }
    }

@Preview
@Composable
fun SegmentsPreview() {
    Column(Modifier.background(Color.Black)) {
        Row {
            (0..4).forEach {
                SevenSegment(
                    modifier = Modifier
                        .height(72.dp)
                        .width(40.dp),
                    number = it
                )
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        Row {
            (5..9).forEach {
                SevenSegment(
                    modifier = Modifier
                        .height(72.dp)
                        .width(40.dp),
                    number = it
                )
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
    }
}

@Preview
@Composable
fun IncSevenSegmentPreview() {
    var value by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SevenSegment(
            modifier = Modifier
                .height(72.dp)
                .width(40.dp),
            number = value
        )
        Spacer(modifier = Modifier.size(12.dp))
        Button(onClick = { value = (value + 1) % 10 }) {
            Text(text = "Inc(1)")
        }
    }
}
