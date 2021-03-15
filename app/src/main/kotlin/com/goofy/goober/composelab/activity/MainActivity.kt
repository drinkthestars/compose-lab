package com.goofy.goober.composelab.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.goofy.goober.composelab.activity.gestures.DragWrappingAndroidView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeLabApp()
        }
    }

    @Composable
    fun ComposeLabApp() {
        Surface(modifier = Modifier.fillMaxSize()) {
            DragWrappingAndroidView()
        }
    }
}
