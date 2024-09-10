package com.noahkohrs.riot.api.lol.status

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.PlatformDataDto
import com.noahkohrs.riot.api.values.Platform
import feign.RequestLine

public class StatusApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(StatusApiClient::class.java)

    public fun getPlatformData(): PlatformData = PlatformData.fromDto(apiClient.getPlatformData())

    private interface StatusApiClient {
        @RequestLine("GET /lol/status/v4/platform-data")
        fun getPlatformData(): PlatformDataDto
    }
}
