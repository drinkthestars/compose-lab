package com.goofy.goober.composelab.activity.gifs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.giphy.sdk.core.models.Media
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun GifResults(gifSearchResults: List<Media>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp)
    ) {
        this.items(items = gifSearchResults) { item ->
            key(item) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 3.dp,
                    modifier = Modifier.height(250.dp).padding(12.dp)
                ) {
                    GlideImage(
                        modifier = Modifier.fillMaxSize(),
                        requestBuilder = { apply(RequestOptions().centerCrop()) },
                        data = item.images.downsizedMedium?.gifUrl.orEmpty(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
