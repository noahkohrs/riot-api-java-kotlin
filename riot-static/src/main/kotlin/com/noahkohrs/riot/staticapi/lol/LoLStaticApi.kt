package com.noahkohrs.riot.staticapi.lol

import com.noahkohrs.riot.staticapi.ChampionDto
import com.noahkohrs.riot.staticapi.ChampionSummaryDto
import com.noahkohrs.riot.staticapi.ResponseWrapper
import com.noahkohrs.riot.staticapi.StaticRiotApiFactory
import com.noahkohrs.riot.staticapi.values.Image
import feign.Param
import feign.RequestLine

private const val VERSION = "14.18.1"
private const val LANG = "en_US"

public class LoLStaticApi(
    private val version: String = VERSION,
    private val lang: String = LANG,
) {
    private val ddragonClient = StaticRiotApiFactory.createJsonClient(LoLStaticApiClient::class.java, "https://ddragon.leagueoflegends.com")

    private val ddragonDynamicClient =
        StaticRiotApiFactory.createJsonClient(
            LoLStaticDynamicApiClient::class.java,
            "https://ddragon.leagueoflegends.com/cdn/$version/data/$lang/",
        )

    private val ddragonPatchRelative =
        StaticRiotApiFactory.createImageClient(
            LoLStaticApiClient::class.java,
            "https://ddragon.leagueoflegends.com/cdn/$version",
        )

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
    public fun getChampions(): List<ChampionSummaryDto> = ddragonDynamicClient.getChampions().data.values.toList()

    /**
     * Get champion.
     */
    public fun getChampion(championName: String): ChampionDto = ddragonDynamicClient.getChampion(championName).data.values.first()

    /**
     * Get champion splash.
     */
    public fun getChampionSplash(championName: String, skinNum: Int): Image = ddragonImgClient.getChampionSplash(championName, skinNum)

    /**
     * Get passive.
     */
    public fun getPassive(name: String): Image = ddragonPatchRelative.getPassive(name)

    /**
     * Get spell.
     */
    public fun getSpell(name: String): Image = ddragonPatchRelative.getSpell(name)

    internal interface LoLStaticApiClient {
        @RequestLine("GET /api/versions.json")
        fun getVersions(): List<String>

        // https://ddragon.leagueoflegends.com/cdn/languages.json
        @RequestLine("GET /cdn/languages.json")
        fun getLanguages(): List<String>

        @RequestLine("GET /cdn/img/champion/splash/{championName}_{skinNum}.jpg")
        fun getChampionSplash(
            @Param("championName") championName: String,
            @Param("skinNum") skinNum: Int,
        ): Image

        @RequestLine("GET /img/passive/{img}")
        fun getPassive(
            @Param("img") img: String,
        ): Image

        @RequestLine("GET /img/spell/{img}")
        fun getSpell(
            @Param("img") name: String,
        ): Image

        @RequestLine("GET /img/item/{img}")
        fun getItem(
            /**
             * Item ID +.png
             */
            @Param("name") img: String,
        ): Image
    }

    internal interface LoLStaticDynamicApiClient {
        @RequestLine("GET /champion.json")
        fun getChampions(): ResponseWrapper<Map<String, ChampionSummaryDto>>

        @RequestLine("GET /champion/{championName}.json")
        fun getChampion(
            @Param("championName") championName: String,
        ): ResponseWrapper<Map<String, ChampionDto>>

        @RequestLine("GET /item.json")
        fun getItems(): ResponseWrapper<Map<String, Any>>
    }
}
