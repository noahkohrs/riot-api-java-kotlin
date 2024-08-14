package apis.lol

import apis.lol.champion.ChampionApi
import apis.lol.status.StatusApi
import apis.lol.championmastery.ChampionMasteryApi
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Region

public class LoLApi(
    apiKey: String,
    region: Region,
    globalRegion: GlobalRegion
) {
    @JvmField
    public val champion: ChampionApi = ChampionApi(apiKey, region)

    @JvmField
    public val status: StatusApi = StatusApi(apiKey, region)

    @JvmField
    public val championMastery: ChampionMasteryApi = ChampionMasteryApi(apiKey, region)
}