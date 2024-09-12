package com.noahkohrs.riot.api.lol.league

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLRankedQueue
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Test

class LeagueApiTest {
    private val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    @Test
    fun getChallengerLeague() {
        riotApi.lol.league.getChallengerLeague(LoLRankedQueue.RankedSoloQueue)
        riotApi.lol.league.getChallengerLeague(LoLRankedQueue.RankedFlexQueue)
    }

    @Test
    fun getGrandmasterLeague() {
        riotApi.lol.league.getGrandmasterLeague(LoLRankedQueue.RankedSoloQueue)
        riotApi.lol.league.getGrandmasterLeague(LoLRankedQueue.RankedFlexQueue)
    }

    @Test
    fun getLeagueEntriesBySummoner() {
        val puuid = riotApi.account.getAccountByRiotId("DNS Floppa", "007").puuid
        val summonerId = riotApi.lol.summoner.getSummonerByPuuid(puuid).id
        riotApi.lol.league.getLeagueEntriesBySummoner(summonerId)
    }

    @Test
    fun getLeagueEntries() {
        riotApi.lol.league.getLeagueEntries(LoLRankedQueue.RankedSoloQueue, LoLTier.GOLD, LoLDivision.I)
    }

    @Test
    fun getLeague() {
        val challengeLeagueId = riotApi.lol.league.getChallengerLeague(LoLRankedQueue.RankedSoloQueue).leagueId
        riotApi.lol.league.getLeague(challengeLeagueId)
    }

    @Test
    fun getMasterLeague() {
        riotApi.lol.league.getMasterLeague(LoLRankedQueue.RankedSoloQueue)
    }
}
