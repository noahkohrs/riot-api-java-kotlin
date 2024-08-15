package com.noahkohrs.riot.api.account

import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Region
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

data class Player(
    val name: String,
    val tag: String
)

val players = listOf(
    Player("DNS Floppa", "007"),
    Player("DNS Yotsuba", "EUW")
)

val apiKey by lazy {
    System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
}

/**
 * Still not convinced by how we can test things.
 */
class AccountApiTest {
    private val riotApi = RiotApi(apiKey, Region.EUW1)
    @Test fun testAccount() {

        val account = Account(AccountDto("puuid", "gameName", "tagLine"))
        assertEquals("puuid", account.puuid)
        assertEquals("gameName", account.gameName)
        assertEquals("tagLine", account.tagLine)
    }

    @Test fun testGetPuuid() {
        players.forEach {
                val puuid = riotApi.account.getAccountByRiotId(it.name, it.tag).puuid
                val playerFounded = riotApi.account.getAccountByPuuid(puuid).let { Player(it.gameName!!, it.tagLine!!) }
                assertEquals(it, playerFounded)
        }

    }

}
