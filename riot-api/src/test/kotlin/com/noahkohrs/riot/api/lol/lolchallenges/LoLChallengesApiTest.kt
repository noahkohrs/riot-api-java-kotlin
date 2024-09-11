package com.noahkohrs.riot.api.lol.lolchallenges

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LoLChallengesApiTest {
    val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    @Test
    fun getChallengeConfigStressTest() {
        riotApi.lol.challenges.getChallengeConfig()
    }

    @Test
    fun getChallengePercentilesStressTest() {
        riotApi.lol.challenges.getChallengePercentiles()
    }

    @Test
    fun getChallengeConfigByIdStressTest() {
        riotApi.lol.challenges.getChallengeConfigById(1)
    }

    @Test
    fun getChallengeLeaderboardsByLevelStressTest() {
        riotApi.lol.challenges.getChallengeLeaderboardsByLevel(1, LoLTier.MASTER)
    }

    @Test
    fun getChallengePercentilesByIdStressTest() {
        riotApi.lol.challenges.getChallengePercentilesById(1)
    }

    @Test
    fun getPlayerDataStressTest() {
        val playerPuuid = riotApi.account.getAccountByRiotId(TestInternal.dnsYotsuba.name, TestInternal.dnsYotsuba.tag).puuid
        riotApi.lol.challenges.getPlayerData(playerPuuid)
    }
}
