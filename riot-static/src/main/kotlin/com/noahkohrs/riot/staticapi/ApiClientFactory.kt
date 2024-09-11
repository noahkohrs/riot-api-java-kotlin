package com.noahkohrs.riot.staticapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import feign.Feign
import feign.moshi.MoshiDecoder
import feign.moshi.MoshiEncoder

internal object StaticRiotApiFactory {
    fun <T> create(clazz: Class<T>, baseUrl: String): T {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Feign.builder()
            .decoder(MoshiDecoder(moshi))
            .encoder(MoshiEncoder(moshi))
            .target(clazz, baseUrl)
    }
}
