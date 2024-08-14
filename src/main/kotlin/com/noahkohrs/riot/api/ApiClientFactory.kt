package com.noahkohrs.riot.api

import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Region
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import feign.Feign
import feign.RequestInterceptor
import feign.RequestTemplate
import feign.moshi.MoshiDecoder
import feign.moshi.MoshiEncoder


internal class ApiKeyRequestInterceptor(private val apiKey: String) : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        template.query("api_key", apiKey)
    }
}


internal class ApiClientFactory(private val baseUrl: String, private val apiKey: String) {
    // create a MoshiEncoder and MoshiDecoder
    fun <T> createApiClient(apiType: Class<T>?): T {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Feign.builder()
            .decoder(MoshiDecoder(moshi))
            .encoder(MoshiEncoder(moshi))
            .requestInterceptor(ApiKeyRequestInterceptor(apiKey))
            .target(apiType, baseUrl)
    }
}

internal object RegionApiClientFactory {
    fun create(apiKey: String, region: Region): ApiClientFactory {
        val baseUrl = "https://$region.api.riotgames.com"
        return ApiClientFactory(baseUrl, apiKey)
    }
}

internal object GlobalRegionApiClientFactory {
    fun create(apiKey: String, globalRegion: GlobalRegion): ApiClientFactory {
        val baseUrl = "https://$globalRegion.api.riotgames.com"
        return ApiClientFactory(baseUrl, apiKey)
    }
}