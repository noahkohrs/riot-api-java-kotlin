package com.noahkohrs.riot.api.lol.leagueexp

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLQueue
import com.noahkohrs.riot.api.values.LoLTier
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LeagueExpApiTest {
    val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    /**
     * Just test that it's not crashing.
     */
    @Test
    fun getLeagueEntries() {
        val leagueExp = riotApi.lol.leagueExp
        val p1 = leagueExp.getLeagueEntries(LoLQueue.RankedSoloQueue, LoLTier.GOLD, LoLDivision.I)
        val p10 = leagueExp.getLeagueEntries(LoLQueue.RankedSoloQueue, LoLTier.GOLD, LoLDivision.I, 10)
        assertTrue(p1.isNotEmpty())
        assertTrue(p10.isNotEmpty())
        assertNotEquals(p1, p10)
    }
}
