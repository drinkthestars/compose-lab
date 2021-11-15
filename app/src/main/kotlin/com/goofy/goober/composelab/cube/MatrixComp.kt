package com.goofy.goober.composelab.cube

fun Array<FloatArray>.multiply(
    array: FloatArray,
    r1: Int,
    c1: Int,
    c2: Int
): FloatArray {
    val product = FloatArray(r1) { 0f }
    for (i in 0 until r1) {
        repeat((0 until c2).count()) {
            for (k in 0 until c1) {
                product[i] += this[i][k] * array[k]
            }
        }
    }
    return product
}
