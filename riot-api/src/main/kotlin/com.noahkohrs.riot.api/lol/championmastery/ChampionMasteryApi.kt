package com.noahkohrs.riot.api.lol.championmastery

import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.dtos.ChampionMasteryDto
import com.noahkohrs.riot.api.values.Region
import feign.Param
import feign.RequestLine

public class ChampionMasteryApi(
    apiKey: String,
    region: Region,
) {
    private val apiClient =
        RegionApiClientFactory
            .create(apiKey, region)
            .createApiClient(ChampionMasteryApiClient::class.java)


    public fun getAllMasteriesByPuuid(puuid: String): List<ChampionMasteryDto> = apiClient.getAllMasteriesByPuuid(puuid)

    public fun getChampMasteriesByPuuid(
        puuid: String,
        champId: Int,
    ): ChampionMasteryDto =
        apiClient.getChampMasteriesByPuuid(
            puuid,
            champId,
        )

    public fun getTopMasteriesByPuuid(
        puuid: String,
        top: Int,
    ): List<ChampionMasteryDto> = apiClient.getTopMasteriesByPuuid(puuid, top)

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

// TODO: Generate a test for this class by right-clicked on <ClassName> -> Generate -> Test... -> OK
// TODO: Add the test in the test folder.
