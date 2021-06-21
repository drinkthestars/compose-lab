package com.goofy.goober.composelab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goofy.goober.composelab.gestures.DragWrappingAndroidView

@Composable
fun MiscLab() {
    val navController = rememberNavController()

    LabScaffold(
        navController = navController,
        labs = MiscLab,
        startDestination = Screen.MiscLab
    ) {
        composable(Screen.Gestures.DragAndroidView.route) { DragWrappingAndroidView() }
    }
}

private val MiscLab = listOf(
    ComposableLab(screen = Screen.Gestures.DragAndroidView)
)
