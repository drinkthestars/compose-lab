
object Libs {
    const val junit = "junit:junit:4.13"
    const val material = "com.google.android.material:material:1.1.0"

    object Giphy {
        const val ui = "com.giphy.sdk:ui:2.1.17"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:4.12.0"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Project.Kotlin.version}"
        const val graphicsGlm = "kotlin.graphics:glm:0.9.9.1-4"
    }

    object Accompanist {
        private const val version = "0.24.8-beta"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object Coil {
        private const val version = "2.0.0"
        const val gif = "io.coil-kt:coil-gif:$version"
        const val compose = "io.coil-kt:coil-compose:$version"
    }

    object Coroutines {
        private const val version = "1.6.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        private const val coreKtxVersion = "1.8.0-rc01"

        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
        const val appCompat = "androidx.appcompat:appcompat:1.4.0"

        object Compose {
            const val version = "1.2.0-beta03"
            private const val toolingVersion = "1.1.1"

            const val animation = "androidx.compose.animation:animation:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$toolingVersion"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$toolingVersion"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val test = "androidx.compose.ui:ui-test:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object ConstraintLayout {
            private const val version = "1.0.1"

            const val compose = "androidx.constraintlayout:constraintlayout-compose:$version"
        }

        object Navigation {
            private const val version = "2.5.0-rc01"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val compose = "androidx.navigation:navigation-compose:$version"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.5.0-rc01"
        }

        object Lifecycle {
            private const val version = "2.5.0-rc01"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val viewModelSavedState =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        }

        object Hilt {
            const val navCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
        }

        object Test {
            private const val androidxTestVersion = "1.4.1-alpha04"
            const val core = "androidx.test:core:$androidxTestVersion"
            const val rules = "androidx.test:rules:$androidxTestVersion"

            object Ext {
                private const val version = "1.1.4-alpha04"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }
        }
    }
}
