package com.goofy.goober.composelab.gestures

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.goofy.goober.composelab.R
import kotlin.math.absoluteValue

private const val CamDist = 15f

@Composable
fun InfiniteRotation3D() {
    //region Rotations
    val rotX = remember { mutableStateOf(value = 0.8f) }
    val rotY = remember { mutableStateOf(value = 0.9f) }
    val rotZ = remember { mutableStateOf(value = 0.4f) }

    LaunchYRotation(rotY)
    LaunchXRotation(rotX)
    LaunchZRotation(rotZ)
    //endregion

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .graphicsLayer {
                    rotationX = rotX.value
                    rotationY = rotY.value
                    rotationZ = rotZ.value
                    cameraDistance = CamDist
                    shape = RoundedCornerShape(6.dp)
                }
                .shadow(12.dp, shape = RoundedCornerShape(6.dp)),
            shape = RoundedCornerShape(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.5f)
                    .aspectRatio(ratio = 0.726f),
                painter = painterResource(rotY, rotX),
                contentDescription = "charizard",
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
private fun LaunchZRotation(rotZ: MutableState<Float>) {
    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 180f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 9_500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, _ ->
            rotZ.value = value
        }
    }
}

@Composable
private fun LaunchXRotation(rotX: MutableState<Float>) {
    LaunchedEffect(Unit) {
        animate(
            initialValue = -80f,
            targetValue = 60f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 9_000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, _ ->
            rotX.value = value
        }
    }
}

@Composable
private fun LaunchYRotation(rotY: MutableState<Float>) {
    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 270f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 8_000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, _ ->
            rotY.value = value
        }
    }
}

@Composable
private fun painterResource(
    rotY: MutableState<Float>,
    rotX: MutableState<Float>
): Painter {
    return if (isFlipped(rotY, rotX)) {
        painterResource(R.drawable.pokemon_back)
    } else {
        painterResource(R.drawable.charizard)
    }
}

@Composable
fun isFlipped(
    rotY: MutableState<Float>,
    rotX: MutableState<Float>
) = rotY.value.absoluteValue >= FlipThreshDeg || rotX.value.absoluteValue >= FlipThreshDeg
