package com.noahkohrs.riot.api.account

import Player
import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Still not convinced by how we can test things.
 */
class AccountApiTest {
    private val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    @Test fun testGetPuuid() {
        TestInternal.somePlayers.forEach { it ->
            val puuid = riotApi.account.getAccountByRiotId(it.name, it.tag).puuid
            val playerFounded = riotApi.account.getAccountByPuuid(puuid).let { Player(it.gameName, it.tagLine) }
            assertEquals(it, playerFounded)
        }
    }
}
