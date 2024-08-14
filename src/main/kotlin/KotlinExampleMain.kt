import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Region


public fun main() {
    val apiKey by lazy {
        System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
    }
    val riotApi = RiotApi(
        apiKey,
        Region.EUW1
    )
    val champ = riotApi.lol.champion
    println(champ.getChampionRotations())

    val status = riotApi.lol.status
    println(status.getPlatformData())

    val champMastery = riotApi.lol.championMastery
    println(champMastery.getAllMasteriesByPuuid("_KW9cHtSCSSCUVNZ3-__w2UsiIvQbpx1KKP0hC5U8xq4iBf0WL8f6Y8unxngvtKq-Ho_7l3E89D5TQ"))
    //Puuid of DNS Floppa#007
}


