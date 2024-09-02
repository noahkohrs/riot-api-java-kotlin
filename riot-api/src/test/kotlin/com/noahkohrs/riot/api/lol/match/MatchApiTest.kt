package com.noahkohrs.riot.api.lol.match

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import getTestExecutor
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import waitForCompletion

class MatchApiTest {
    val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    /**
     * Helps to determine if it can fail in unexpected situations.
     */
    @Test
    fun getMatchByIdStressTest() {
        val testMatchs = getTestMatchsPool()
        val threadPool = getTestExecutor()
        testMatchs.forEach { matchId ->
            threadPool.submit {
                val match = riotApi.lol.match.getMatchById(matchId)
                assertNotNull(match)
            }
        }
        threadPool.waitForCompletion()
    }

    @Test
    fun getMatchTimelineByIdStressTest() {
        val threadPool = getTestExecutor()
        val testMatchs = getTestMatchsPool()
        testMatchs.forEach { matchId ->
            threadPool.submit {
                val match = riotApi.lol.match.getMatchTimelineById(matchId)
                assertNotNull(match)
            }
        }
        threadPool.waitForCompletion()
    }

    private fun getTestMatchsPool(): Set<String> {
        val testMatchs = mutableSetOf<String>()
        for (p in TestInternal.somePlayers) {
            val puuid = riotApi.account.getAccountByRiotId(p.name, p.tag).puuid
            val matchIds = riotApi.lol.match.getMatchIdsByPuuid(puuid, count = 80)
            testMatchs.addAll(matchIds)
        }
        return testMatchs
    }
}
