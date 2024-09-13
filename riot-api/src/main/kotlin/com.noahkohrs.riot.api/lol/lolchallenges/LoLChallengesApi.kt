package com.noahkohrs.riot.api.lol.lolchallenges

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.ApexPlayerInfoDto
import com.noahkohrs.riot.api.dtos.ChallengeConfigInfoDto
import com.noahkohrs.riot.api.dtos.PlayerInfoDto
import com.noahkohrs.riot.api.util.mapSafe
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

/**
 * The LoLChallengesApi provides information about challenges.
 *
 * [Riot LoL Challenges API](https://developer.riotgames.com/apis#lol-challenges-v1)
 */
public class LoLChallengesApi(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform, debug = false)
            .createApiClient(LoLChallengesClient::class.java)

    public fun getChallengeConfig(): List<ChallengeConfigInfo> =
        apiClient.getChallengeConfig()
            .map { ChallengeConfigInfo.fromDto(it) }

    public fun getChallengePercentiles(): Map<Long, Map<LoLTier, Double>> =
        apiClient.getChallengePercentiles()
            .map { (k, v) ->
                k to v.map { (k, v) -> LoLTier.fromValue(k) to v }.toMap()
            }.toMap()

    public fun getChallengeConfigById(challengeId: Long): ChallengeConfigInfo =
        apiClient.getChallengeConfigById(challengeId).let {
            ChallengeConfigInfo.fromDto(it)
        }

    public fun getChallengeLeaderboardsByLevel(challengeId: Long, level: LoLTier): List<ApexPlayerInfo> =
        apiClient.getChallengeLeaderboardsByLevel(
            challengeId,
            level.value,
        ).map {
            ApexPlayerInfo.fromDto(it)
        }

    public fun getChallengePercentilesById(challengeId: Long): Map<LoLTier, Double> =
        apiClient.getChallengePercentilesById(
            challengeId,
        ).mapSafe { (k, v) -> LoLTier.fromValue(k) to v }.toMap()

    public fun getPlayerData(puuid: String): PlayerInfo = apiClient.getPlayerData(puuid).let { PlayerInfo.fromDto(it) }

    private interface LoLChallengesClient {
        //        GET /lol/challenges/v1/challenges/configList of all basic challenge configuration information (includes all translations for names and descriptions)
        @RequestLine("GET /lol/challenges/v1/challenges/config")
        fun getChallengeConfig(): List<ChallengeConfigInfoDto>

        //        GET /lol/challenges/v1/challenges/percentilesMap of level to percentile of players who have achieved it - keys: ChallengeId -> Season -> Level -> percentile of players who achieved it
        @RequestLine("GET /lol/challenges/v1/challenges/percentiles")
        fun getChallengePercentiles(): Map<Long, Map<String, Double>>

        //        GET /lol/challenges/v1/challenges/{challengeId}/configGet challenge configuration (REST)
        @RequestLine("GET /lol/challenges/v1/challenges/{challengeId}/config")
        fun getChallengeConfigById(
            @Param("challengeId") challengeId: Long,
        ): ChallengeConfigInfoDto

        //        GET /lol/challenges/v1/challenges/{challengeId}/leaderboards/by-level/{level}Return top players for each level. Level must be MASTER, GRANDMASTER or CHALLENGER.
        @RequestLine("GET /lol/challenges/v1/challenges/{challengeId}/leaderboards/by-level/{level}")
        fun getChallengeLeaderboardsByLevel(
            @Param("challengeId") challengeId: Long,
            @Param("level") level: String,
        ): List<ApexPlayerInfoDto>

        //        GET /lol/challenges/v1/challenges/{challengeId}/percentilesMap of level to percentile of players who have achieved it
        @RequestLine("GET /lol/challenges/v1/challenges/{challengeId}/percentiles")
        fun getChallengePercentilesById(
            @Param("challengeId") challengeId: Long,
        ): Map<String, Double>

        //        GET /lol/challenges/v1/player-data/{puuid}Returns player information with list of all progressed challenges (REST)
        @RequestLine("GET /lol/challenges/v1/player-data/{puuid}")
        fun getPlayerData(
            @Param("puuid") puuid: String,
        ): PlayerInfoDto
    }
}
