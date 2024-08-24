package com.noahkohrs.riot.api.lol.match

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MatchApiTest {
    val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    /**
     * Helps to determine if it can fail in unexpected situations.
     */
    @Test
    fun getMatchByIdStressTest() {
        val testMatchs = mutableSetOf<String>()
        for (p in TestInternal.somePlayers) {
            val puuid = riotApi.account.getAccountByRiotId(p.name, p.tag).puuid
            val matchIds = riotApi.lol.match.getMatchIdsByPuuid(puuid, count = 80)
            testMatchs.addAll(matchIds)
        }
        for (matchId in testMatchs) {
            val match = riotApi.lol.match.getMatchById(matchId)
            assertNotNull(match)
        }
    }
}
