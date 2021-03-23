package com.goofy.goober.composelab.lists.gifs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.giphy.sdk.core.models.Media
import dev.chrisbanes.accompanist.coil.CoilImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GifResults(gifSearchResults: List<Media>) {
    LazyVerticalGrid(
        cells =  GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp)
    ) {
        this.items(items = gifSearchResults) { item ->
            key(item) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 3.dp,
                    modifier = Modifier.height(150.dp).padding(12.dp)
                ) {
                    CoilImage(
                        fadeIn = true,
                        modifier = Modifier.fillMaxSize(),
                        data = item.images.downsizedMedium?.gifUrl.orEmpty(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
