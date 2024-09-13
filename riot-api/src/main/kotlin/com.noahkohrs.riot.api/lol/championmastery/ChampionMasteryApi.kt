package com.noahkohrs.riot.api.lol.championmastery

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.annotations.LinkToStaticApi
import com.noahkohrs.riot.api.dtos.ChampionMasteryDto
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

/**
 * The ChampionMastery API provides information about champion masteries.
 *
 * [Riot ChampionMastery API](https://developer.riotgames.com/apis#champion-mastery-v4)
 */
public class ChampionMasteryApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(ChampionMasteryApiClient::class.java)

    public fun getAllMasteriesByPuuid(puuid: String): List<ChampionMastery> =
        apiClient.getAllMasteriesByPuuid(puuid).map {
            ChampionMastery.fromDto(it)
        }

    public fun getChampMasteriesByPuuid(
        puuid: String,
        @LinkToStaticApi
        champId: Int,
    ): ChampionMastery = ChampionMastery.fromDto(apiClient.getChampMasteriesByPuuid(puuid, champId))

    public fun getTopMasteriesByPuuid(
        puuid: String,
        top: Int,
    ): List<ChampionMastery> = apiClient.getTopMasteriesByPuuid(puuid, top).map { ChampionMastery.fromDto(it) }

    public fun getMasteryScoreByPuuid(puuid: String): Int = apiClient.getMasteryScoreByPuuid(puuid)

    private interface ChampionMasteryApiClient {
        @RequestLine("GET /lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}")
        fun getAllMasteriesByPuuid(
            @Param("encryptedPUUID") puuid: String,
        ): List<ChampionMasteryDto>

        @RequestLine("GET /lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}/by-champion/{championId}")
        fun getChampMasteriesByPuuid(
            @Param("encryptedPUUID") puuid: String,
            @Param("championId") champId: Int,
        ): ChampionMasteryDto

        @RequestLine("GET /lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}/top/?count={count}")
        fun getTopMasteriesByPuuid(
            @Param("encryptedPUUID") puuid: String,
            @Param("count") top: Int,
        ): List<ChampionMasteryDto>

        @RequestLine("GET /lol/champion-mastery/v4/scores/by-puuid/{encryptedPUUID}")
        fun getMasteryScoreByPuuid(
            @Param("encryptedPUUID") puuid: String,
        ): Int
    }
}
