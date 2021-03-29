package com.goofy.goober.composelab.starfield

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawTransform
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.goofy.goober.composelab.map
import kotlin.math.absoluteValue

private const val MaxSpeed = 45f

@Composable
fun Starfield() {
    setupStatusAndNavBars(LocalContext.current)
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    val screenWidthPx = with(density) { config.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { config.screenHeightDp.dp.toPx() }
    val draggedY = remember { mutableStateOf(0f) }
    val canvasTranslation: DrawTransform.() -> Unit = {
        translate(left = screenWidthPx / 2, top = screenHeightPx / 2)
    }
    val stars = remember { initStars(size = 700, screenWidthPx, screenHeightPx) }
    var currentSpeed by remember { mutableStateOf(15f) }

    LaunchedEffect(Unit) {
        do {
            withFrameNanos { stars.forEach { it.draw(currentSpeed) } }
        } while (true)
    }

    Canvas(Modifier.fillMaxSize()
        .background(Color.Black)
        .draggable(
            orientation = Orientation.Vertical,
            state = rememberDraggableState { delta ->
                val originalY = draggedY.value
                val newY = (originalY + delta).coerceIn(0f, screenHeightPx)
                draggedY.value = newY
                currentSpeed = currentSpeed(draggedY.value, screenHeightPx)
            }
        )
    ) {
        withTransform(canvasTranslation) {
            stars.forEach {
                drawLine(
                    start = it.fromOffset,
                    end = it.toOffset,
                    strokeWidth = 0.5f,
                    color = it.color,
                    cap = StrokeCap.Round
                )
                drawCircle(
                    center = it.toOffset,
                    color = it.color,
                    radius = it.radius
                )
            }
        }
    }
}

private fun setupStatusAndNavBars(context: Context) {
    val window = (context as Activity).window
    window.statusBarColor = Color.Black.value.toInt()
    window.navigationBarColor = Color.Black.value.toInt()
}

private fun initStars(
    size: Int,
    screenWidthPx: Float,
    screenHeightPx: Float
) = List(size) { Star(screenWidthPx, screenHeightPx) }

private fun currentSpeed(newValue: Float, screenHeightPx: Float) =
    newValue.absoluteValue.map(0f, screenHeightPx, MaxSpeed, 0f)
