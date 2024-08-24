package com.noahkohrs.riot.api.manipulation

internal class JsonObject() {
    constructor(data: Map<String, Any>) : this() {
        for ((key, value) in data) {
            add(key, value)
        }
    }

    private val data: MutableMap<String, Any> = mutableMapOf()

    fun getString(key: String): String? {
        return data[key]?.toString()
    }

    fun getInt(key: String): Int? =
        when (val value = data[key]) {
            is Int -> value
            is Number -> value.toInt()
            is String -> value.toIntOrNull()
            else -> null
        }

    fun getBoolean(key: String): Boolean? {
        return when (val value = data[key]) {
            is Boolean -> value
            is String ->
                when (value.lowercase()) {
                    "true" -> true
                    "false" -> false
                    else -> null
                }
            else -> null
        }
    }

    fun getDouble(key: String): Double? =
        when (val value = data[key]) {
            is Double -> value
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            else -> null
        }

    fun getObject(key: String): JsonObject? {
        val map = data[key] as? JsonObject
        return map
    }

    // This class misses handling for lists.

    fun add(key: String, value: Any) {
        if (value is Map<*, *>) {
            data[key] = JsonObject(value as Map<String, Any>)
        } else {
            data[key] = value
        }
    }

    fun isEmpty(): Boolean {
        return data.isEmpty()
    }
}
