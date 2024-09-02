package com.noahkohrs.riot.api.lol

import com.noahkohrs.riot.api.lol.champion.ChampionApi
import com.noahkohrs.riot.api.lol.championmastery.ChampionMasteryApi
import com.noahkohrs.riot.api.lol.league.LeagueApi
import com.noahkohrs.riot.api.lol.leagueexp.LeagueExpApi
import com.noahkohrs.riot.api.lol.match.MatchApi
import com.noahkohrs.riot.api.lol.status.StatusApi
import com.noahkohrs.riot.api.lol.summoner.SummonerApi
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Platform

public class LoLApi(
    private val apiKey: String,
    private val platform: Platform,
) {
    @JvmField
    public val champion: ChampionApi = ChampionApi(apiKey, platform)

    @JvmField
    public val status: StatusApi = StatusApi(apiKey, platform)

    @JvmField
    public val championMastery: ChampionMasteryApi = ChampionMasteryApi(apiKey, platform)

    @JvmField
    public val summoner: SummonerApi = SummonerApi(apiKey, platform)

    @JvmField
    public val leagueExp: LeagueExpApi = LeagueExpApi(apiKey, platform)

    @JvmField
    public val league: LeagueApi = LeagueApi(apiKey, platform)

    @JvmField
    public val match: MatchApi = MatchApi(apiKey, GlobalRegion.fromRegion(platform))
}
