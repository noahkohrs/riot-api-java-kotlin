package com.noahkohrs.riot.api.lol.status

import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.dtos.PlatformDataDto
import com.noahkohrs.riot.api.values.Region
import feign.RequestLine

public class StatusApi(
    apiKey: String,
    region: Region,
) {
    private val apiClient =
        RegionApiClientFactory
            .create(apiKey, region)
            .createApiClient(StatusApiClient::class.java)

    public fun getPlatformData(): PlatformDataDto = apiClient.getPlatformData()

    private interface StatusApiClient {
        @RequestLine("GET /lol/status/v4/platform-data")
        fun getPlatformData(): PlatformDataDto
    }
}
