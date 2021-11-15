package com.goofy.goober.composelab.lists.animations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class ItemSnapshots {

    // Source of truth of the data
    private var items = initialItems()

    // To represent deletions
    private var deletions = listOf<Item>()
    private var snapshots by mutableStateOf(ItemSnapshot(items))

    operator fun invoke() = snapshots

    fun update(update: Update) {
        when (update) {
            Update.Remove -> {
                if (items.isEmpty()) return

                // Update item status to Gone
                items[items.size - 1].status = Item.Status.Gone

                // Update deletions in here
                deletions = deletions.toMutableList().apply { add(items[items.size - 1]) }

                // Update the snapshots state
                snapshots = ItemSnapshot(items, deletions)

                // Make sure to remove the item from the backing source of truth
                items = items.toMutableList().apply { removeLast() }
            }
            is Update.Add -> {
                val addIndex = items.size
                val newItem = Item("$addIndex", update.color)
                items = items.toMutableList().apply { add(addIndex, newItem) }

                // Remove if deleted item is being added
                deletions = deletions.toMutableList().apply { removeIf { it.id == newItem.id } }

                // Update the snapshots state
                snapshots = ItemSnapshot(items, deletions)
            }
            Update.Clear -> {
                items = emptyList()
                deletions = emptyList()
                snapshots = ItemSnapshot(emptyList(), emptyList())
            }
        }
    }
}

data class ItemSnapshot(
    val items: List<Item>,
    val deletions: List<Item> = emptyList()
)

sealed class Update {
    object Remove : Update()
    object Clear : Update()
    data class Add(val color: Color) : Update()
}

fun initialItems() = listOf(
    Item("0", Color(color = 0xffBCF8FF)),
    Item("1", Color(color = 0xff8AEAE9)),
    Item("2", Color(color = 0xff46CECA))
)
