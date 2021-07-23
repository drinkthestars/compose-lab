package com.goofy.goober.composelab.lists.animations

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.goofy.goober.composelab.R
import com.goofy.goober.composelab.animations.DirectionalSlideInOut
import com.goofy.goober.composelab.animations.VisibilityState
import com.goofy.goober.composelab.animations.next
import java.util.UUID

@Composable
fun DynamicShowHideItemAnimation() {
    Box(modifier = Modifier.fillMaxSize()) {
        val list = remember { VinylImages.map { ListItem(painterRes = it) } }
        Controls(list)
        List(list)
    }
}

@Composable
private fun BoxScope.List(list: List<ListItem>) {
    Box(
        modifier = Modifier.Companion.align(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        list.forEach { item ->
            key(item.id) {
                DirectionalSlideInOut(
                    modifier = Modifier.align(Alignment.Center),
                    state = item.visibilityState
                ) {
                    Image(
                        modifier = Modifier
                            .width(200.dp)
                            .aspectRatio(1f),
                        painter = painterResource(item.painterRes),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}

@Composable
private fun Controls(
    list: List<ListItem>
) {
    var topIndex by remember { mutableStateOf(list.size - 1) }
    InfoAndControls(
        list[topIndex].visibilityState,
        onRemoveClick = {
            list[topIndex].visibilityState.apply {
                visibility.targetState = false
            }
            topIndex = (topIndex - 1).coerceAtLeast(0)
        },
        onRestoreClick = {
            topIndex = (topIndex + 1).coerceAtMost(list.size - 1)
            list.getOrNull(topIndex)?.visibilityState?.apply {
                visibility.targetState = true
            }
        },
        onDirectionChange = {
            list[topIndex].visibilityState.apply {
                inDirection.value = inDirection.value.next()
                outDirection.value = outDirection.value.next()
            }
        }
    )
}

@Composable
private fun InfoAndControls(
    state: VisibilityState,
    onRemoveClick: () -> Unit,
    onRestoreClick: () -> Unit,
    onDirectionChange: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(12.dp)
                .background(Color.Transparent)
        ) {
            Column(Modifier.wrapContentSize()) {
                Button(onClick = onRemoveClick) {
                    Text("remove")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onRestoreClick) {
                    Text("restore")
                }
            }
            Spacer(modifier = Modifier.width(60.dp))
            Button(onClick = onDirectionChange) {
                Text("toggle removal direction")
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "in=${state.inDirection.value.javaClass.simpleName}  out=${state.outDirection.value.javaClass.simpleName}")
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Immutable
private data class ListItem(
    val visibilityState: VisibilityState = VisibilityState(),
    val id: String = UUID.randomUUID().toString(),
    @DrawableRes val painterRes: Int
)

private val VinylImages = listOf(
    R.drawable.vinyl1,
    R.drawable.vinyl2,
    R.drawable.vinyl3,
    R.drawable.vinyl4,
    R.drawable.vinyl5
)
