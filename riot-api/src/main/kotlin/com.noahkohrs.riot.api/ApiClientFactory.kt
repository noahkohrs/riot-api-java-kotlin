package com.noahkohrs.riot.api

import com.noahkohrs.riot.api.manipulation.UnpredictableDtoAdapterFactory
import com.noahkohrs.riot.api.statics.StaticsRiotData
import com.noahkohrs.riot.api.values.AccountRegion
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Platform
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import feign.*
import feign.codec.ErrorDecoder
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
    private val moshi =
        Moshi.Builder()
            .add(UnpredictableDtoAdapterFactory())
            .add(KotlinJsonAdapterFactory())
            .build()

    fun <T> createApiClient(apiType: Class<T>?): T {
        return Feign.builder()
            .decoder(MoshiDecoder(moshi))
            .encoder(MoshiEncoder(moshi))
            .errorDecoder(RiotApiErrorDecoder())
            .retryer(RiotApiRetryer())
            .requestInterceptor(RiotApiRequestInterceptor(apiKey, debug))
            .target(apiType, baseUrl)
    }
}

internal object PlatformApiClientFactory {
    fun create(
        apiKey: String,
        platform: Platform,
        debug: Boolean = false,
    ): ApiClientFactory {
        val baseUrl = "https://$platform.api.riotgames.com"
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

/**
 * Planned to be replaced by an entire new module for the project.
 *
 * Do not expend.
 */
internal object StaticRiotDragoonFactory {
    private const val STATIC_API_URL = "https://static.developer.riotgames.com"

    fun create(): StaticsRiotData.StaticDragoonApi {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Feign.builder()
            .decoder(MoshiDecoder(moshi))
            .encoder(MoshiEncoder(moshi))
            .target(StaticsRiotData.StaticDragoonApi::class.java, STATIC_API_URL)
    }
}

internal class RiotApiErrorDecoder : ErrorDecoder {
    private val defaultDecoder = ErrorDecoder.Default()

    override fun decode(methodKey: String, response: Response): Exception {
        return if (response.status() == 429) {
            val retryAfter = response.headers()["Retry-After"]?.firstOrNull() ?: "1"
            val waitTimeInSeconds = retryAfter.toLongOrNull() ?: 1

            return RetryableException(
                response.status(),
                "Rate limit exceeded. Retrying after $waitTimeInSeconds seconds",
                response.request().httpMethod(),
                waitTimeInSeconds * 1000,
                response.request(),
            )
        } else {
            defaultDecoder.decode(methodKey, response)
        }
    }
}

internal class RiotApiRetryer : Retryer {
    override fun continueOrPropagate(e: RetryableException) {
        try {
            Thread.sleep(e.retryAfter())
        } catch (ignored: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    override fun clone(): Retryer {
        return RiotApiRetryer()
    }
}
