package com.goofy.goober.composelab.cube

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberCubeState(
    coroutineScope: CoroutineScope
): CubeState {
    return remember { CubeState(coroutineScope) }
}

@Stable
class CubeState(
    private val coroutineScope: CoroutineScope
) {
    val animatedCubeAngle = Animatable(0f)
    var isPaused by mutableStateOf(true)

    fun playPause() = Snapshot.withMutableSnapshot {
        if (isPaused) {
            isPaused = false
            animate()
        } else {
            isPaused = true
            stop()
        }
    }

    private fun animate() {
        coroutineScope.launch {
            animatedCubeAngle.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 60_000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    private fun stop() {
        coroutineScope.launch {
            animatedCubeAngle.stop()
        }
    }
}
