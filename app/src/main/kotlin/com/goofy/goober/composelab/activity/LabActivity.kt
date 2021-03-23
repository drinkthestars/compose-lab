package com.goofy.goober.composelab.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.giphy.sdk.ui.Giphy
import com.goofy.goober.composelab.ComposableLab
import com.goofy.goober.composelab.LabScaffold
import com.goofy.goober.composelab.Screen
import com.goofy.goober.composelab.gestures.DragWrappingAndroidView
import com.goofy.goober.composelab.lists.gifs.GifSearchLab

class LabActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            composable(Screen.Gestures.DragAndroidView.route) { DragWrappingAndroidView() }
            composable(Screen.Lists.GiphyGifSearch.route) { GifSearchLab() }
        }
    }
}

private val MainLabs = listOf(
    ComposableLab(screen = Screen.Gestures.DragAndroidView),
    ComposableLab(screen = Screen.Lists.GiphyGifSearch)
)
