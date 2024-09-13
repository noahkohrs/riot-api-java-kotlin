package com.noahkohrs.riot.api.lol.match

import com.noahkohrs.riot.api.GlobalRegionApiClientFactory
import com.noahkohrs.riot.api.dtos.MatchDto
import com.noahkohrs.riot.api.dtos.TimelineDto
import com.noahkohrs.riot.api.values.GlobalRegion
import feign.HeaderMap
import feign.Param
import feign.QueryMap
import feign.RequestLine
import java.util.*

// Note: We're not planning to test for this api handling, we're just providing the gateway for the user to access the api.

/**
 * The MatchRsoApi provides match information.
 *
 * If you are looking for information about a RSO, please look at [RSO](https://support-developer.riotgames.com/hc/en-us/articles/22801670382739-RSO-Riot-Sign-On).
 *
 * [Riot Match RSO API](https://developer.riotgames.com/apis#rso-match-v1)
 *
 * @see Match if you are looking for match information without RSO.
 */
public class MatchRsoApi internal constructor(
    apiKey: String,
    globalRegion: GlobalRegion,
) {
    private val apiClient =
        GlobalRegionApiClientFactory
            .create(apiKey, globalRegion)
            .createApiClient(MatchApiClient::class.java)

    /**
     * Get a list of match ids by puuid.
     */
    @JvmOverloads
    public fun getMatchIdsByPuuid(
        authorization: String,
        startTime: Date? = null,
        endTime: Date? = null,
        queueId: Int? = null,
        type: String? = null,
        startIndex: Int = 0,
        count: Int = 20,
    ): List<String> {
        val queries = buildMatchQueries(startTime, endTime, queueId, type, startIndex, count)
        val headers = mapOf("Authorization" to authorization)
        return apiClient.getMatchIdsByPuuid(queries, headers)
    }

    /**
     * Get a match by match id.
     */
    public fun getMatchById(
        matchId: String,
        authorization: String,
    ): Match {
        val res = apiClient.getMatchById(matchId, mapOf("Authorization" to authorization))
        return Match.fromDto(res)
    }

    /**
     * Get a match timeline by match id.
     */
    public fun getMatchTimelineById(
        matchId: String,
        authorization: String,
    ): MatchTimeline {
        val res = apiClient.getMatchTimelineById(matchId, mapOf("Authorization" to authorization))
        return MatchTimeline.fromDto(res)
    }

    private interface MatchApiClient {
        @RequestLine("GET /lol/rso-match/v1/matches/ids")
        fun getMatchIdsByPuuid(
            @QueryMap
            queryMap: Map<String, Any>,
            @HeaderMap
            headers: Map<String, String>,
        ): List<String>

        @RequestLine("GET /lol/rso-match/v1/matches/{matchId}")
        fun getMatchById(
            @Param("matchId")
            matchId: String,
            @HeaderMap
            headers: Map<String, String>,
        ): MatchDto

        @RequestLine("GET /lol/rso-match/v1/matches/{matchId}/timeline")
        fun getMatchTimelineById(
            @Param("matchId")
            matchId: String,
            @HeaderMap
            headers: Map<String, String>,
        ): TimelineDto
    }
}
