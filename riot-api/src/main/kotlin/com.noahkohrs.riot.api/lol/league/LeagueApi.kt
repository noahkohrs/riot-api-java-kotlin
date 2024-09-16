package com.noahkohrs.riot.api.lol.league

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.LeagueEntryDto
import com.noahkohrs.riot.api.dtos.LeagueListDto
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLRankedQueue
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

/**
 * The League API provides information about leagues.
 *
 * [Riot League API](https://developer.riotgames.com/apis#league-v4)
 */
public class LeagueApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(LeagueApiClient::class.java)

    /**
     * Get the challenger league for given queue.
     */
    public fun getChallengerLeague(queue: LoLRankedQueue): LeagueListResponse {
        val dto = apiClient.getChallengerLeague(queue.value)
        return LeagueListResponse.fromDto(dto)
    }

    /**
     * Get league entries in all queues for a given summoner ID.
     */
    public fun getLeagueEntriesBySummoner(encryptedSummonerId: String): Set<LeagueEntryResponse> {
        val dto = apiClient.getLeagueEntriesBySummoner(encryptedSummonerId)
        return dto.map { LeagueEntryResponse.fromDto(it) }.toSet()
    }

    /**
     * Get all the league entries.
     */
    public fun getLeagueEntries(
        queue: LoLRankedQueue,
        tier: LoLTier,
        division: LoLDivision,
        page: Int = 1,
    ): Set<LeagueEntryResponse> {
        if (tier == LoLTier.CHALLENGER || tier == LoLTier.GRANDMASTER || tier == LoLTier.MASTER) {
            throw IllegalArgumentException(
                "Tier must be a division tier between IRON and DIAMOND. " +
                    "For challenger, grandmaster and master leagues, use their respective methods.",
            )
        }
        val dto = apiClient.getLeagueEntries(queue.value, tier.value, division.value, page)
        return dto.map { LeagueEntryResponse.fromDto(it) }.toSet()
    }

    /**
     * Get the grandmaster league of a specific queue.
     */
    public fun getGrandmasterLeague(queue: LoLRankedQueue): LeagueListResponse {
        val dto = apiClient.getGrandmasterLeague(queue.value)
        return LeagueListResponse.fromDto(dto)
    }

    /**
     * Get league with given ID, including inactive entries.
     */
    public fun getLeague(leagueId: String): LeagueListResponse {
        val dto = apiClient.getLeague(leagueId)
        return LeagueListResponse.fromDto(dto)
    }

    /**
     * Get the master league for given queue.
     */
    public fun getMasterLeague(queue: LoLRankedQueue): LeagueListResponse {
        val dto = apiClient.getMasterLeague(queue.value)
        return LeagueListResponse.fromDto(dto)
    }

    private interface LeagueApiClient {
//        GET /lol/league/v4/challengerleagues/by-queue/{queue}Get the challenger league for given queue.
        @RequestLine("GET /lol/league/v4/challengerleagues/by-queue/{queue}")
        fun getChallengerLeague(
            @Param("queue") queue: String,
        ): LeagueListDto

//        GET /lol/league/v4/entries/by-summoner/{encryptedSummonerId}Get league entries in all queues for a given summoner ID.
        @RequestLine("GET /lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
        fun getLeagueEntriesBySummoner(
            @Param("encryptedSummonerId") encryptedSummonerId: String,
        ): Set<LeagueEntryDto>

        @RequestLine("GET /lol/league/v4/entries/{queue}/{tier}/{division}?page={page}")
        fun getLeagueEntries(
            @Param("queue") queue: String,
            @Param("tier") tier: String,
            @Param("division") division: String,
            @Param("page") page: Int,
        ): Set<LeagueEntryDto>

        @RequestLine("GET /lol/league/v4/grandmasterleagues/by-queue/{queue}")
        fun getGrandmasterLeague(
            @Param("queue") queue: String,
        ): LeagueListDto

        @RequestLine("GET /lol/league/v4/leagues/{leagueId}")
        fun getLeague(
            @Param("leagueId") leagueId: String,
        ): LeagueListDto

        @RequestLine("GET /lol/league/v4/masterleagues/by-queue/{queue}")
        fun getMasterLeague(
            @Param("queue") queue: String,
        ): LeagueListDto
    }
}
