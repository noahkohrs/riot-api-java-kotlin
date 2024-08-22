package org.example

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type

abstract class UnpredictableDto {
    val additionalData: MutableMap<String, Any> = mutableMapOf()
}

data class ExampleDto(
    val a: String,
    val b: String
) : UnpredictableDto()

class UnpredictableDtoJsonAdapter<T : UnpredictableDto>(
    private val delegateAdapter: JsonAdapter<T>,
    private val moshi: Moshi,
    private val type: Type
) : JsonAdapter<T>() {

    override fun fromJson(reader: JsonReader): T? {
        val jsonValue = reader.readJsonValue() as? Map<String, Any> ?: return null

        val knownProps = mutableMapOf<String, Any>()
        val additionalData = mutableMapOf<String, Any>()

        // Use reflection to figure out known properties of the DTO
        val tempAdapter = moshi.adapter<Map<String, Any>>(type)
        for ((key, value) in jsonValue) {
            val tempValue = tempAdapter.fromJsonValue(mapOf(key to value))
            if (tempValue != null && key in jsonValue.keys) {
                knownProps[key] = value
            } else {
                additionalData[key] = value
            }
        }

        val dtoInstance = delegateAdapter.fromJsonValue(knownProps) ?: return null

        if (dtoInstance is UnpredictableDto) {
            dtoInstance.additionalData.putAll(additionalData)
        }

        return dtoInstance
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        if (value == null) {
            writer.nullValue()
            return
        }

        val jsonValue = delegateAdapter.toJsonValue(value) as Map<String, Any>

        writer.beginObject()

        jsonValue.forEach { (key, v) ->
            writer.name(key)
            writer.value(v.toString())  // Adjust this for more complex objects if necessary
        }

        value.additionalData.forEach { (key, v) ->
            writer.name(key)
            writer.value(v.toString())  // Adjust this for more complex objects if necessary
        }

        writer.endObject()
    }
}

class UnpredictableDtoJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        println("Artyom: $rawType")
        if (UnpredictableDto::class.java.isAssignableFrom(rawType)) {
            val delegateAdapter = moshi.nextAdapter<Any>(this, type, annotations) as JsonAdapter<UnpredictableDto>
            return UnpredictableDtoJsonAdapter(delegateAdapter, moshi, type)
        }
        return null
    }
}

fun main() {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())  // Necessary for Kotlin data classes
        .add(UnpredictableDtoJsonAdapterFactory())  // Our custom adapter
        .build()

    val json = """{
        "a": "value A",
        "b": "value B",
        "c": "value C",
        "d": {"nested": "value"}
    }"""

    val adapter = moshi.adapter(ExampleDto::class.java)
    val exampleDto = adapter.fromJson(json)

    println(exampleDto)
    println(exampleDto?.additionalData)  // Should contain "c" and "d"
}
