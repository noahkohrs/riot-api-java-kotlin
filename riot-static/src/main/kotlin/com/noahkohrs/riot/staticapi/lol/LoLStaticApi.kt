package com.noahkohrs.riot.staticapi.lol

import com.noahkohrs.riot.staticapi.ChampionDto
import com.noahkohrs.riot.staticapi.ResponseWrapper
import com.noahkohrs.riot.staticapi.StaticRiotApiFactory
import feign.RequestLine

private const val VERSION = "14.18.1"
private const val LANG = "en_US"

public class LoLStaticApi {
    private val apiClient = StaticRiotApiFactory.create(LoLStaticApiClient::class.java, "https://ddragon.leagueoflegends.com")

    /**
     * Get versions.
     */
    public fun getVersions(): List<String> = apiClient.getVersions()

    /**
     * Get latest version.
     */
    public fun getLatestVersion(): String = getVersions().first()

    // TODO: Consider rewriting this to return enumeration field. Consider languages conflict between different LoL Apis
    public fun getLanguages(): Set<String> = apiClient.getLanguages().toSet()

    /**
     * Get champions.
     */
    public fun getChampions(): List<ChampionDto> = apiClient.getChampions().data.values.toList()

    internal interface LoLStaticApiClient {
        @RequestLine("GET /api/versions.json")
        fun getVersions(): List<String>

        // https://ddragon.leagueoflegends.com/cdn/languages.json
        @RequestLine("GET /cdn/languages.json")
        fun getLanguages(): List<String>

        @RequestLine("GET /cdn/$VERSION/data/$LANG/champion.json")
        fun getChampions(): ResponseWrapper<Map<String, ChampionDto>>
    }
}
