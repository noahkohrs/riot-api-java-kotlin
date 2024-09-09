package com.noahkohrs.riot.api.lol.champion

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.ChampionInfo
import com.noahkohrs.riot.api.values.Platform
import feign.RequestLine

/**
 * The Champion API provides information about champion rotations.
 *
 * [Champion API](https://developer.riotgames.com/apis#champion-v3)
 */
public class ChampionApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(ChampionApiClient::class.java)

    /**
     * Returns champion rotations, including free-to-play and low-level free-to-play rotations.
     */
    public fun getChampionRotations(): ChampionRotation = ChampionRotation.fromDto(apiClient.getChampionRotations())

    private interface ChampionApiClient {
        @RequestLine("GET /lol/platform/v3/champion-rotations")
        fun getChampionRotations(): ChampionInfo
    }
}
