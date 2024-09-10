package com.noahkohrs.riot.api.templates

import apis.template.SomeResponse
import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Platform
import feign.RequestLine

// TODO: Replace ___ with the name of the API
public class ExampleApi internal constructor(
    apiKey: String,
    // TODO: Remove either region or globalRegion depending on the https://developer.riotgames.com/apis
    platform: Platform,
    globalRegion: GlobalRegion,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(ExampleApiClient::class.java)

    // TODO: Add the functions linked to the endpoints as wanted
    //  and with possible parameters and post treatments if needed
    public fun getSomeValue(): SomeResponse = apiClient.getSomeValue()

    private interface ExampleApiClient {
        // TODO: Add the endpoints: https://developer.riotgames.com/apis

        @RequestLine("GET /path/to/the/endpoint")
        fun getSomeValue(): SomeResponse
    }
}

// TODO: Generate a test for this class by right-clicked on <ClassName> -> Generate -> Test... -> OK
// TODO: Add the test in the test folder.
