import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Region

private const val DnsFloppaPuuid = "OPl5MMfUFkGFwdVrXaFz22kgUmANTcQcFlEDDOPvo3Hd9M00PLeY-4gPjzq8XSP6bb1mzT-iWC3ZzQ"

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
    //Puuid of DNS Floppa#007
    println(champMastery.getChampMasteriesByPuuid(DnsFloppaPuuid, 69))
    println(champMastery.getAllMasteriesByPuuid(DnsFloppaPuuid)[0])
    println(champMastery.getTopMasteriesByPuuid(DnsFloppaPuuid, 3))
    println(champMastery.getMasteryScoreByPuuid(DnsFloppaPuuid))

}


