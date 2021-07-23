package com.goofy.goober.composelab.lists.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview
@Composable
private fun AnimatedVisibilityLazyColumnPreview() {
    AnimatedVisibilityLazyColumn()
}

@Composable
fun AnimatedVisibilityLazyColumn() {
    Surface {
        val store = remember { ItemSnapshots() }
        AnimatedVisibilityLazyColumn(
            snapshot = store(),
            onAdd = { store.update(Update.Add(it)) },
            onRemove = { store.update(Update.Remove) },
        ) { store.update(Update.Clear) }
    }
}

@Composable
private fun AnimatedVisibilityLazyColumn(
    snapshot: ItemSnapshot,
    onAdd: (color: Color) -> Unit,
    onRemove: () -> Unit,
    onClear: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(78.dp))
        Info(snapshot)
        Spacer(modifier = Modifier.height(8.dp))
        Controls(snapshot, onAdd, onRemove, onClear)
        Spacer(modifier = Modifier.height(8.dp))
        ColorList(snapshot)
    }
}

@Composable
private fun ColorList(itemSnapshot: ItemSnapshot) {
    val removals = remember { mutableMapOf<Item, String>() }
    LazyColumn {
        itemsIndexed(itemSnapshot.items, key = { _, item -> item.id }) { _, item ->
            val visibility = remember(item.id) {
                MutableTransitionState(initialState = item.status != Item.Status.Visible)
            }

            SideEffect {
                (item.status == Item.Status.Visible).let {
                    if (!it) removals[item] = "removed"
                    visibility.targetState = it
                }
            }

            AnimatedVisibility(
                visibleState = visibility,
                exit = exitTransition(),
                enter = enterTransition()
            ) {
                ColorItemContent(item) {
//                        listState.selectedItem = item
                }
            }
        }
    }
}

@Composable
private fun Controls(
    snapshot: ItemSnapshot,
    onAdd: (color: Color) -> Unit,
    onRemove: () -> Unit,
    onClear: () -> Unit,
) {
    Row(Modifier.fillMaxWidth()) {
        ControlButton(
            modifier = Modifier.weight(1f),
            text = "Add",
            onClick = { onAdd(randomColor()) }
        )
        ControlButton(
            modifier = Modifier.weight(1f),
            text = "Remove",
            enabled = snapshot.items.isNotEmpty(),
            onClick = { onRemove() }
        )
        ControlButton(
            modifier = Modifier.weight(1f),
            text = "Clear All",
            enabled = snapshot.items.isNotEmpty(),
            onClick = { onClear() }
        )
    }
}

@Composable
private fun Info(snapshot: ItemSnapshot) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                fontSize = 13.sp,
//                modifier = Modifier.weight(1f),
//                text = "last removal at = ${listState.removalIndex}"
//            )
//            Text(
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                fontSize = 13.sp,
//                modifier = Modifier.weight(1f),
//                text = "selected at = ${listState.selectedIndex}"
//            )
//        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f),
                text = "items # = ${snapshot.items.size}"
            )
        }
    }
}

@Composable
private fun ControlButton(
    modifier: Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick,
        enabled = enabled,
        modifier = modifier.padding(15.dp)
    ) {
        Text(text)
    }
}

@Composable
private fun enterTransition() = slideInHorizontally(
    animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
)

@Composable
private fun exitTransition() = slideOutHorizontally(
    animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
)

fun randomColor() = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
