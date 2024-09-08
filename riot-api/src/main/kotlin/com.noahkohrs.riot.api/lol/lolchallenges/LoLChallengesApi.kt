package com.noahkohrs.riot.api.lol.lolchallenges

import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.dtos.ApexPlayerInfoDto
import com.noahkohrs.riot.api.dtos.ChallengeConfigInfoDto
import com.noahkohrs.riot.api.dtos.PlayerInfoDto
import com.noahkohrs.riot.api.values.Platform
import feign.RequestLine

public class LoLChallengesApi(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        RegionApiClientFactory
            .create(apiKey, platform, debug = false)
            .createApiClient(LoLChallengesClient::class.java)

    private interface LoLChallengesClient {
        //        GET /lol/challenges/v1/challenges/configList of all basic challenge configuration information (includes all translations for names and descriptions)
        @RequestLine("GET /lol/challenges/v1/challenges/config")
        fun getChallengeConfig(): List<ChallengeConfigInfoDto>

        //        GET /lol/challenges/v1/challenges/percentilesMap of level to percentile of players who have achieved it - keys: ChallengeId -> Season -> Level -> percentile of players who achieved it
        @RequestLine("GET /lol/challenges/v1/challenges/percentiles")
        fun getChallengePercentiles(): Map<Long, Map<Int, Map<String, Int>>>

        //        GET /lol/challenges/v1/challenges/{challengeId}/configGet challenge configuration (REST)
        @RequestLine("GET /lol/challenges/v1/challenges/{challengeId}/config")
        fun getChallengeConfigById(challengeId: Long): ChallengeConfigInfoDto

        //        GET /lol/challenges/v1/challenges/{challengeId}/leaderboards/by-level/{level}Return top players for each level. Level must be MASTER, GRANDMASTER or CHALLENGER.
        @RequestLine("GET /lol/challenges/v1/challenges/{challengeId}/leaderboards/by-level/{level}")
        fun getChallengeLeaderboardsByLevel(challengeId: Long, level: String): List<ApexPlayerInfoDto>

        //        GET /lol/challenges/v1/challenges/{challengeId}/percentilesMap of level to percentile of players who have achieved it
        @RequestLine("GET /lol/challenges/v1/challenges/{challengeId}/percentiles")
        fun getChallengePercentilesById(challengeId: Long): Map<String, Double>

        //        GET /lol/challenges/v1/player-data/{puuid}Returns player information with list of all progressed challenges (REST)
        @RequestLine("GET /lol/challenges/v1/player-data/{puuid}")
        fun getPlayerData(puuid: String): PlayerInfoDto
    }
}
