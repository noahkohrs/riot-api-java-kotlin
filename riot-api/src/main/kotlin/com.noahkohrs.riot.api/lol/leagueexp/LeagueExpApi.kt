package com.noahkohrs.riot.api.lol.leagueexp


import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.dtos.LeagueEntryDto
import feign.RequestLine
import com.noahkohrs.riot.api.values.Region
import feign.Param


public class LeagueExpApi(
    private val apiKey: String,
    private val region: Region,
) {
    private val apiClient = RegionApiClientFactory
        .create(apiKey, region)
        .createApiClient(LeagueExpApiClient::class.java)


    /**
     Get all the league entries for given queue, tier, and division with:
        legal values for queue are: "RANKED_SOLO_5x5", "RANKED_FLEX_SR", "RANKED_FLEX_TT", "RANKED_TFT"
        legal values for tier are: "IRON", "BRONZE", "SILVER", "GOLD", "PLATINUM", "EMERALD", "DIAMOND", "MASTER", "GRANDMASTER", "CHALLENGER"
        legal values for division are: "I", "II", "III", "IV"
    */
    public fun getLeagueEntries(queue: String, tier: String, division: String, page: Int = 1): Set<LeagueEntryDto> = apiClient.getLeagueEntries(queue, tier, division, page)


    private interface LeagueExpApiClient {
        @RequestLine("GET /lol/league-exp/v4/entries/{queue}/{tier}/{division}/?page={page}")
        fun getLeagueEntries(
            @Param("queue")
            queue: String,
            @Param("tier")
            tier: String,
            @Param("division")
            division: String,
            @Param("page")
            page: Int,
        ): Set<LeagueEntryDto>
    }
}

