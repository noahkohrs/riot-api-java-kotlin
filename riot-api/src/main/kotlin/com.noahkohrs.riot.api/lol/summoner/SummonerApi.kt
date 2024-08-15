package com.noahkohrs.riot.api.lol.summoner

import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.dtos.SummonerDto
import com.noahkohrs.riot.api.values.Region
import feign.Param
import feign.RequestLine

public class SummonerApi(
    private val apiKey: String,
    private val region: Region,
) {
    private val apiClient =
        RegionApiClientFactory
            .create(apiKey, region, debug = false)
            .createApiClient(SummonerApiClient::class.java)

    /**
     * Get a summoner by its RSO encrypted PUUID.
     */
    public fun getSummonerByRsoPuuid(rsoPUUID: String): SummonerResponse = SummonerResponse(apiClient.getSummonerByRsoPuuid(rsoPUUID))

    /**
     * Get a summoner by account ID.
     */
    public fun getSummonerByAccountId(encryptedAccountId: String): SummonerResponse =
        SummonerResponse(apiClient.getSummonerByAccountId(encryptedAccountId))

    /**
     * Get a summoner by PUUID.
     */
    public fun getSummonerByPuuid(encryptedPUUID: String): SummonerResponse = SummonerResponse(apiClient.getSummonerByPuuid(encryptedPUUID))

    /**
     * Get a summoner by access token.
     */
    public fun getSummonerByAccessToken(): SummonerResponse = SummonerResponse(apiClient.getSummonerByAccessToken())

    /**
     * Get a summoner by summoner ID.
     */
    public fun getSummonerBySummonerId(encryptedSummonerId: String): SummonerResponse =
        SummonerResponse(apiClient.getSummonerBySummonerId(encryptedSummonerId))

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
