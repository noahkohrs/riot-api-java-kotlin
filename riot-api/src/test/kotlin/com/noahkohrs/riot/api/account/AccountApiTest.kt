package com.noahkohrs.riot.api.account

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestFactory

/**
 * Still not convinced by how we can test things.
 */
class AccountApiTest {
    private val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    private fun verifyAccountData(account: Account) {
        assertNotNull(account)
        assertNotNull(account.puuid)
        assertNotNull(account.gameName)
        assertNotNull(account.tagLine)
    }

    @Tag("fast")
    @TestFactory
    fun getAccountByRiotIdStressTest(): List<DynamicTest> {
        val testPlayers = TestInternal.somePlayers
        return testPlayers.map { player ->
            dynamicTest("${player.name}#${player.tag}") {
                val account = riotApi.account.getAccountByRiotId(player.name, player.tag)
                verifyAccountData(account)
            }
        }
    }
}
