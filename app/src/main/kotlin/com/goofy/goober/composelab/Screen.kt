package com.goofy.goober.composelab

sealed class Screen {
    abstract val route: String

    object LabsHome : Screen() {
        override val route: String = "Labs Home"
    }

    object Starfield : Screen() {
        override val route: String = "Starfield"
    }

    object Cube : Screen() {
        override val route: String = "Cube"
    }

    object Hypercube : Screen() {
        override val route: String = "Hypercube"
    }

    object ComposeSourceDemos : Screen() {
        override val route: String = "Compose Source Demos"
    }

    object MiscLab : Screen() {
        override val route: String = "Misc"
    }

    sealed class Animations : Screen() {
        object AlignmentChange : Gestures() {
            override val route: String = "Animated Alignment Change"
        }
    }

    sealed class Gestures : Screen() {
        object DragAndroidView : Gestures() {
            override val route: String = "Drag Android View"
        }

        object DragRotation3D : Gestures() {
            override val route: String = "Drag Rotation 3D"
        }

        object Rotation3D : Gestures() {
            override val route: String = "Rotation 3D"
        }
    }

    sealed class Lists : Screen() {
        object ItemAnimation : Lists() {
            override val route: String = "Item Animations"
        }

        object SlideItemVisibilityZStack : Lists() {
            override val route: String = "ZStack Sliding Item Visibility"
        }
    }
}
