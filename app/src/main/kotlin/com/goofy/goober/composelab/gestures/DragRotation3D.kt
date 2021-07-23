package com.goofy.goober.composelab.gestures

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.goofy.goober.composelab.R
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

private const val RotationFactor = 0.09f
private const val RotLimit = 180f
private const val CamDist = 15f

@Composable
fun DragRotation3D() {

    //region Rotations
    val rotX = remember { Animatable(0f) }
    var dRotX by remember { mutableStateOf(0f) }
    val rotY = remember { Animatable(0f) }
    var dRotY by remember { mutableStateOf(0f) }
    //endregion

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        awaitPointerEventScope {
                            val pointerId = awaitFirstDown().run {
                                launch {
                                    dRotX = 0f
                                    dRotY = 0f
                                }
                                id
                            }
                            drag(pointerId) {
                                launch {
                                    dRotX = rotX.value + it.positionChange().y * RotationFactor
                                    dRotY = rotY.value + it.positionChange().x * RotationFactor
                                    // TODO: Constrain 180 deg flip only on one axis at a time
                                    rotX.snapTo(dRotX)
                                    rotY.snapTo(dRotY)
                                }
                            }
                            //region Restore animation
                            launch {
                                awaitAll(
                                    async {
                                        rotY.animateTo(
                                            0f,
                                            animationSpec = spring(stiffness = Spring.StiffnessLow)
                                        )
                                    },
                                    async {
                                        rotX.animateTo(
                                            0f,
                                            animationSpec = spring(stiffness = Spring.StiffnessLow)
                                        )
                                    }
                                )
                            }
                            //endregion
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent, RoundedCornerShape(6.dp))
                .wrapContentSize()
                .graphicsLayer {
                    rotationX = (-rotX.value).coerceIn(-RotLimit, RotLimit)
                    rotationY = rotY.value.coerceIn(-RotLimit, RotLimit)
                    cameraDistance = CamDist
                    shape = RoundedCornerShape(6.dp)
                }
                .shadow(12.dp, shape = RoundedCornerShape(6.dp)),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(0.726f),
                painter = painterResource(rotY, rotX),
                contentDescription = "charizard",
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun isFlipped(
    rotY: Animatable<Float, AnimationVector1D>,
    rotX: Animatable<Float, AnimationVector1D>
) = rotY.value.absoluteValue > 90f || rotX.value.absoluteValue > 90f

@Composable
private fun painterResource(
    rotY: Animatable<Float, AnimationVector1D>,
    rotX: Animatable<Float, AnimationVector1D>
) = if (isFlipped(rotY, rotX)) painterResource(
    R.drawable.pokemon_back
) else painterResource(R.drawable.charizard)
