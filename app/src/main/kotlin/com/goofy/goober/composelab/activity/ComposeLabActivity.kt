package com.goofy.goober.composelab.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.giphy.sdk.ui.Giphy
import com.goofy.goober.composelab.activity.gifs.GifSearch

class ComposeLabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Giphy.configure(this, "odQumPBsMVYt0fGMipX26ElepU3KtRXo")
        setContent {
            ComposeLabApp()
        }
    }

    @Composable
    fun ComposeLabApp() {
        Surface(modifier = Modifier.fillMaxSize()) {
            GifSearch()
        }
    }
}
