package com.noahkohrs.riot.api.lol.champion

import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.values.Region
import feign.RequestLine

/**
 * The Champion API provides information about champion rotations.
 *
 * [Champion API](https://developer.riotgames.com/apis#champion-v3)
 */
public class ChampionApi(
    apiKey: String,
    region: Region,
) {
    private val apiClient =
        RegionApiClientFactory
            .create(apiKey, region)
            .createApiClient(ChampionApiClient::class.java)

    /**
     * Returns champion rotations, including free-to-play and low-level free-to-play rotations.
     */
    public fun getChampionRotations(): ChampionResponse = apiClient.getChampionRotations()

    private interface ChampionApiClient {
        @RequestLine("GET /lol/platform/v3/champion-rotations")
        fun getChampionRotations(): ChampionResponse
    }
}
