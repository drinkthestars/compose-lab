package com.goofy.goober.composelab.lists.animations

import androidx.compose.ui.graphics.Color

class Item(
    val id: String,
    val color: Color
) {
    var status: Status = Status.Visible

    sealed class Status {
        object Gone : Status()
        object Visible : Status()
    }
}
