package com.goofy.goober.composelab.animations


import androidx.compose.runtime.Composable


/**
 * Source: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui/samples/src/main/java/androidx/compose/ui/samples/OnPlacedSamples.kt
 * and https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/animation/animation/integration-tests/animation-demos/src/main/java/androidx/compose/animation/demos/AnimatedPlacement.kt
 */
@Composable
fun OnPlacedModifierAlignmentChange() {
//    @OptIn(ExperimentalComposeUiApi::class)
//    fun Modifier.animatePlacement(): Modifier = composed {
//        val scope = rememberCoroutineScope()
//        var targetOffset by remember { mutableStateOf(IntOffset.Zero) }
//        var animatable by remember {
//            mutableStateOf<Animatable<IntOffset, AnimationVector2D>?>(null)
//        }
//        this
//            .onPlaced {
//                // Calculate the position in the parent layout
//                targetOffset = it
//                    .positionInParent()
//                    .round()
//            }
//            .offset {
//                // Animate to the new target offset when alignment changes.
//                val anim = animatable ?: Animatable(targetOffset, IntOffset.VectorConverter)
//                    .also { animatable = it }
//                if (anim.targetValue != targetOffset) {
//                    scope.launch {
//                        anim.animateTo(targetOffset, spring(stiffness = StiffnessMediumLow))
//                    }
//                }
//                // Offset the child in the opposite direction to the targetOffset, and slowly catch
//                // up to zero offset via an animation to achieve an overall animated movement.
//                animatable?.let { it.value - targetOffset } ?: IntOffset.Zero
//            }
//    }
//
//    @OptIn(ExperimentalComposeUiApi::class)
//    @Composable
//    fun AnimateChildAlignment(alignment: Alignment) {
//        Box(
//            Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.8f)
//                .padding(4.dp)
//                .border(1.dp, Color.Red)
//        ) {
//            Box(
//                modifier = Modifier
//                    .animatePlacement()
//                    .align(alignment)
//                    .size(100.dp)
//                    .background(Color.Red)
//            )
//        }
//    }
//
//    var alignment by remember { mutableStateOf(Alignment.TopStart) }
//    Column(modifier = Modifier
//        .padding(24.dp)
//        .fillMaxSize()) {
//        Spacer(modifier = Modifier.height(56.dp))
//        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Button(onClick = { alignment = Alignment.TopStart }) {
//                Text("TopStart")
//            }
//            Button(onClick = { alignment = Alignment.TopCenter }) {
//                Text("TopCenter")
//            }
//            Button(onClick = { alignment = Alignment.TopEnd }) {
//                Text("TopEnd")
//            }
//        }
//        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Button(onClick = { alignment = Alignment.CenterStart }) {
//                Text("CenterStart")
//            }
//            Button(onClick = { alignment = Alignment.Center }) {
//                Text("Center")
//            }
//            Button(onClick = { alignment = Alignment.CenterEnd }) {
//                Text("CenterEnd")
//            }
//        }
//        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Button(onClick = { alignment = Alignment.BottomStart }) {
//                Text("BottomStart")
//            }
//            Button(onClick = { alignment = Alignment.BottomCenter }) {
//                Text("BottomCenter")
//            }
//            Button(onClick = { alignment = Alignment.BottomEnd }) {
//                Text("BottomEnd")
//            }
//        }
//        AnimateChildAlignment(alignment)
//        Spacer(modifier = Modifier.height(66.dp))
//    }
}
