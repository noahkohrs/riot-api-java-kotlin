package org.example

import com.noahkohrs.riot.api.sdk.RiotApi
import values.GlobalRegion
import values.Region

fun main() {
    val riotApi = RiotApi(
        "RGAPI-3dba4106-7b2b-4485-870a-065481d5e310",
        Region.EUW1,
        GlobalRegion.EUROPE
    )
    val champ = riotApi.champion
    println(champ.getChampionRotations())
}

