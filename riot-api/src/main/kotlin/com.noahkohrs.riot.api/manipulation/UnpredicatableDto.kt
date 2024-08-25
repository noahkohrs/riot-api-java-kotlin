package com.noahkohrs.riot.api.manipulation

import com.squareup.moshi.*
import java.lang.reflect.Type

public abstract class UnpredictableDto {
    public val additionalData: JsonObject = JsonObject(mutableMapOf())
}

// Custom JsonAdapter
internal class UnpredictableDtoAdapter<T : UnpredictableDto>(
    private val delegate: JsonAdapter<T>,
    private val moshi: Moshi,
) : JsonAdapter<T>() {
    override fun fromJson(reader: JsonReader): T? {
        val jsonValue = reader.readJsonValue() as? Map<*, *>
        val result = delegate.fromJsonValue(jsonValue)

        if (result is UnpredictableDto && jsonValue != null) {
            for ((key, value) in jsonValue) {
                if (key is String && key !in result::class.java.declaredFields.map { it.name }) {
                    value?.let { result.additionalData.add(key, value) }
                }
            }
        }

        return result
    }

    override fun toJson(writer: JsonWriter, value: T?) {
//        val jsonValue = delegate.toJsonValue(value) as? MutableMap<Any?, Any?>
//
//        if (value is UnpredictableDto && jsonValue != null) {
//            value.additionalData.data.forEach { (key, additionalValue) ->
//                jsonValue[key] = additionalValue
//            }
//        }
//
//        writer.jsonValue(jsonValue)
        delegate.toJson(writer, value)
        // Not necessary for now, might be to do later on if needed
    }
}

internal class UnpredictableDtoAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        return if (UnpredictableDto::class.java.isAssignableFrom(rawType)) {
            val delegate = moshi.nextAdapter<UnpredictableDto>(this, type, annotations)
            UnpredictableDtoAdapter(delegate, moshi)
        } else {
            null
        }
    }
}
