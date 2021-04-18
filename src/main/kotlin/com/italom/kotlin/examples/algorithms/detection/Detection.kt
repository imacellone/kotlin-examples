package com.italom.kotlin.examples.algorithms.detection

fun <T, R : Comparable<R>> minByOrNull(
    elements: Iterable<T>,
    selector: (T) -> R,
    fromIndexInclusive: Int = 0,
    toIndexExclusive: Int? = null
): T? {
    return when (val index = indexOfMinByOrNull(elements, selector, fromIndexInclusive, toIndexExclusive)) {
        -1 -> null
        else -> elements.toList()[index]
    }
}

fun <T, R : Comparable<R>> indexOfMinByOrNull(
    elements: Iterable<T>,
    selector: (T) -> R,
    fromIndexInclusive: Int = 0,
    toIndexExclusive: Int? = null
): Int {
    val elementList = elements.toList()
    if (elementList.isEmpty()) return -1
    val actualToIndexExclusive = toIndexExclusive ?: elementList.size
    validateListRange(fromIndexInclusive, actualToIndexExclusive, elementList)
    if (elementList.size == 1) return 0

    var minValue = selector(elementList[fromIndexInclusive])
    var minIndex = fromIndexInclusive

    for (index in fromIndexInclusive until actualToIndexExclusive) {
        val currentValue = selector(elementList[index])
        if (currentValue < minValue) {
            minValue = currentValue
            minIndex = index
        }
    }
    return minIndex
}

private fun validateListRange(fromInclusive: Int, toExclusive: Int, list: List<*>) {
    if (fromInclusive < 0 || toExclusive > list.size || fromInclusive >= toExclusive) throw IndexOutOfBoundsException()
}

//
//fun <T, R : Comparable<R>> minAndMaxByOrNull(elements: Iterable<T>, selector: (T) -> R): Pair<T, T>? {
//    val iterator = elements.iterator()
//    if (!iterator.hasNext()) return null
//    var minElement = iterator.next()
//    var maxElement = minElement
//    if (!iterator.hasNext()) return Pair(minElement, maxElement)
//    var minValue = selector(minElement)
//    var maxValue = minValue
//    do {
//        val currentElement = iterator.next()
//        val currentValue = selector(currentElement)
//        if (currentValue < minValue) {
//            minValue = currentValue
//            minElement = currentElement
//        } else if (currentValue > maxValue) {
//            maxValue = currentValue
//            maxElement = currentElement
//        }
//    } while (iterator.hasNext())
//    return Pair(minElement, maxElement)
//}