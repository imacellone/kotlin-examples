package com.italom.kotlin.examples.algorithms.ordering

fun doMergeSort(a: IntArray, b: IntArray) = doMergeSort(a + b, secondHalfIndex = a.size)

fun doMergeSort(
    originalArray: IntArray,
    firstHalfIndex: Int = 0,
    secondHalfIndex: Int,
    secondHalfEndIndexExcl: Int = originalArray.size
): IntArray {
    val result = IntArray(originalArray.size) // Let's not change the original array.

    for (i in 0 until firstHalfIndex)
        result[i] = originalArray[i]

    for (i in secondHalfEndIndexExcl until originalArray.size)
        result[i] = originalArray[i]

    var resultIndex = firstHalfIndex
    var part1Index = firstHalfIndex
    var part2Index = secondHalfIndex

    while (part1Index < secondHalfIndex && part2Index < secondHalfEndIndexExcl)
        result[resultIndex++] = if (originalArray[part1Index] < originalArray[part2Index]) originalArray[part1Index++] else originalArray[part2Index++]

    while (part1Index < secondHalfIndex)
        result[resultIndex++] = originalArray[part1Index++]

    while (part2Index < secondHalfEndIndexExcl)
        result[resultIndex++] = originalArray[part2Index++]

    return result
}
