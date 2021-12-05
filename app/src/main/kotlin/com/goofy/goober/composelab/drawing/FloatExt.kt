package com.goofy.goober.composelab.cube

fun Float.normalize(max: Float, min: Float) = (this - min) / (max - min)

fun Float.map(
    oldMin: Float,
    oldMax: Float,
    newMin: Float,
    newMax: Float
): Float {
    return ((this - oldMin) / (oldMax - oldMin)) * (newMax - newMin) + newMin
}
