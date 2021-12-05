package com.goofy.goober.composelab.hypercube

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.goofy.goober.composelab.cube.CubeState
import com.goofy.goober.composelab.cube.multiply
import com.goofy.goober.composelab.cube.normalize
import com.goofy.goober.composelab.drawing.drawCube
import com.goofy.goober.composelab.drawing.drawLine
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

private const val CamDistance = 1.7f
private const val YZRotAngle = -360f
private val RotationYZ = arrayOf(
    floatArrayOf(1f, 0f, 0f),
    floatArrayOf(0f, cos(YZRotAngle), -sin(YZRotAngle)),
    floatArrayOf(0f, sin(YZRotAngle), cos(YZRotAngle)),
)
private val CubeState.zwRotMatrix get() = rotationZW(animatedCubeAngle.value / 10)
private val CubeState.xyRotMatrix get() = rotationXY(animatedCubeAngle.value / 12)

@Composable
fun RotatingHypercube(
    cubeState: CubeState,
    scaleFactor: Float,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    size: Float = 100f,
) {
    val cubePoints = remember { cubePoints(scaleFactor) }
    Canvas(modifier = modifier) {
        cubePoints.map {
            // First perform rotation around X-Y in 4D
            val rotatedXY = cubeState.xyRotMatrix.multiply(
                array = it,
                firstRows = 4,
                firstColumn = 4,
                secondColumn = 1
            )
            // Then around Z-W in 4D
            val rotatedZW = cubeState.zwRotMatrix.multiply(
                array = rotatedXY,
                firstRows = 4,
                firstColumn = 4,
                secondColumn = 1
            )
            // Calculate 3D skew
            val skewFactor3D = skewFactor(rotatedZW)
            // Apply 3D skew
            val projected3D = skewed4DProjection(skew = 1 / skewFactor3D).multiply(
                array = rotatedZW,
                firstRows = 3,
                firstColumn = 4,
                secondColumn = 1
            )
            // Rotate around YZ in 3D
            val rotateX = RotationYZ.multiply(
                array = projected3D,
                firstRows = 3,
                firstColumn = 3,
                secondColumn = 1
            )
            // Calculate 2D skew
            val skewFactor2D = skewFactor(rotateX)
            // Apply 2D skew
            val projected2D = skewed3DProjection(skew = 1 / skewFactor2D).multiply(
                array = rotateX,
                firstRows = 2,
                firstColumn = 3,
                secondColumn = 1
            )
            // Final 2D projected Offset
            Offset(projected2D[0] * size, projected2D[1] * size)
        }.also { offsets ->
            // Draw points for both inner & outer cube
            drawPoints(
                points = offsets,
                color = color.copy(alpha = 0.8f),
                pointMode = PointMode.Points,
                strokeWidth = 20f,
                cap = StrokeCap.Round
            )
            // Draw 1st cube
            drawCube(offsets, color)
            // Draw 2ndt cube
            drawCube(offsets, Color.Magenta, indexOffset = 8)
            // Draw connections between both cubes
            hypercubeConnections(offsets, color)
        }
    }
}

private fun DrawScope.hypercubeConnections(
    offsets: List<Offset>,
    color: Color
) {
    for (i in 0 until 8) {
        drawLine(
            offsets,
            start = i,
            end = i + 8,
            color
        )
    }
}

private fun cubePoints(scaleFactor: Float): Array<FloatArray> {
    val f = scaleFactor.absoluteValue
    // Hypercube is made up of 16 vertices - 2 cubes connected
    return arrayOf(
        // 1st cube - side 1
        floatArrayOf(-f, -f, -f, f),
        floatArrayOf(f, -f, -f, f),
        floatArrayOf(f, f, -f, f),
        floatArrayOf(-f, f, -f, f),
        // 1st cube - side 2
        floatArrayOf(-f, -f, f, f),
        floatArrayOf(f, -f, f, f),
        floatArrayOf(f, f, f, f),
        floatArrayOf(-f, f, f, f),
        // 2nd cube - side 1
        floatArrayOf(-f, -f, -f, -f),
        floatArrayOf(f, -f, -f, -f),
        floatArrayOf(f, f, -f, -f),
        floatArrayOf(-f, f, -f, -f),
        // 2nd cube - side 2
        floatArrayOf(-f, -f, f, -f),
        floatArrayOf(f, -f, f, -f),
        floatArrayOf(f, f, f, -f),
        floatArrayOf(-f, f, f, -f)
    )
}

private fun skewed4DProjection(skew: Float = 1f) = arrayOf(
    floatArrayOf(skew, 0f, 0f, 0f),
    floatArrayOf(0f, skew, 0f, 0f),
    floatArrayOf(0f, 0f, skew, 0f),
)

private fun skewed3DProjection(skew: Float = 1f) = arrayOf(
    floatArrayOf(skew, 0f, 0f),
    floatArrayOf(0f, skew, 0f)
)

private fun rotationXY(angle: Float) = arrayOf(
    floatArrayOf(cos(angle), -sin(angle), 0f, 0f),
    floatArrayOf(sin(angle), cos(angle), 0f, 0f),
    floatArrayOf(0f, 0f, 1f, 0f),
    floatArrayOf(0f, 0f, 0f, 1f),
)

private fun rotationZW(angle: Float) = arrayOf(
    floatArrayOf(1f, 0f, 0f, 0f),
    floatArrayOf(0f, 1f, 0f, 0f),
    floatArrayOf(0f, 0f, cos(angle), -sin(angle)),
    floatArrayOf(0f, 0f, sin(angle), cos(angle)),
)

private fun skewFactor(projected: FloatArray): Float {
    return CamDistance - projected
        .last()
        .normalize(max = 2.7f, min = -2.7f)
}
