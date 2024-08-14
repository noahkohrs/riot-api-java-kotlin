package apis.lol

import apis.lol.champion.ChampionApi
import apis.lol.status.StatusApi
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Region

public class LoLApi(
    apiKey: String,
    region: Region,
    globalRegion: GlobalRegion
) {
    @JvmField
    public val champion: ChampionApi = ChampionApi(apiKey, region)
    public val status: StatusApi = StatusApi(apiKey, region)
}