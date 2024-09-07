package com.noahkohrs.riot.api.lol.leagueexp

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.LeagueEntryDto
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLRankedQueue
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

public class LeagueExpApi(
    private val apiKey: String,
    private val platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(LeagueExpApiClient::class.java)

    /**
     * Get all the league entries for given queue, tier, and division.
     */
    public fun getLeagueEntries(
        queue: LoLRankedQueue,
        tier: LoLTier,
        division: LoLDivision,
        page: Int = 1,
    ): Set<LeagueEntryDto> = apiClient.getLeagueEntries(queue.value, tier.value, division.value, page)

    private interface LeagueExpApiClient {
        @RequestLine("GET /lol/league-exp/v4/entries/{queue}/{tier}/{division}?page={page}")
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
