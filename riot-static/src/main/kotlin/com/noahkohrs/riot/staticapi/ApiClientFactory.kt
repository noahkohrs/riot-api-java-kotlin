package com.noahkohrs.riot.staticapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import feign.Feign
import feign.moshi.MoshiDecoder
import feign.moshi.MoshiEncoder
import java.lang.reflect.Type

internal object StaticRiotApiFactory {
    fun <T> createJsonClient(clazz: Class<T>, baseUrl: String): T {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Feign.builder()
            .decoder(MoshiDecoder(moshi))
            .encoder(MoshiEncoder(moshi))
            .target(clazz, baseUrl)
    }

    fun <T> createImageClient(clazz: Class<T>, baseUrl: String): T {
        return Feign.builder()
            .decoder(ByteArrayDecoder())
            .target(clazz, baseUrl)
    }
}

internal class ByteArrayDecoder : feign.codec.Decoder {
    override fun decode(response: feign.Response, type: Type): Any {
        return response.body().asInputStream().readBytes()
    }
}
