package com.goofy.goober.composelab.lists.animations

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

@Composable
fun rememberListState(initItems: List<Item>): ListState {
    return remember { ListState(initItems) }
}

class ListState(initialItems: List<Item>) {

    var removalIndex by mutableStateOf(0)
    var selectedItem by mutableStateOf<Item?>(null)

    val size: Int get() = items.size
    val items = mutableStateListOf(*initialItems.toTypedArray())
    val isEmpty get() = items.isEmpty()
    val selectedIndex: Int?
        get() {
            return selectedItem
                ?.let { items.indexOf(selectedItem) }
                ?.let { if (it < 0) null else it }
        }

    fun deletions(): Flow<Item> {
        return snapshotFlow { items.firstOrNull { it.isDeleted } }
            .filterNotNull()
            .onEach {
                items.filter { !it.isDeleted }
                    .forEach { it.visibilityTransitionState = MutableTransitionState(true) }
            }
    }

    fun add(color: Color) {
        items.add(
            Item("${items.size + 1}", color)
        )
    }

    fun removeLast() {
        items[selectedIndex ?: size - 1].visibilityTransitionState.targetState = false
        selectedItem = null
    }

    fun pruneItems() {
        items.removeAll(items.filter { it.isDeleted })
    }

    fun clear() {
        items.forEach {
            it.visibilityTransitionState.targetState = false
        }
    }
}

class Item(
    val id: String,
    val color: Color,
    animateInitialVisibility: Boolean = true
) {
    var visibilityTransitionState = MutableTransitionState(
        initialState = !animateInitialVisibility
    ).apply { targetState = true }

    val isDeleted get() = visibilityTransitionState.isIdle && !visibilityTransitionState.targetState
}
