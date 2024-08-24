package com.noahkohrs.riot.api.lol.match

import com.noahkohrs.riot.api.GlobalRegionApiClientFactory
import com.noahkohrs.riot.api.LoLQueue
import com.noahkohrs.riot.api.values.GlobalRegion
import feign.Param
import feign.QueryMap
import feign.RequestLine
import java.util.Date

public class MatchApi(
    private val apiKey: String,
    private val globalRegion: GlobalRegion,
) {
    private val apiClient =
        GlobalRegionApiClientFactory
            .create(apiKey, globalRegion)
            .createApiClient(MatchApiClient::class.java)

    /**
     * Get a list of match ids by puuid
     */
    public fun getMatchIdsByPuuid(
        puuid: String,
        startTime: Date,
        endTime: Date,
        queue: LoLQueue,
        type: String,
        startIndex: Int = 0,
        count: Int = 20,
    ): List<String> {
        val queryMap =
            mapOf(
                "startTime" to startTime.time,
                "endTime" to endTime.time,
                "queue" to queue.queueId,
                "type" to type,
                "startIndex" to startIndex,
                "count" to count,
            )
        return apiClient.getMatchIdsByPuuid(puuid, queryMap)
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
//        GET /lol/match/v5/matches/{matchId}/timelineGet a match timeline by match id
    }
}
