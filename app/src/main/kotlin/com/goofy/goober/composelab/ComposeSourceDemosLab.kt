package com.goofy.goober.composelab

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goofy.goober.composelab.animations.OnPlacedModifierAlignmentChange

@Composable
fun ComposeSourceDemosLab() {
    val navController = rememberNavController()

    LabScaffold(
        navController = navController,
        labs = MiscLab,
        startDestination = Screen.ComposeSourceDemos
    ) {
        composable(Screen.Animations.AlignmentChange.route) { OnPlacedModifierAlignmentChange() }
    }
}

private val MiscLab = listOf(
    ComposableLab(screen = Screen.Animations.AlignmentChange)
)
