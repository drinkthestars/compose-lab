package com.goofy.goober.composelab.animations


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import kotlinx.coroutines.launch


/**
 * Source: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui/samples/src/main/java/androidx/compose/ui/samples/OnPlacedSamples.kt
 * and https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/animation/animation/integration-tests/animation-demos/src/main/java/androidx/compose/animation/demos/AnimatedPlacement.kt
 */
@Composable
fun OnPlacedModifierAlignmentChange() {
    @OptIn(ExperimentalComposeUiApi::class)
    fun Modifier.animatePlacement(): Modifier = composed {
        val scope = rememberCoroutineScope()
        var targetOffset by remember { mutableStateOf(IntOffset.Zero) }
        var animatable by remember {
            mutableStateOf<Animatable<IntOffset, AnimationVector2D>?>(null)
        }
        this
            .onPlaced {
                // Calculate the position in the parent layout
                targetOffset = it
                    .positionInParent()
                    .round()
            }
            .offset {
                // Animate to the new target offset when alignment changes.
                val anim = animatable ?: Animatable(targetOffset, IntOffset.VectorConverter)
                    .also { animatable = it }
                if (anim.targetValue != targetOffset) {
                    scope.launch {
                        anim.animateTo(targetOffset, spring(stiffness = Spring.StiffnessMediumLow))
                    }
                }
                // Offset the child in the opposite direction to the targetOffset, and slowly catch
                // up to zero offset via an animation to achieve an overall animated movement.
                animatable?.let { it.value - targetOffset } ?: IntOffset.Zero
            }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun AnimateChildAlignment(alignment: Alignment) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(4.dp)
                .border(1.dp, Color.Red)
        ) {
            Box(
                modifier = Modifier
                    .animatePlacement()
                    .align(alignment)
                    .size(100.dp)
                    .background(Color.Red)
            )
        }
    }

    var alignment by remember { mutableStateOf(Alignment.TopStart) }
    Column(modifier = Modifier
        .padding(24.dp)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(56.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { alignment = Alignment.TopStart }) {
                Text("TopStart")
            }
            Button(onClick = { alignment = Alignment.TopCenter }) {
                Text("TopCenter")
            }
            Button(onClick = { alignment = Alignment.TopEnd }) {
                Text("TopEnd")
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { alignment = Alignment.CenterStart }) {
                Text("CenterStart")
            }
            Button(onClick = { alignment = Alignment.Center }) {
                Text("Center")
            }
            Button(onClick = { alignment = Alignment.CenterEnd }) {
                Text("CenterEnd")
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { alignment = Alignment.BottomStart }) {
                Text("BottomStart")
            }
            Button(onClick = { alignment = Alignment.BottomCenter }) {
                Text("BottomCenter")
            }
            Button(onClick = { alignment = Alignment.BottomEnd }) {
                Text("BottomEnd")
            }
        }
        AnimateChildAlignment(alignment)
        Spacer(modifier = Modifier.height(66.dp))
    }
}
