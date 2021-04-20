package com.italom.kotlin.examples.algorithms.ordering

fun mergeSort(elements: IntArray) = mergeSort(elements, 0, elements.size)

private fun mergeSort(elements: IntArray, start: Int, end: Int) {
    val size = end - start

    if (size <= 1) return

    val secondHalfIndex = (size / 2) + start
    mergeSort(elements, start, secondHalfIndex)
    mergeSort(elements, secondHalfIndex, end)
    mergeSort(elements, start, secondHalfIndex, end)
}

private fun mergeSort(
    originalArray: IntArray,
    firstHalfIndex: Int,
    secondHalfIndex: Int,
    secondHalfEndIndexExcl: Int
) {
    val result = IntArray(secondHalfEndIndexExcl - firstHalfIndex)

    var resultIndex = 0
    var part1Index = firstHalfIndex
    var part2Index = secondHalfIndex

    while (part1Index < secondHalfIndex && part2Index < secondHalfEndIndexExcl)
        result[resultIndex++] =
            if (originalArray[part1Index] < originalArray[part2Index]) originalArray[part1Index++]
            else originalArray[part2Index++]

    while (part1Index < secondHalfIndex)
        result[resultIndex++] = originalArray[part1Index++]

    while (part2Index < secondHalfEndIndexExcl)
        result[resultIndex++] = originalArray[part2Index++]

    resultIndex = 0
    for (i in firstHalfIndex until secondHalfEndIndexExcl) {
        originalArray[i] = result[resultIndex++]
    }
}
