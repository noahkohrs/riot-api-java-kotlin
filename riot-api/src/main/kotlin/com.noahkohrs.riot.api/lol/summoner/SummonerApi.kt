package com.noahkohrs.riot.api.lol.summoner

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.SummonerDto
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

public class SummonerApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform, debug = false)
            .createApiClient(SummonerApiClient::class.java)

    /**
     * Get a summoner by its RSO encrypted PUUID.
     */
    public fun getSummonerByRsoPuuid(
        rsoPUUID: String,
    ): SummonerResponse = SummonerResponse.fromDto(apiClient.getSummonerByRsoPuuid(rsoPUUID))

    /**
     * Get a summoner by account ID.
     */
    public fun getSummonerByAccountId(encryptedAccountId: String): SummonerResponse =
        SummonerResponse.fromDto(apiClient.getSummonerByAccountId(encryptedAccountId))

    /**
     * Get a summoner by PUUID.
     */
    public fun getSummonerByPuuid(
        encryptedPUUID: String,
    ): SummonerResponse = SummonerResponse.fromDto(apiClient.getSummonerByPuuid(encryptedPUUID))

    /**
     * Get a summoner by access token.
     */
    public fun getSummonerByAccessToken(): SummonerResponse = SummonerResponse.fromDto(apiClient.getSummonerByAccessToken())

    /**
     * Get a summoner by summoner ID.
     */
    public fun getSummonerBySummonerId(encryptedSummonerId: String): SummonerResponse =
        SummonerResponse.fromDto(apiClient.getSummonerBySummonerId(encryptedSummonerId))

    private interface SummonerApiClient {
        //    GET /fulfillment/v1/summoners/by-puuid/{rsoPUUID}Get a summoner by its RSO encrypted PUUID.
        @RequestLine("GET /fulfillment/v1/summoners/by-puuid/{rsoPUUID}")
        fun getSummonerByRsoPuuid(
            @Param("rsoPUUID")
            rsoPUUID: String,
        ): SummonerDto

        //    GET /lol/summoner/v4/summoners/by-account/{encryptedAccountId}Get a summoner by account ID.
        @RequestLine("GET /lol/summoner/v4/summoners/by-account/{encryptedAccountId}")
        fun getSummonerByAccountId(
            @Param("encryptedAccountId")
            encryptedAccountId: String,
        ): SummonerDto

        //    GET /lol/summoner/v4/summoners/by-puuid/{encryptedPUUID}Get a summoner by PUUID.
        @RequestLine("GET /lol/summoner/v4/summoners/by-puuid/{encryptedPUUID}")
        fun getSummonerByPuuid(
            @Param("encryptedPUUID")
            encryptedPUUID: String,
        ): SummonerDto

        //    GET /lol/summoner/v4/summoners/meGet a summoner by access token.
        @RequestLine("GET /lol/summoner/v4/summoners/me")
        fun getSummonerByAccessToken(): SummonerDto

        //    GET /lol/summoner/v4/summoners/{encryptedSummonerId}Get a summoner by summoner ID.
        @RequestLine("GET /lol/summoner/v4/summoners/{encryptedSummonerId}")
        fun getSummonerBySummonerId(
            @Param("encryptedSummonerId")
            encryptedSummonerId: String,
        ): SummonerDto
    }
}
