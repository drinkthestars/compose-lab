package com.goofy.goober.composelab.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawCube(
    offsets: List<Offset>,
    color: Color,
    indexOffset: Int = 0
) {
    for (i in 0 until 4) {
        drawLine(
            offsets,
            start = i,
            end = (i + 1) % 4,
            color,
            indexOffset
        )
        drawLine(
            offsets,
            start = i + 4,
            end = ((i + 1) % 4) + 4,
            color,
            indexOffset
        )
        drawLine(offsets, start = i, end = i + 4, color, indexOffset)
    }
}

fun DrawScope.drawLine(
    offsets: List<Offset>,
    start: Int,
    end: Int,
    color: Color,
    indexOffset: Int = 0
) {
    val startOffset = offsets[start + indexOffset]
    val endOffset = offsets[end + indexOffset]
    drawLine(
        start = startOffset,
        end = endOffset,
        strokeWidth = 5f,
        color = color.copy(alpha = 0.6f),
        cap = StrokeCap.Round
    )
}
