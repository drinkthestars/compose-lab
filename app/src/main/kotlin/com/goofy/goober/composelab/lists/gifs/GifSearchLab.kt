package com.goofy.goober.composelab.lists.gifs

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import com.google.accompanist.coil.LocalImageLoader

@Composable
fun GifSearchLab(
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalImageLoader provides coilImageLoader()) {
        GifSearch(modifier)
    }
}

@Composable
private fun coilImageLoader() = ImageLoader.Builder(LocalContext.current)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .componentRegistry {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder(LocalContext.current))
        } else add(GifDecoder())
    }
    .build()
