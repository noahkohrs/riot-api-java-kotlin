package com.noahkohrs.riot.api.lol.league

import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.dtos.LeagueEntryDto
import com.noahkohrs.riot.api.dtos.LeagueListDto
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLRankedQueue
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

public class LeagueApi(
    private val apiKey: String,
    private val platform: Platform,
) {
    private val apiClient =
        RegionApiClientFactory
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
    ): Set<LeagueEntryResponse> {
        val dto = apiClient.getLeagueEntries(queue.value, tier.value, division.value)
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

    private interface LeagueApiClient {
//        GET /lol/league/v4/challengerleagues/by-queue/{queue}Get the challenger league for given queue.
        @RequestLine("GET /lol/league/v4/challengerleagues/by-queue/{queue}")
        fun getChallengerLeague(
            @Param("queue")
            queue: String,
        ): LeagueListDto

//        GET /lol/league/v4/entries/by-summoner/{encryptedSummonerId}Get league entries in all queues for a given summoner ID.
        @RequestLine("GET /lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
        fun getLeagueEntriesBySummoner(
            @Param("encryptedSummonerId")
            encryptedSummonerId: String,
        ): Set<LeagueEntryDto>

//        GET /lol/league/v4/entries/{queue}/{tier}/{division}Get all the league entries.
        @RequestLine("GET /lol/league/v4/entries/{queue}/{tier}/{division}")
        fun getLeagueEntries(
            @Param("queue")
            queue: String,
            @Param("tier")
            tier: String,
            @Param("division")
            division: String,
        ): Set<LeagueEntryDto>

//        GET /lol/league/v4/grandmasterleagues/by-queue/{queue}Get the grandmaster league of a specific queue.
        @RequestLine("GET /lol/league/v4/grandmasterleagues/by-queue/{queue}")
        fun getGrandmasterLeague(
            @Param("queue")
            queue: String,
        ): LeagueListDto

//        GET /lol/league/v4/leagues/{leagueId}Get league with given ID, including inactive entries.
        @RequestLine("GET /lol/league/v4/leagues/{leagueId}")
        fun getLeague(
            @Param("leagueId")
            leagueId: String,
        ): LeagueListDto

//        GET /lol/league/v4/masterleagues/by-queue/{queue}Get the master league for given queue.
        @RequestLine("GET /lol/league/v4/masterleagues/by-queue/{queue}")
        fun getMasterLeague(
            @Param("queue")
            queue: String,
        ): LeagueListDto
    }
}
