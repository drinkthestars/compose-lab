package com.goofy.goober.composelab.cube

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import com.goofy.goober.composelab.drawing.drawCube
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

private const val CamDistance = 1.7f
private const val XRotation = 5f
private const val YRotation = 7f
private const val ZRotation = 9f
private val CubeState.effectiveXRotation get() = rotationX(animatedCubeAngle.value / -XRotation)
private val CubeState.effectiveYRotation get() = rotationY(animatedCubeAngle.value / YRotation)
private val CubeState.effectiveZRotation get() = rotationZ(animatedCubeAngle.value / ZRotation)

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
            val rotatedX = cubeState.effectiveXRotation.multiply(
                array = it,
                firstRows = 3,
                firstColumn = 3,
                secondColumn = 1
            )
            val rotatedXY = cubeState.effectiveYRotation.multiply(
                array = rotatedX,
                firstRows = 3,
                firstColumn = 3,
                secondColumn = 1
            )
            val rotatedXYZ = cubeState.effectiveZRotation.multiply(
                array = rotatedXY,
                firstRows = 3,
                firstColumn = 3,
                secondColumn = 1
            )
            val skewFactor = skewFactor(rotatedXYZ)
            val projected2D = skewed2DProjection(skew = 1 / skewFactor).multiply(
                array = rotatedXYZ,
                firstRows = 2,
                firstColumn = 3,
                secondColumn = 1
            )
            Offset(projected2D[0] * size, projected2D[1] * size)
        }.also { offsets ->
            drawPoints(
                points = offsets,
                color = color.copy(alpha = 0.8f),
                pointMode = PointMode.Points,
                strokeWidth = 14f,
                cap = StrokeCap.Round
            )
            drawCube(offsets, color)
        }
    }
}

private fun cubePoints(scaleFactor: Float): Array<FloatArray> {
    val f = scaleFactor.absoluteValue
    return arrayOf(
        // Side 1
        floatArrayOf(-f, -f, -f),
        floatArrayOf(f, -f, -f),
        floatArrayOf(f, f, -f),
        floatArrayOf(-f, f, -f),
        // Side 2
        floatArrayOf(-f, -f, f),
        floatArrayOf(f, -f, f),
        floatArrayOf(f, f, f),
        floatArrayOf(-f, f, f)
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

private fun skewFactor(rotatedXYZ: FloatArray): Float {
    return (CamDistance - rotatedXYZ.last().normalize(max = 3f, min = -3f))
}
