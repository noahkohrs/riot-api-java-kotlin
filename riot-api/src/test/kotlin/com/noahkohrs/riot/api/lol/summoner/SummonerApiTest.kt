package com.noahkohrs.riot.api.lol.summoner

import TestInternal
import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Region
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SummonerApiTest {
    private val riotApi = RiotApi(TestInternal.apiKey, Region.EUW1)
    private val summonerApi = riotApi.lol.summoner

    @Test
    fun simpleInteraction() {
        val puuid = riotApi.account.getAccountByRiotId(TestInternal.somePlayers[0].name, TestInternal.somePlayers[0].tag).puuid
        val summoner = summonerApi.getSummonerByPuuid(puuid)
        val summoner2 = summonerApi.getSummonerBySummonerId(summoner.id)
        assertEquals(summoner.id, summoner2.id)
    }

    // TODO
    @Test
    fun getSummonerByRsoPuuid() {
    }

    @Test
    fun getSummonerByAccountId() {
    }

    @Test
    fun getSummonerByPuuid() {
    }

    @Test
    fun getSummonerByAccessToken() {
    }

    @Test
    fun getSummonerBySummonerId() {
    }
}
