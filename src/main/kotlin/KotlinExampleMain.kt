import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Region

private const val DNS_FLOPPA_PUUID = "OPl5MMfUFkGFwdVrXaFz22kgUmANTcQcFlEDDOPvo3Hd9M00PLeY-4gPjzq8XSP6bb1mzT-iWC3ZzQ"

public fun main() {
    val apiKey by lazy {
        System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
    }
    val riotApi =
        RiotApi(
            apiKey,
            Region.EUW1,
        )
    val champ = riotApi.lol.champion
    println(champ.getChampionRotations())

    val status = riotApi.lol.status
    println(status.getPlatformData())

    val champMastery = riotApi.lol.championMastery
    // Puuid of DNS Floppa#007
    println(champMastery.getChampMasteriesByPuuid(DNS_FLOPPA_PUUID, 69))
    println(champMastery.getAllMasteriesByPuuid(DNS_FLOPPA_PUUID)[0])
    println(champMastery.getTopMasteriesByPuuid(DNS_FLOPPA_PUUID, 3))
    println(champMastery.getMasteryScoreByPuuid(DNS_FLOPPA_PUUID))
}
