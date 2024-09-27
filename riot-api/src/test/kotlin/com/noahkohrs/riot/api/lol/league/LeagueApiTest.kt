package com.noahkohrs.riot.api.lol.league

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLRankedQueue
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DynamicContainer
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

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

    @TestFactory
    fun getLeagueEntries(): List<DynamicContainer> {
        return LoLRankedQueue.entries.map { league ->
            DynamicContainer.dynamicContainer(
                "League: $league",
                LoLTier.lowerTiers.map { tier ->
                    DynamicContainer.dynamicContainer(
                        "Tier: $tier",
                        LoLDivision.entries.map { division ->
                            DynamicContainer.dynamicContainer(
                                "Division: $division",
                                (1..3).map { page ->
                                    dynamicTest("Page: $page") {
                                        riotApi.lol.league.getLeagueEntries(league, tier, division, page).forEach {
                                            verifyLeagueEntryData(it, league, tier, division)
                                        }
                                    }
                                },
                            )
                        },
                    )
                },
            )
        }
    }

    private fun verifyLeagueEntryData(
        leagueEntry: LeagueEntryResponse,
        league: LoLRankedQueue,
        tier: LoLTier,
        division: LoLDivision,
    ) {
        val summonerId = leagueEntry.summonerId ?: "Unknown Summoner"

        // Note: Most of theses checks are useless and should be replaced by a proper test
        assertNotNull(leagueEntry, "League entry should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.leagueId, "League ID should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.leaguePoints, "League points should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.wins, "Wins should not be null for summoner $summonerId")
        assertEquals(tier, leagueEntry.tier, "Expected tier $tier but got ${leagueEntry.tier} for summoner $summonerId")
        assertEquals(division, leagueEntry.division, "Expected division $division but got ${leagueEntry.division} for summoner $summonerId")
        assertNotNull(leagueEntry.losses, "Losses should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.division, "Division should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.freshBlood, "Fresh blood status should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.hotStreak, "Hot streak status should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.inactive, "Inactive status should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.queue, "Queue should not be null for summoner $summonerId")
        assertNotNull(leagueEntry.summonerId, "Summoner ID should not be null")
        assertNotNull(leagueEntry.veteran, "Veteran status should not be null for summoner $summonerId")
    }

    @TestFactory
    fun getLeague(): List<DynamicTest> {
        val entries = riotApi.lol.league.getLeagueEntries(LoLRankedQueue.RankedSoloQueue, LoLTier.EMERALD, LoLDivision.I)
        return entries.map { entry ->
            dynamicTest(entry.leagueId) {
                val league = entry.leagueId?.let { riotApi.lol.league.getLeague(it) }
                assertNotNull(league)
            }
        }
    }

    @Test
    fun getMasterLeague() {
        riotApi.lol.league.getMasterLeague(LoLRankedQueue.RankedSoloQueue)
    }
}
