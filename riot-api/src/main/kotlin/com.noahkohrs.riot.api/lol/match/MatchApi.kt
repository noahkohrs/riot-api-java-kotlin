package com.noahkohrs.riot.api.lol.match

import com.noahkohrs.riot.api.GlobalRegionApiClientFactory
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
        startTime: Date? = null,
        endTime: Date? = null,
        queueId: Int? = null,
        type: String? = null,
        startIndex: Int = 0,
        count: Int = 20,
    ): List<String> {
        val queries = mutableMapOf<String, Any>()
        startTime?.let { queries["startTime"] = it.time }
        endTime?.let { queries["endTime"] = it.time }
        queueId?.let { queries["queue"] = it }
        type?.let { queries["type"] = it }
        queries["startIndex"] = startIndex
        queries["count"] = count
        return apiClient.getMatchIdsByPuuid(puuid, queries)
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
