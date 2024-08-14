package apis.lol

import apis.lol.champion.ChampionApi
import apis.lol.championmastery.ChampionMasteryApi
import apis.lol.status.StatusApi
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
