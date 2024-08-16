package org.example

import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform

private data class Player(val name: String, val tag: String)

private val DNS_FLOPPA = Player("DNS Floppa", "007")
private val DNS_YOTSUBA = Player("DNS Yotsuba", "EUW")

fun main() {
    val apiKey by lazy {
        System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
    }
    val riotApi =
        RiotApi(
            apiKey,
            Platform.EUW1,
        )
    println(riotApi.lol.champion.getChampionRotations())
    val dnsFloppaPuuid = riotApi.account.getAccountByRiotId(DNS_FLOPPA.name, DNS_FLOPPA.tag).puuid
    val dnsYotsubaPuuid = riotApi.account.getAccountByRiotId(DNS_YOTSUBA.name, DNS_YOTSUBA.tag).puuid

    println(riotApi.lol.status.getPlatformData())

    val champMastery = riotApi.lol.championMastery
    // Puuid of DNS Floppa#007
    println(champMastery.getChampMasteriesByPuuid(dnsFloppaPuuid, 69))
    println(champMastery.getAllMasteriesByPuuid(dnsFloppaPuuid)[0])
    println(champMastery.getTopMasteriesByPuuid(dnsFloppaPuuid, 3))
    println(champMastery.getMasteryScoreByPuuid(dnsFloppaPuuid))

    val leagueExp = riotApi.lol.leagueExp
    println(leagueExp.getLeagueEntries("RANKED_SOLO_5x5", "DIAMOND", "I", 10))
}
