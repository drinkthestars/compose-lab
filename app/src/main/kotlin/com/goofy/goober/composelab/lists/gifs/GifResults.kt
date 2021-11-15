package com.goofy.goober.composelab.lists.gifs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import coil.compose.rememberImagePainter
import com.giphy.sdk.core.models.Media

@OptIn(ExperimentalFoundationApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun GifResults(gifSearchResults: List<Media>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp)
    ) {
        this.items(items = gifSearchResults) { item ->
            key(item) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 3.dp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(12.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        painter = rememberImagePainter(
                            data = item.images.downsizedMedium?.gifUrl.orEmpty(),
                            builder = {
                                crossfade(true)
                            },
                        ),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = item.images.downsizedMedium?.gifUrl.orEmpty(),
                    )
                }
            }
        }
    }
}
