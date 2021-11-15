package com.goofy.goober.composelab.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.giphy.sdk.ui.Giphy
import com.goofy.goober.composelab.ComposableLab
import com.goofy.goober.composelab.LabScaffold
import com.goofy.goober.composelab.MiscLab
import com.goofy.goober.composelab.Screen
import com.goofy.goober.composelab.cube.CubeLab
import com.goofy.goober.composelab.gestures.DragRotation3D
import com.goofy.goober.composelab.gestures.InfiniteRotation3D
import com.goofy.goober.composelab.lists.animations.AnimatedVisibilityLazyColumn
import com.goofy.goober.composelab.lists.animations.DynamicShowHideItemAnimation
import com.goofy.goober.composelab.lists.gifs.GifSearchLab
import com.goofy.goober.composelab.starfield.Starfield
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class LabActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        Giphy.configure(this, "odQumPBsMVYt0fGMipX26ElepU3KtRXo")

        setContent {
            LabApp()
        }
    }

    @Composable
    private fun LabApp() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight
        val navController = rememberNavController()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }

        LabScaffold(
            navController = navController,
            labs = MainLabs,
            startDestination = Screen.LabsHome
        ) {
            composable(Screen.Lists.GiphyGifSearch.route) { GifSearchLab() }
            composable(Screen.Lists.ItemAnimation.route) { AnimatedVisibilityLazyColumn() }
            composable(Screen.Lists.DynamicShowHideItemAnimation.route) { DynamicShowHideItemAnimation() }
            composable(Screen.Starfield.route) { Starfield() }
            composable(Screen.Cube.route) { CubeLab() }
            composable(Screen.Gestures.DragRotation3D.route) { DragRotation3D() }
            composable(Screen.Gestures.Rotation3D.route) { InfiniteRotation3D() }
            composable(Screen.MiscLab.route) { MiscLab() }
        }
    }
}

private val MainLabs = listOf(
    ComposableLab(screen = Screen.Starfield),
    ComposableLab(screen = Screen.Cube),
    ComposableLab(screen = Screen.Lists.GiphyGifSearch),
    ComposableLab(screen = Screen.Lists.ItemAnimation),
    ComposableLab(screen = Screen.Lists.DynamicShowHideItemAnimation),
    ComposableLab(screen = Screen.Gestures.DragRotation3D),
    ComposableLab(screen = Screen.Gestures.Rotation3D),
    ComposableLab(screen = Screen.MiscLab)
)
