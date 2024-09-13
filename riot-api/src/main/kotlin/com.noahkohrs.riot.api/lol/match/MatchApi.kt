package com.noahkohrs.riot.api.lol.match

import com.noahkohrs.riot.api.GlobalRegionApiClientFactory
import com.noahkohrs.riot.api.dtos.MatchDto
import com.noahkohrs.riot.api.dtos.TimelineDto
import com.noahkohrs.riot.api.values.GlobalRegion
import feign.Param
import feign.QueryMap
import feign.RequestLine
import java.util.Date

public class MatchApi internal constructor(
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
        puuid: String,
        startTime: Date? = null,
        endTime: Date? = null,
        queueId: Int? = null,
        type: String? = null,
        startIndex: Int = 0,
        count: Int = 20,
    ): List<String> {
        val queries = buildMatchQueries(startTime, endTime, queueId, type, startIndex, count)
        return apiClient.getMatchIdsByPuuid(puuid, queries)
    }

    /**
     * Get a match by match id.
     */
    public fun getMatchById(matchId: String): Match {
        val res = apiClient.getMatchById(matchId)
        return Match.fromDto(res)
    }

    /**
     * Get a match timeline by match id.
     */
    public fun getMatchTimelineById(matchId: String): MatchTimeline {
        val res = apiClient.getMatchTimelineById(matchId)
        return MatchTimeline.fromDto(res)
    }

    private interface MatchApiClient {
//        GET /lol/match/v5/matches/by-puuid/{puuid}/idsGet a list of match ids by puuid
        @RequestLine("GET /lol/match/v5/matches/by-puuid/{puuid}/ids")
        fun getMatchIdsByPuuid(
            @Param("puuid")
            puuid: String,
            @QueryMap
            queryMap: Map<String, Any>,
        ): List<String>

//        GET /lol/match/v5/matches/{matchId}Get a match by match id
        @RequestLine("GET /lol/match/v5/matches/{matchId}")
        fun getMatchById(
            @Param("matchId")
            matchId: String,
        ): MatchDto

//        GET /lol/match/v5/matches/{matchId}/timelineGet a match timeline by match id
        @RequestLine("GET /lol/match/v5/matches/{matchId}/timeline")
        fun getMatchTimelineById(
            @Param("matchId")
            matchId: String,
        ): TimelineDto
    }
}

internal fun buildMatchQueries(
    startTime: Date?,
    endTime: Date?,
    queueId: Int?,
    type: String?,
    startIndex: Int,
    count: Int,
): MutableMap<String, Any> {
    val queries = mutableMapOf<String, Any>()
    startTime?.let { queries["startTime"] = it.time }
    endTime?.let { queries["endTime"] = it.time }
    queueId?.let { queries["queue"] = it }
    type?.let { queries["type"] = it }
    queries["startIndex"] = startIndex
    queries["count"] = count
    return queries
}
