package com.goofy.goober.composelab.activity.gifs

import com.giphy.sdk.core.models.Media
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.models.enums.RatingType
import com.giphy.sdk.core.network.api.CompletionHandler
import com.giphy.sdk.core.network.response.ListMediaResponse
import com.giphy.sdk.ui.pagination.GPHContent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun loadGifs(query: String) = suspendCancellableCoroutine<List<Media>> {
    val completionHandler = object : CompletionHandler<ListMediaResponse> {
        override fun onComplete(result: ListMediaResponse?, e: Throwable?) {
            it.resume(result?.data ?: emptyList(), onCancellation = {})
        }
    }

    GPHContent.searchQuery(
        search = query,
        mediaType = MediaType.gif,
        ratingType = RatingType.pg13
    ).queryGifs(offset = 0, completionHandler = completionHandler)
}
