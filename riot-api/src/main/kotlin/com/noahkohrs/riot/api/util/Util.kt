package com.noahkohrs.riot.api.util

/**
 * Remove null elements of null keys and null values from a map
 */
@Suppress("UNCHECKED_CAST")
public fun <K, V> Map<K?, V?>.removeNulls(): Map<K, V> = this.filterKeys { it != null }.filterValues { it != null } as Map<K, V>

/**
 * Map with the transform function while skipping each element for each the transform functions throws an exception
 */
public fun <K, V, R> Map<K, V>.mapSafe(transform: (Pair<K, V>) -> R): List<R> {
    val result = mutableListOf<R>()
    for ((k, v) in this) {
        try {
            result.add(transform(k to v))
        } catch (e: Exception) {
            // skip
        }
    }
    return result
}

/**
 * Map with the transform function while skipping each element for each the transform functions throws an exception
 */
public fun <T, R1, R2> List<T>.associateBySafe(transform: (T) -> Pair<R1, R2>): Map<R1, R2> {
    val result = mutableMapOf<R1, R2>()
    for (element in this) {
        try {
            val (key, value) = transform(element)
            result[key] = value
        } catch (e: Exception) {
            // skip
        }
    }
    return result
}
