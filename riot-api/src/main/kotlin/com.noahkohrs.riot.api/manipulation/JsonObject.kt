package com.noahkohrs.riot.api.manipulation

public class JsonObject() {
    public constructor(data: Map<String, Any>) : this() {
        for ((key, value) in data) {
            add(key, value)
        }
    }

    private val data: MutableMap<String, Any> = mutableMapOf()

    public fun getString(key: String): String? {
        return data[key]?.toString()
    }

    public fun getLong(key: String): Long? =
        when (val value = data[key]) {
            is Long -> value
            is Number -> value.toLong()
            is String -> value.toLongOrNull()
            else -> null
        }

    public fun getInt(key: String): Int? =
        when (val value = data[key]) {
            is Int -> value
            is Number -> value.toInt()
            is String -> value.toIntOrNull()
            else -> null
        }

    public fun getBoolean(key: String): Boolean? {
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

    public fun getDouble(key: String): Double? =
        when (val value = data[key]) {
            is Double -> value
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            else -> null
        }

    public fun getObject(key: String): JsonObject? {
        val map = data[key] as? Map<*, *> ?: return null
        val obj = JsonObject(map as Map<String, Any>)
        return obj
    }

    public fun <T> getArray(key: String): List<T>? {
        val ret = data[key]
        if (ret !is List<*>) {
            return null
        }
        if (ret.isEmpty()) {
            return emptyList()
        }
        if (ret[0] is Map<*, *>) {
            return ret.map { JsonObject(it as Map<String, Any>) } as List<T>
        }
        return ret as List<T>
    }

    // This class misses handling for lists.

    public fun add(key: String, value: Any) {
        data[key] = value
    }

    public fun isEmpty(): Boolean {
        return data.isEmpty()
    }
}
