package com.goofy.goober.composelab.drawing

fun Int.map(
    oldMin: Float,
    oldMax: Float,
    newMin: Float,
    newMax: Float
): Float {
    return (((this - oldMin) / (oldMax - oldMin)) * (newMax - newMin) + newMin)
}
