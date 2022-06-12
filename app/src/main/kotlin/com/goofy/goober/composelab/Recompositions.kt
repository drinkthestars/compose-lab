package com.goofy.goober.composelab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecompositionHighlights() {

    var scale by remember { mutableStateOf(0.5f) }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { scale = (scale + 0.1f).coerceAtMost(maximumValue = 1f) }) {
                Text("alpha +")
            }
            Button(onClick = { scale = (scale - 0.1f).coerceAtLeast(minimumValue = 0f) }) {
                Text("alpha -")
            }
            Button(onClick = { scale = 0.5f }) {
                Text("reset")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "graphicsLayer(): ",
            style = MaterialTheme.typography.subtitle1,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .recomposeHighlighter()
                .graphicsLayer(alpha = scale)
                .size(100.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "graphicsLayer {}: ",
            style = MaterialTheme.typography.subtitle1,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .recomposeHighlighter()
                .graphicsLayer { alpha = scale }
                .size(100.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(66.dp))
    }
}

@Preview
@Composable
fun RecompositionHighlightsPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RecompositionHighlights()
        }
    }
}
