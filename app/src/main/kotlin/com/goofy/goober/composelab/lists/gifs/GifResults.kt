package com.goofy.goober.composelab.lists.gifs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.giphy.sdk.core.models.Media
import com.google.accompanist.coil.rememberCoilPainter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GifResults(gifSearchResults: List<Media>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
//    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp)
    ) {
        this.items(items = gifSearchResults) { item ->
            key(item) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 3.dp,
                    modifier = Modifier
                        .height(250.dp)
                        .padding(12.dp)
                ) {
                    // See https://github.com/google/accompanist/issues/241
//                    GlideImage(
//                        modifier = Modifier.fillMaxSize(),
//                        requestBuilder = { apply(RequestOptions().centerCrop()) },
//                        data = item.images.downsizedMedium?.gifUrl.orEmpty(),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop
//                    )
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberCoilPainter(
                            fadeIn = true,
                            request = item.images.downsizedMedium?.gifUrl.orEmpty(),
                            requestBuilder = {
                                transformations(CircleCropTransformation())
                            },
                        ),
                        contentDescription = item.images.downsizedMedium?.gifUrl.orEmpty(),
                    )
                }
            }
        }
    }
}
