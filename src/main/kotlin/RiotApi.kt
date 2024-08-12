import apis.lol.champion.ChampionApi
import apis.lol.match.MatchApi
import values.GlobalRegion
import values.Region

public class RiotApi(
    apiKey : String,
    region : Region,
    globalRegion: GlobalRegion
) {
    public val match = MatchApi(apiKey, globalRegion)
    public val champion = ChampionApi(apiKey, region)
}