package com.goofy.goober.composelab.sketch

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import glm_.glm.gaussRand
import glm_.glm.linearRand
import glm_.glm.simplex
import glm_.vec2.Vec2
import kotlin.math.abs

private val Padding = 48.dp
private const val SizeMean = 13f
private const val SizeStdDev = 1f
private const val SatMin = 0.2f
private const val SatMax = 0.8f
private const val DotCount = 75

class Dot(val offset: Offset, val size: Float, val rotationDeg: Float)

@Composable
fun SketchLab(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF20222C)),
        contentAlignment = Alignment.Center
    ) {
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color(0xFF20222C),
                darkIcons = false,
                transformColorForLightContent = { _ -> Transparent }
            )
        }

        Sketch()
    }
}

@Composable
private fun Sketch(modifier: Modifier = Modifier) {
    val (density, screenHeightPx) = screenSize()
    val padding = with(density) { Padding.toPx() }
    var size by remember { mutableStateOf(IntSize.Zero) }
    val draggedY = remember { mutableStateOf(0f) }
//    val canvasTranslation: DrawTransform.() -> Unit = {
//        translate(left = screenWidthPx / 2, top = screenHeightPx / 2)
//    }

    val dots by remember {
        derivedStateOf {
            dots(
                size.width.toFloat(), size.height.toFloat(), count = DotCount, padding = padding
            )
        }
    }
    var hueMin by remember { mutableStateOf(240f) }
    var hueMax by remember { mutableStateOf(360f) }

    Canvas(
        modifier
            .fillMaxSize(0.9f)
//            .aspectRatio(1f)
            .onSizeChanged {
                size = it
            }
            .background(Transparent)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    val originalY = draggedY.value
                    val newY = (originalY + delta).coerceIn(0f, screenHeightPx)
                    draggedY.value = newY
//                currentSpeed = currentSpeed(draggedY.value, screenHeightPx)
                }
            )
    ) {
        drawIntoCanvas { canvas ->
            dots
//            .filter { linearRand(0f, 1f) < 0.7f }
                .forEach { dot ->
                    canvas.nativeCanvas.rotate(
                        dot.rotationDeg,
                        dot.offset.x,
                        dot.offset.y,
                    )
                    canvas.nativeCanvas.drawText(
                        ")",
                        dot.offset.x,
                        dot.offset.y,
                        Paint().apply {
                            color = Color.hsv(
                                hueMin + linearRand(0f, 1f) * (hueMax - hueMin),
                                lerp(SatMin, SatMax, linearRand(0f, 1f)),
                                1f
                            )
                        }.asFrameworkPaint().apply {
                            textSize = dot.size * 3f + 10f
                        }
                    )
                    canvas.nativeCanvas.restore()
                    canvas.nativeCanvas.save()
//                    drawLine(
//                        start = dot.offset,
//                        end = Offset(dot.offset.x + dot.size + 20f, dot.offset.y + dot.size.toInt()),
//                        color = hsv(
//                            hueMin + linearRand(0f, 1f) * (hueMax - hueMin),
//                            SatMin + linearRand(0f, 1f) * (SatMax - SatMin),
//                            1f
//                        )
//                    )
//                drawCircle(
//                    center = dot.offset, radius = dot.size / 2,
//                    color = hsv(
//                        hue = linearRand(hueMin, hueMax),
//                        saturation = linearRand(SatMin, SatMax),
//                        value = 1f
//                    )
//                )
                }
        }
    }
}

@Composable
private fun screenSize(): Pair<Density, Float> {
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    val screenWidthPx = with(density) { config.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { config.screenHeightDp.dp.toPx() }
    return Pair(density, screenHeightPx)
}

private fun dots(
    widthPx: Float,
    heightPx: Float,
    count: Int,
    padding: Float
): List<Dot> {
    return mutableListOf<Dot>().apply {
        (0 until count).forEach { x ->
            (0 until count).forEach { y ->
                // working between 0 and 1 aka U/V Space
                val u = x / (count - 1).toFloat()
                val v = y / (count - 1).toFloat()

                // lerp to get a value between 0 and 1
                val posX = lerp(padding, widthPx - padding, u)
                val posY = lerp(padding, heightPx - padding, v)

                val offset = Offset(posX, posY)
                val noise = noise(u, v)
                val size = size(noise)
                val rotationDeg = noise * 80f

                add(Dot(offset, size, rotationDeg))
            }
        }
    }
}

private fun size(noise: Float): Float {
    return noise * 16f
}

private fun noise(x: Float, y: Float) = abs(
    simplex(
        Vec2(
            x = x + gaussRand(7f, 0.4f),
            y = y + gaussRand(4f, 0.02f)
        )
    )
)

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewSketch() {
    SketchLab()
}
