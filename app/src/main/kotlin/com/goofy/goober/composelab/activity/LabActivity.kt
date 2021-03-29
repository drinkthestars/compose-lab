package com.goofy.goober.composelab.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.composable
import com.giphy.sdk.ui.Giphy
import com.goofy.goober.composelab.ComposableLab
import com.goofy.goober.composelab.LabScaffold
import com.goofy.goober.composelab.MiscLab
import com.goofy.goober.composelab.Screen
import com.goofy.goober.composelab.gestures.DragWrappingAndroidView
import com.goofy.goober.composelab.lists.gifs.GifSearchLab
import com.goofy.goober.composelab.starfield.Starfield

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
    fun LabApp() {
        LabScaffold(
            labs = MainLabs,
            startDestination = Screen.LabsHome
        ) {
            composable(Screen.Lists.GiphyGifSearch.route) { GifSearchLab() }
            composable(Screen.Starfield.route) { Starfield() }
            composable(Screen.MiscLab.route) { MiscLab() }
        }
    }
}

private val MainLabs = listOf(
    ComposableLab(screen = Screen.Starfield),
    ComposableLab(screen = Screen.Lists.GiphyGifSearch),
    ComposableLab(screen = Screen.MiscLab)
)
