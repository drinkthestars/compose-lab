package com.goofy.goober.composelab

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.goofy.goober.composelab.gestures.DragWrappingAndroidView

@Composable
fun MiscLab() {
    LabScaffold(
        labs = MiscLab,
        startDestination = Screen.MiscLab
    ) {
        composable(Screen.Gestures.DragAndroidView.route) { DragWrappingAndroidView() }
    }
}
private val MiscLab = listOf(
    ComposableLab(screen = Screen.Gestures.DragAndroidView)
)
