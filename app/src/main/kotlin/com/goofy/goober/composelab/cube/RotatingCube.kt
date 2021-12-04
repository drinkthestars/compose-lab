package com.goofy.goober.composelab.cube

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

private const val CamDistance = 1.7f
private const val XRotation = 5f
private const val YRotation = 0f
private const val ZRotation = 0f

@Composable
fun RotatingCube(
    cubeState: CubeState,
    scaleFactor: Float,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    size: Float = 100f,
) {
    val cubePoints = remember { cubePoints(scaleFactor) }
    Canvas(modifier = modifier) {
        cubePoints.map {
            val rotated = cubeState.effectiveYRotation.multiply(
                array = it,
                firstRows = 3,
                firstColumn = 3,
                secondColumn = 1
            )
//            val rotatedXY = cubeState.effectiveYRotation.multiply(
//                array = rotatedX,
//                r1 = 3,
//                c1 = 3,
//                c2 = 1
//            )
//            val rotatedXYZ = cubeState.effectiveZRotation.multiply(
//                array = rotatedXY,
//                r1 = 3,
//                c1 = 3,
//                c2 = 1
//            )
            val skewFactor = skewFactor(rotated)
            val projected2D = skewed2DProjection(skew = 1 / skewFactor).multiply(
                array = rotated,
                firstRows = 2,
                firstColumn = 3,
                secondColumn = 1
            )
            Offset(projected2D[0] * size, projected2D[1] * size)
        }.also { offsets ->
            for (i in 0 until 4) {
                draw(
                    offsets,
                    start = i,
                    end = (i + 1) % 4,
                    color
                )
                draw(
                    offsets,
                    start = i + 4,
                    end = ((i + 1) % 4) + 4,
                    color
                )
                draw(offsets, start = i, end = i + 4, color)
            }
        }
    }
}

private val CubeState.effectiveXRotation get() = rotationX(animatedCubeAngle.value - XRotation)

private val CubeState.effectiveYRotation get() = rotationY(animatedCubeAngle.value - YRotation)

private val CubeState.effectiveZRotation get() = rotationZ(animatedCubeAngle.value - ZRotation)

private fun cubePoints(scaleFactor: Float): Array<FloatArray> {
    val factor = scaleFactor.absoluteValue
    return arrayOf(
        floatArrayOf(-factor, -factor, -factor),
        floatArrayOf(factor, -factor, -factor),
        floatArrayOf(factor, factor, -factor),
        floatArrayOf(-factor, factor, -factor),

        floatArrayOf(-factor, -factor, factor),
        floatArrayOf(factor, -factor, factor),
        floatArrayOf(factor, factor, factor),
        floatArrayOf(-factor, factor, factor)
    )
}

private fun skewed2DProjection(skew: Float = 1f) = arrayOf(
    floatArrayOf(skew, 0f, 0f),
    floatArrayOf(0f, skew, 0f)
)

private fun rotationX(angle: Float) = arrayOf(
    floatArrayOf(1f, 0f, 0f),
    floatArrayOf(0f, cos(angle), -sin(angle)),
    floatArrayOf(0f, sin(angle), cos(angle)),
)

private fun rotationY(angle: Float) = arrayOf(
    floatArrayOf(cos(angle), 0f, -sin(angle)),
    floatArrayOf(0f, 1f, 0f),
    floatArrayOf(sin(angle), 0f, cos(angle)),
)

fun rotationZ(angle: Float) = arrayOf(
    floatArrayOf(cos(angle), -sin(angle), 0f),
    floatArrayOf(sin(angle), cos(angle), 0f),
    floatArrayOf(0f, 0f, 1f),
)

private fun DrawScope.draw(
    offsets: List<Offset>,
    start: Int,
    end: Int,
    color: Color
) {
    val startOffset = offsets[start]
    val endOffset = offsets[end]
    drawLine(
        start = startOffset,
        end = endOffset,
        strokeWidth = 10f,
        color = color.copy(alpha = 0.8f),
        cap = StrokeCap.Round
    )
    drawPoints(
        points = offsets,
        color = color.copy(alpha = 0.8f),
        pointMode = PointMode.Points,
        strokeWidth = 14f,
        cap = StrokeCap.Round
    )
}

private fun skewFactor(rotatedXYZ: FloatArray) =
    (CamDistance - rotatedXYZ.last().normalize(max = 3f, min = -3f))
