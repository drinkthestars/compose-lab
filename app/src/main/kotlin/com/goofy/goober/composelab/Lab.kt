package com.goofy.goober.composelab

import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

sealed class Lab(val title: String) {
    override fun toString() = title
}
class ActivityLab<T : ComponentActivity>(title: String, val activityClass: KClass<T>) : Lab(title)
class ComposableLab(val screen: Screen) : Lab(screen.route)

