package com.goofy.goober.composelab

sealed class Screen {
    abstract val route: String

    object LabsHome: Screen() {
        override val route: String = "Labs Home"
    }

    object Starfield: Screen() {
        override val route: String = "Starfield"
    }

    object Cube: Screen() {
        override val route: String = "Cube"
    }

    object MiscLab: Screen() {
        override val route: String = "Misc"
    }

    sealed class Gestures: Screen() {
        object DragAndroidView: Gestures() {
            override val route: String = "Drag Android View"
        }
        object DragRotation3D: Gestures() {
            override val route: String = "Drag Rotation 3D"
        }
        object Rotation3D: Gestures() {
            override val route: String = "Rotation 3D"
        }
    }

    sealed class Lists: Screen() {
        object GiphyGifSearch: Lists() {
            override val route: String = "Giphy Gif Search"
        }
        object ItemAnimation: Lists() {
            override val route: String = "Item Animations"
        }
    }
}
