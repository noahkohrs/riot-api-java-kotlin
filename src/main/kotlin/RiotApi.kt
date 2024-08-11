package com.noahkohrs.riot.api.sdk

import ApiClientFactory
import apis.champion.ChampionApi
import apis.match.MatchApi
import values.GlobalRegion
import values.Region

public class RiotApi(
    apiKey : String,
    region : Region,
    globalRegion: GlobalRegion
) {
    private val apiClient = ApiClientFactory("https://na1.api.riotgames.com", apiKey = apiKey)
    public val match = MatchApi(apiKey, globalRegion)
    public val champion = ChampionApi(apiKey, region)
}