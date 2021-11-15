package com.goofy.goober.composelab.starfield

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.compose.ui.geometry.Offset
import com.goofy.goober.composelab.cube.map
import kotlin.random.Random

private const val SizeMax = 30f
private const val SizeMin = 12f

class Star(
    private val screenWidthPx: Float,
    private val screenHeightPx: Float
) {
    private var x = random(-screenWidthPx, screenWidthPx)
    private var y = random(-screenHeightPx, screenHeightPx)
    private var z = random(max = screenWidthPx)
    private var prevZ = z
    private val randMaxSize = random(SizeMin, SizeMax)

    var fromOffset by mutableStateOf(Offset.Zero)
    var toOffset by mutableStateOf(Offset.Zero)
    var radius by mutableStateOf(0f)
    val color = StarColors.random()

    fun draw(speed: Float) {
        withMutableSnapshot {
            z -= speed
            if (z < 1) {
                z = screenWidthPx
                x = random(-screenWidthPx, screenWidthPx)
                y = random(-screenHeightPx, screenHeightPx)
                prevZ = z
            }
            radius = z.map(0f, screenWidthPx, randMaxSize, 0f)
            fromOffset = Offset(
                x = (x / prevZ).map(0f, 1f, 0f, screenWidthPx),
                y = (y / prevZ).map(0f, 1f, 0f, screenHeightPx)
            )
            toOffset = Offset(
                x = (x / z).map(0f, 1f, 0f, screenWidthPx),
                y = (y / z).map(0f, 1f, 0f, screenHeightPx)
            )
            prevZ = z
        }
    }

    private fun random(min: Float = 0f, max: Float) = min + Random.nextFloat() * (max - min)
}
