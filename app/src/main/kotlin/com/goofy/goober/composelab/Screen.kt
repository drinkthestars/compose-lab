package com.goofy.goober.composelab

sealed class Screen {
    abstract val route: String

    object LabsHome: Screen() {
        override val route: String = "Labs Home"
    }

    sealed class Gestures: Screen() {
        object DragAndroidView: Gestures() {
            override val route: String = "Drag Android View"
        }
    }

    sealed class Lists: Screen() {
        object GiphyGifSearch: Lists() {
            override val route: String = "Giphy Gif Search"
        }
    }
}
