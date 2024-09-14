package com.noahkohrs.riot.api.lol.spectator

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SpectatorApiTest {
    private val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    private val playerPuuid = riotApi.account.getAccountByRiotId(TestInternal.somePlayers[2].name, TestInternal.somePlayers[2].tag).puuid

    @Test
    fun getCurrentGameInfoByPuuidStressTest() {
        try {
            val currentGameInfo = riotApi.lol.spectator.getCurrentGameInfoByPuuid(playerPuuid)
            assertNotNull(currentGameInfo)
        } catch (e : feign.FeignException.NotFound) {
            // This is fine, the player is not in a game
            println("Player is not in a game")
            return
        }

    }



    @Test
    fun getFeaturedGamesStressTest() {
        val featuredGames = riotApi.lol.spectator.getFeaturedGames()
        assertNotNull(featuredGames)
    }
}
