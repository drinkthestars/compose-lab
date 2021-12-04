package com.goofy.goober.composelab.tesseract

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.goofy.goober.composelab.cube.rememberCubeState
import com.goofy.goober.composelab.cubeEdges
import com.goofy.goober.composelab.darkNavy
import com.goofy.goober.composelab.lightNavy
import com.goofy.goober.composelab.lightPurpleBlue

@Composable
fun Tesseract() {
    val coroutineScope = rememberCoroutineScope()
    val cubeState = rememberCubeState(coroutineScope)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(darkNavy)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Brush.radialGradient(colors = listOf(lightNavy, darkNavy)))
        ) {
            RotatingTesseract(
                modifier = Modifier.align(Alignment.Center),
                cubeState = cubeState,
                color = cubeEdges,
                scaleFactor = 1.55f
            )
        }
        IconButton(onClick = { cubeState.playPause() }) {
            if (cubeState.isPaused) {
                PlaybackControlIcon(
                    imageVector = Icons.Default.PlayArrow,
                    tint = lightPurpleBlue
                )
            } else {
                PlaybackControlIcon(
                    imageVector = Icons.Default.Pause,
                    tint = lightPurpleBlue
                )
            }
        }
    }
}

@Composable
fun PlaybackControlIcon(imageVector: ImageVector, tint: Color) {
    Icon(
        modifier = Modifier.size(50.dp),
        imageVector = imageVector,
        contentDescription = null,
        tint = tint
    )
}
