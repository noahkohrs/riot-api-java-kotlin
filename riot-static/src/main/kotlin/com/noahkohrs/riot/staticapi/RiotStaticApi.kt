package com.noahkohrs.riot.staticapi

import com.noahkohrs.riot.staticapi.lol.LoLStaticApi

public class RiotStaticApi {
    /*
     * Valorant Static Api
     * - https://dash.valorant-api.com
     *
     * LoL Static Api
     * - https://static.developer.riotgames.com/docs/lol
     * - https://ddragon.leagueoflegends.com
     */
    @JvmField
    public val lol: LoLStaticApi = LoLStaticApi()
}
