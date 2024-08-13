package com.noahkohrs.riot.api

import feign.Feign
import feign.RequestInterceptor
import feign.RequestTemplate
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Region


internal class ApiKeyRequestInterceptor(private val apiKey: String) : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        template.query("api_key", apiKey)
    }
}


internal class ApiClientFactory(private val baseUrl: String, private val apiKey: String) {
    fun <T> createApiClient(apiType: Class<T>?): T {
        return Feign.builder()
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
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