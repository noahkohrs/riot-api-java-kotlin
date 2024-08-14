package com.noahkohrs.riot.api.lol

import com.noahkohrs.riot.api.lol.champion.ChampionApi
import com.noahkohrs.riot.api.lol.championmastery.ChampionMasteryApi
import com.noahkohrs.riot.api.lol.status.StatusApi
import com.noahkohrs.riot.api.values.Region

public class LoLApi(
    apiKey: String,
    region: Region,
) {
    @JvmField
    public val champion: ChampionApi = ChampionApi(apiKey, region)

    @JvmField
    public val status: StatusApi = StatusApi(apiKey, region)

    @JvmField
    public val championMastery: ChampionMasteryApi = ChampionMasteryApi(apiKey, region)
}
