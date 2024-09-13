package com.noahkohrs.riot.staticapi.lol

import com.noahkohrs.riot.staticapi.ChampionDto
import com.noahkohrs.riot.staticapi.ChampionSummaryDto
import com.noahkohrs.riot.staticapi.ResponseWrapper
import com.noahkohrs.riot.staticapi.StaticRiotApiFactory
import feign.Param
import feign.RequestLine

private const val VERSION = "14.18.1"
private const val LANG = "en_US"

public class LoLStaticApi {
    private val ddragonClient = StaticRiotApiFactory.createJsonClient(LoLStaticApiClient::class.java, "https://ddragon.leagueoflegends.com")
    private val ddragonImgClient =
        StaticRiotApiFactory.createImageClient(
            LoLStaticApiClient::class.java,
            "https://ddragon.leagueoflegends.com",
        )

    /**
     * Get versions.
     */
    public fun getVersions(): List<String> = ddragonClient.getVersions()

    /**
     * Get latest version.
     */
    public fun getLatestVersion(): String = getVersions().first()

    // TODO: Consider rewriting this to return enumeration field. Consider languages conflict between different LoL Apis
    public fun getLanguages(): Set<String> = ddragonClient.getLanguages().toSet()

    /**
     * Get champions.
     */
    public fun getChampions(): List<ChampionSummaryDto> = ddragonClient.getChampions().data.values.toList()

    /**
     * Get champion.
     */
    public fun getChampion(championName: String): ChampionDto = ddragonClient.getChampion(championName).data.values.first()

    /**
     * Get champion splash.
     */
    public fun getChampionSplash(championName: String, skinNum: Int): ByteArray = ddragonImgClient.getChampionSplash(championName, skinNum)

    internal interface LoLStaticApiClient {
        @RequestLine("GET /api/versions.json")
        fun getVersions(): List<String>

        // https://ddragon.leagueoflegends.com/cdn/languages.json
        @RequestLine("GET /cdn/languages.json")
        fun getLanguages(): List<String>

        @RequestLine("GET /cdn/$VERSION/data/$LANG/champion.json")
        fun getChampions(): ResponseWrapper<Map<String, ChampionSummaryDto>>

        @RequestLine("GET /cdn/$VERSION/data/$LANG/champion/{championName}.json")
        fun getChampion(
            @Param("championName") championName: String,
        ): ResponseWrapper<Map<String, ChampionDto>>

        @RequestLine("GET /cdn/img/champion/splash/{championName}_{skinNum}.jpg")
        fun getChampionSplash(
            @Param("championName") championName: String,
            @Param("skinNum") skinNum: Int,
        ): ByteArray
    }
}
