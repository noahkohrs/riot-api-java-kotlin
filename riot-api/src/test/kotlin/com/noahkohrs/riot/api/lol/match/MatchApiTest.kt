package com.noahkohrs.riot.api.lol.match

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class MatchApiTest {
    val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    /**
     * Helps to determine if it can fail in unexpected situations.
     */
    @TestFactory
    fun getMatchByIdStressTest(): List<DynamicTest> {
        val testMatchs = getTestMatchsPool()
        return testMatchs.map {
            dynamicTest(it) {
                val match = riotApi.lol.match.getMatchById(it)
                verifyMatchData(match)
            }
        }
    }

    @TestFactory
    fun getMatchTimelineByIdStressTest(): List<DynamicTest> {
        val testMatchs = getTestMatchsPool()
        return testMatchs.map { matchId ->
            dynamicTest("getMatchTimelineByIdStressTest $matchId") {
                val matchTimeline = riotApi.lol.match.getMatchTimelineById(matchId)
                verifyMatchTimelineData(matchTimeline)
            }
        }
    }

    // TODO: Polish
    private fun verifyMatchData(match: Match) {
        assertNotNull(match)
    }

    // TODO: Polish
    private fun verifyMatchTimelineData(matchTimeline: MatchTimeline) {
        assertNotNull(matchTimeline)
    }

    @Synchronized
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
