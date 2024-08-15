package com.noahkohrs.riot.api

import com.noahkohrs.riot.api.values.AccountRegion
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Region
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import feign.*
import feign.moshi.MoshiDecoder
import feign.moshi.MoshiEncoder

internal class RiotApiRequestInterceptor(private val apiKey: String, private val debug: Boolean = false) : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        template.query("api_key", apiKey)
        if (debug) {
            println("Request:")
            println(template.url())
            println(template.headers())
            println(template.queries())
        }
    }
}

internal class ApiClientFactory(private val baseUrl: String, private val apiKey: String, private val debug: Boolean = false) {
    // create a MoshiEncoder and MoshiDecoder
    fun <T> createApiClient(apiType: Class<T>?): T {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Feign.builder()
            .decoder(MoshiDecoder(moshi))
            .encoder(MoshiEncoder(moshi))
            .requestInterceptor(RiotApiRequestInterceptor(apiKey, debug))
            .target(apiType, baseUrl)
    }
}

internal object RegionApiClientFactory {
    fun create(
        apiKey: String,
        region: Region,
        debug: Boolean = false,
    ): ApiClientFactory {
        val baseUrl = "https://$region.api.riotgames.com"
        return ApiClientFactory(baseUrl, apiKey, debug)
    }
}

internal object AccountApiClientFactory {
    fun create(
        apiKey: String,
        path: AccountRegion,
        debug: Boolean = false,
    ): ApiClientFactory {
        val baseUrl = "https://$path.api.riotgames.com"
        return ApiClientFactory(baseUrl, apiKey, debug)
    }
}

internal object GlobalRegionApiClientFactory {
    fun create(
        apiKey: String,
        globalRegion: GlobalRegion,
        debug: Boolean = false,
    ): ApiClientFactory {
        val baseUrl = "https://$globalRegion.api.riotgames.com"
        return ApiClientFactory(baseUrl, apiKey, debug)
    }
}
