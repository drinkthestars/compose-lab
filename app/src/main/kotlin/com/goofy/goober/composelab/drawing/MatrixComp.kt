package com.goofy.goober.composelab.cube

fun Array<FloatArray>.multiply(
    array: FloatArray,
    firstRows: Int,
    firstColumn: Int,
    secondColumn: Int
): FloatArray {
    val product = FloatArray(firstRows) { 0f }
    for (i in 0 until firstRows) {
        repeat((0 until secondColumn).count()) {
            for (k in 0 until firstColumn) {
                product[i] += this[i][k] * array[k]
            }
        }
    }
    return product
}
