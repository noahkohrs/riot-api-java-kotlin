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
}

