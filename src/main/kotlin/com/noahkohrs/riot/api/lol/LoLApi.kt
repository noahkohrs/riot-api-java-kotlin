package apis.lol

import apis.lol.champion.ChampionApi
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Region

public class LoLApi(
    apiKey: String,
    region: Region,
    globalRegion: GlobalRegion
) {
    @JvmField
    public val champion: ChampionApi = ChampionApi(apiKey, region)
}