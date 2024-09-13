package com.noahkohrs.riot.api.lol.spectator

import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SpectatorApiTest {
    private val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    private val playerPuuid = riotApi.account.getAccountByRiotId(TestInternal.somePlayers[2].name, TestInternal.somePlayers[2].tag).puuid

    @Test
    fun getCurrentGameInfoByPuuidStressTest() {
        val currentGameInfo = riotApi.lol.spectator.getCurrentGameInfoByPuuid(playerPuuid)
        assertNotNull(currentGameInfo)
    }

    @Test
    fun getFeaturedGamesStressTest() {
        val featuredGames = riotApi.lol.spectator.getFeaturedGames()
        assertNotNull(featuredGames)
    }
}
