package com.goofy.goober.composelab.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

private const val RotationDeg = 90f

@Composable
fun DirectionalSlideInOut(
    modifier: Modifier = Modifier,
    state: VisibilityState,
    content: @Composable BoxScope.() -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visibleState = state.visibility,
        enter = state.inDirection.value.enterTransition(),
        exit = state.outDirection.value.exitTransition()
    ) {
        val animatedRotZ by transition.animateFloat(label = "rotation") { enterExitState ->
            when (enterExitState) {
                EnterExitState.PreEnter -> {
                    when (state.inDirection.value) {
                        Direction.In.Left -> -RotationDeg
                        Direction.In.Right -> RotationDeg
                        Direction.In.Up -> 0f
                        Direction.In.Down -> 0f
                    }
                }
                EnterExitState.Visible -> 0f
                EnterExitState.PostExit -> {
                    when (state.outDirection.value) {
                        Direction.Out.Left -> -RotationDeg
                        Direction.Out.Right -> RotationDeg
                        Direction.Out.Up -> 0f
                        Direction.Out.Down -> 0f
                    }
                }
            }
        }

        Box(
            modifier = Modifier.graphicsLayer { rotationZ = animatedRotZ },
            content = content
        )
    }
}

@Stable
fun Direction.In.enterTransition(): EnterTransition = run {
    when (this) {
        Direction.In.Left -> slideInHorizontally(
            { -it * 3 },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        Direction.In.Right -> slideInHorizontally(
            { it * 3 },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        Direction.In.Up -> slideInVertically(
            { -it },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        Direction.In.Down -> slideInVertically(
            { it },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
    }
}

@Stable
fun Direction.Out.exitTransition(): ExitTransition = run {
    when (this) {
        Direction.Out.Left -> slideOutHorizontally(
            { -it * 3 },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        Direction.Out.Right -> slideOutHorizontally(
            { it * 3 },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        Direction.Out.Up -> slideOutVertically(
            { -it * 3 },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        Direction.Out.Down -> slideOutVertically(
            { it * 3 },
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
    }
}

@Stable
class VisibilityState {
    val visibility = MutableTransitionState(initialState = false).apply { targetState = true }
    var inDirection = mutableStateOf<Direction.In>(Direction.In.Left)
    var outDirection = mutableStateOf<Direction.Out>(Direction.Out.Left)
}

@Stable
fun Direction.In.next(): Direction.In {
    return when (this) {
        Direction.In.Left -> Direction.In.Right
        Direction.In.Right -> Direction.In.Up
        Direction.In.Up -> Direction.In.Down
        Direction.In.Down -> Direction.In.Left
    }
}

@Stable
fun Direction.Out.next(): Direction.Out {
    return when (this) {
        Direction.Out.Left -> Direction.Out.Right
        Direction.Out.Right -> Direction.Out.Up
        Direction.Out.Up -> Direction.Out.Down
        Direction.Out.Down -> Direction.Out.Left
    }
}

@Stable
sealed class Direction {
    sealed class In : Direction() {
        object Left : In()
        object Right : In()
        object Up : In()
        object Down : In()
    }

    sealed class Out : Direction() {
        object Left : Out()
        object Right : Out()
        object Up : Out()
        object Down : Out()
    }
}
