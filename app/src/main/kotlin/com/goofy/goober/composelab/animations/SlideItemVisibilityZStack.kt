package com.goofy.goober.composelab.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier

private const val OffsetMultiplier = 3

@Composable
fun SlideAnimatedVisibilityLab(
    state: SlideAnimatedVisibilityState,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visibleState = state.visibility,
        enter = state.inDirection.value.slideEnter(),
        exit = state.outDirection.value.slideExit(),
        content = content
    )
}

@Stable
class SlideAnimatedVisibilityState {
    val visibility = MutableTransitionState(initialState = false).apply { targetState = true }
    var inDirection = mutableStateOf<Direction.In>(Direction.In.Left)
    var outDirection = mutableStateOf<Direction.Out>(Direction.Out.Left)
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

@Stable
private fun Direction.In.slideEnter(): EnterTransition = when (this) {
    Direction.In.Left -> slideInHorizontally(
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        initialOffsetX = { -it * OffsetMultiplier })
    Direction.In.Right -> slideInHorizontally(
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        initialOffsetX = { it * OffsetMultiplier }
    )
    Direction.In.Up -> slideInVertically(
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        initialOffsetY = { -it }
    )
    Direction.In.Down -> slideInVertically(
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        initialOffsetY = { it }
    )
}

@Stable
private fun Direction.Out.slideExit(): ExitTransition = run {
    when (this) {
        Direction.Out.Left -> slideOutHorizontally(
            animationSpec = spring(stiffness = Spring.StiffnessLow),
            targetOffsetX = { -it * OffsetMultiplier }
        )
        Direction.Out.Right -> slideOutHorizontally(
            animationSpec = spring(stiffness = Spring.StiffnessLow),
            targetOffsetX = { it * OffsetMultiplier }
        )
        Direction.Out.Up -> slideOutVertically(
            animationSpec = spring(stiffness = Spring.StiffnessLow),
            targetOffsetY = { -it * OffsetMultiplier }
        )
        Direction.Out.Down -> slideOutVertically(
            animationSpec = spring(stiffness = Spring.StiffnessLow),
            targetOffsetY = { it * OffsetMultiplier }
        )
    }
}
