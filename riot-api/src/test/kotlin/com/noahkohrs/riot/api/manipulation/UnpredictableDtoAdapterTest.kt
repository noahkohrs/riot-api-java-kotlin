package com.noahkohrs.riot.api.manipulation

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnpredictableDtoAdapterTest {
    private data class ExampleDto(
        @Json(name = "a")
        val a: String = "",
        @Json(name = "b")
        val b: String = "",
    ) : UnpredictableDto()

    private val moshi =
        Moshi.Builder()
            .add(UnpredictableDtoAdapterFactory())
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()) // Ensure you have this dependency for Kotlin support
            .build()

    private val adapter = moshi.adapter(ExampleDto::class.java)

    @Test
    fun fromJson_withAdditionalFields() {
        val json = """
        {
            "a": "value A",
            "b": "value B",
            "c": "value C",
            "d": 123,
            "e": {"nestedKey": "nestedValue"}
        }
        """

        val exampleDto = adapter.fromJson(json)

        assertNotNull(exampleDto)
        assertEquals("value A", exampleDto?.a)
        assertEquals("value B", exampleDto?.b)

        // Test additionalData
        assertEquals("value C", exampleDto?.additionalData?.getString("c"))
        assertEquals(123, exampleDto?.additionalData?.getInt("d"))
        assertEquals("nestedValue", exampleDto?.additionalData?.getObject("e")?.getString("nestedKey"))
    }

    @Test
    fun fromJson_withoutAdditionalFields() {
        val json = """
        {
            "a": "value A",
            "b": "value B"
        }
        """

        val exampleDto = adapter.fromJson(json)

        assertNotNull(exampleDto)
        assertEquals("value A", exampleDto?.a)
        assertEquals("value B", exampleDto?.b)

        // Test additionalData should be empty
        assertTrue(exampleDto?.additionalData?.isEmpty() == true)
    }

    @Test
    fun fromJson_withInvalidAdditionalFields() {
        val json = """
        {
            "a": "value A",
            "b": "value B",
            "c": true
        }
        """

        val exampleDto = adapter.fromJson(json)

        assertNotNull(exampleDto)
        assertEquals("value A", exampleDto?.a)
        assertEquals("value B", exampleDto?.b)

        // Test additionalData with invalid field types
        assertEquals(true, exampleDto?.additionalData?.getBoolean("c"))
    }

    @Test
    fun fromJson_withEmptyJson() {
        val json = "{}"

        val exampleDto = adapter.fromJson(json)

        assertNotNull(exampleDto)

        // Test additionalData should be empty since no fields are present
        assertTrue(exampleDto?.additionalData?.isEmpty() == true)
    }
}
