package com.goofy.goober.composelab.lists.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import kotlin.random.Random

@Preview
@Composable
private fun AnimatedVisibilityLazyColumnPreview() {
    Surface {
        AnimatedVisibilityLazyColumn()
    }
}

@Composable
fun AnimatedVisibilityLazyColumn() {
    val listState = rememberListState(initialItems())
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(100.dp))
        Info(listState)
        Spacer(modifier = Modifier.height(8.dp))
        Controls(listState)
        Spacer(modifier = Modifier.height(8.dp))
        ColorList(listState)
    }
}

@Composable
private fun ColorList(listState: ListState) {
    LaunchedEffect(listState) { listState.deletions().collect { listState.pruneItems() } }
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(listState.items) { _, item ->
            key(item) {
                AnimatedVisibility(
                    visibleState = item.visibilityTransitionState,
                    exit = exitTransition(),
                    enter = enterTransition()
                ) {
                    ColorItemContent(item, listState) { listState.selectedItem = item }
                }
            }
        }
    }
}

@Composable
private fun Controls(listState: ListState) {
    Row(Modifier.fillMaxWidth()) {
        ControlButton(
            modifier = Modifier.weight(1f),
            text = "Add",
            onClick = { listState.add(randomColor()) }
        )
        ControlButton(
            modifier = Modifier.weight(1f),
            text = "Remove",
            enabled = !listState.isEmpty,
            onClick = { listState.removeLast() }
        )
        ControlButton(
            modifier = Modifier.weight(1f),
            text = "Clear All",
            enabled = !listState.isEmpty,
            onClick = { listState.clear() }
        )
    }
}

@Composable
private fun Info(listState: ListState) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f),
                text = "last removal at = ${listState.removalIndex}"
            )
            Text(
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f),
                text = "selected at = ${listState.selectedIndex}"
            )
        }
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
                text = "items # = ${listState.items.size}"
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
private fun ColorItemContent(
    item: Item,
    listState: ListState,
    onClick: () -> Unit
) {
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(50.dp)
                .background(color = item.color, shape = RoundedCornerShape(8.dp))
                .clickable { onClick() }
        ) {
            Text(
                color = Color.Black,
                textAlign = TextAlign.Center,
                text = if (listState.selectedItem == item) "selected" else ""
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun enterTransition() = expandVertically(
    animationSpec = spring(
        stiffness = Spring.StiffnessVeryLow
    )
)

@Composable
private fun exitTransition() = shrinkVertically(
    animationSpec = spring(
        stiffness = Spring.StiffnessMedium
    )
)

private fun randomColor() = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())

private fun initialItems() = listOf(
    Item("0", Color(0xffBCF8FF), animateInitialVisibility = false),
    Item("1", Color(0xff8AEAE9), animateInitialVisibility = false),
    Item("2", Color(0xff46CECA), animateInitialVisibility = false)
)
