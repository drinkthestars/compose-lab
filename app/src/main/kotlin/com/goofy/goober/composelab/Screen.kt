package com.goofy.goober.composelab

sealed class Screen {
    abstract val route: String

    object LabsHome : Screen() {
        override val route: String = "Labs Home"
    }

    object Starfield : Screen() {
        override val route: String = "Starfield"
    }

    object Sketch : Screen() {
        override val route: String = "Sketch"
    }

    object MatrixCodeRain : Screen() {
        override val route: String = "Matrix Code Rain"
    }

    object Cube : Screen() {
        override val route: String = "Cube"
    }

    object Hypercube : Screen() {
        override val route: String = "Hypercube"
    }

    object Recompositions : Screen() {
        override val route: String = "Recompositions"
    }

    object ComposeSourceDemos : Screen() {
        override val route: String = "Compose Source Demos"
    }

    object CaseStudies : Screen() {
        override val route: String = "Ui Case Studies"
    }

    object MiscLab : Screen() {
        override val route: String = "Misc"
    }

    sealed class Animations : Screen() {
        object AlignmentChange : Animations() {
            override val route: String = "Animated Alignment Change"
        }

        object AnimatedText : Animations() {
            override val route: String = "Animated Text"
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

    sealed class UiCaseStudies : Screen() {
        object PlantApp : UiCaseStudies() {
            override val route: String = "Planet Plants!"
        }
    }
}
