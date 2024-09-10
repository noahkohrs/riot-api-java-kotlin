package com.noahkohrs.riot.api.lol.clash

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ClashApiTest {
    private val riotApi = RiotApi(TestInternal.apiKey, Platform.EUW1)

    // TODO to test this we need to have a player in a clash team
    private val playerPuuid = riotApi.account.getAccountByRiotId(TestInternal.somePlayers[0].name, TestInternal.somePlayers[0].tag).puuid
    private val summonerId = riotApi.lol.summoner.getSummonerByPuuid(playerPuuid).id

    @Test
    fun getPlayersBySummonerStressTest() {
        riotApi.lol.clash.getPlayersBySummoner(summonerId).forEach { player ->
            assertNotNull(player)
        }
    }

    @Test
    fun getTeamByIdStressTest() {
        if (riotApi.lol.clash.getPlayersBySummoner(summonerId).isEmpty()) {
            return
        }
        val teamId = riotApi.lol.clash.getPlayersBySummoner(summonerId)[0].teamId
        val team = riotApi.lol.clash.getTeamById(teamId)
        assertNotNull(team)
    }

    @Test
    fun getTournamentsStressTest() {
        riotApi.lol.clash.getTournaments().forEach { tournament ->
            assertNotNull(tournament)
        }
    }

    @Test
    fun getTournamentByTeamStressTest() {
        if (riotApi.lol.clash.getPlayersBySummoner(summonerId).isEmpty()) {
            return
        }
        val teamId = riotApi.lol.clash.getPlayersBySummoner(summonerId)[0].teamId
        val tournament = riotApi.lol.clash.getTournamentByTeam(teamId)
        assertNotNull(tournament)
    }

    @Test
    fun getTournamentByIdStressTest() {
        if (riotApi.lol.clash.getTournaments().isEmpty()) {
            return
        }
        val tournamentId = riotApi.lol.clash.getTournaments()[0].id
        val tournament = riotApi.lol.clash.getTournamentById(tournamentId)
        assertNotNull(tournament)
    }
}
