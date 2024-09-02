package com.noahkohrs.riot.api.manipulation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JsonObjectTest {
    @Test
    fun getString() {
        val additionalFields = JsonObject(mapOf("key" to "value"))
        assertEquals("value", additionalFields.getString("key"))
    }

    @Test
    fun getInt() {
        val map =
            mapOf(
                "int" to 1,
                "string" to "2",
                "double" to 3.0,
                "unvalidBool" to true,
                "unvalidStr" to "unvalid",
            )
        val additionalFields = JsonObject(map)
        assertEquals(1, additionalFields.getInt("int"))
        assertEquals(2, additionalFields.getInt("string"))
        assertEquals(3, additionalFields.getInt("double"))
        assertEquals(null, additionalFields.getInt("unvalidBool"))
        assertEquals(null, additionalFields.getInt("unvalidStr"))
    }

    @Test
    fun getBoolean() {
        val map =
            mapOf(
                "bool" to true,
                "string" to "true",
                "int" to 1,
                "unvalidStr" to "unvalid",
            )
        val additionalFields = JsonObject(map)
        assertEquals(true, additionalFields.getBoolean("bool"))
        assertEquals(true, additionalFields.getBoolean("string"))
        assertEquals(null, additionalFields.getBoolean("int"))
        assertEquals(null, additionalFields.getBoolean("unvalidStr"))
    }

    @Test
    fun getDouble() {
        val map =
            mapOf(
                "double" to 1.3,
                "string" to "2.2",
                "int" to 3,
                "unvalidBool" to true,
                "unvalidStr" to "unvalid",
            )
        val additionalFields = JsonObject(map)
        assertEquals(1.3, additionalFields.getDouble("double"))
        assertEquals(2.2, additionalFields.getDouble("string"))
        assertEquals(3.0, additionalFields.getDouble("int"))
        assertEquals(null, additionalFields.getDouble("unvalidBool"))
        assertEquals(null, additionalFields.getDouble("unvalidStr"))
    }

    @Test
    fun getObject() {
        val map =
            mapOf(
                "object" to mapOf("key" to "value", "key2" to "value2"),
                "string" to "2.2",
                "int" to 3,
                "unvalidBool" to true,
                "unvalidStr" to "unvalid",
            )
        val additionalFields = JsonObject(map)
        assertEquals("value", additionalFields.getObject("object")?.getString("key"))
        assertEquals("value2", additionalFields.getObject("object")?.getString("key2"))
        assertEquals(null, additionalFields.getObject("string"))
        assertEquals(null, additionalFields.getObject("int"))
        assertEquals(null, additionalFields.getObject("unvalidBool"))
        assertEquals(null, additionalFields.getObject("unvalidStr"))
    }

    @Test
    fun getList() {
        val map =
            mapOf(
                "list" to listOf("value", "value2"),
                "listOfObj" to listOf(mapOf("key" to "value", "key2" to "value2")),
                "string" to "2.2",
                "int" to 3,
                "unvalidBool" to true,
                "unvalidStr" to "unvalid",
            )
        val additionalFields = JsonObject(map)
        assertEquals(listOf("value", "value2"), additionalFields.getArray<String>("list"))
        assertEquals("value", additionalFields.getArray<JsonObject>("listOfObj")?.get(0)?.getString("key"))
        assertEquals(null, additionalFields.getArray<String>("string"))
        assertEquals(null, additionalFields.getArray<String>("int"))
        assertEquals(null, additionalFields.getArray<String>("unvalidBool"))
    }
}
