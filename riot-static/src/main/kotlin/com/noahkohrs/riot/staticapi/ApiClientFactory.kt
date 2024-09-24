package com.noahkohrs.riot.staticapi

import com.noahkohrs.riot.staticapi.values.*
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
            .decoder(ImageDecoder())
            .target(clazz, baseUrl)
    }
}

internal class ImageDecoder : feign.codec.Decoder {
    override fun decode(response: feign.Response, type: Type): Any {
        val bytes = response.body().asInputStream().readBytes()
        response.body().asInputStream().close()
        return Image(
            url = response.request().url(),
            format = ImageFormat.parseFormat(response.request().url()),
            bytes = bytes,
        )
    }
}
