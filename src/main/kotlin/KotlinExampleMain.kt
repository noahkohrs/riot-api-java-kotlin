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
    //Puuid of DNS Floppa#007
    //println(champMastery.getChampMasteriesByPuuid("OPl5MMfUFkGFwdVrXaFz22kgUmANTcQcFlEDDOPvo3Hd9M00PLeY-4gPjzq8XSP6bb1mzT-iWC3ZzQ", 69))
    //println(champMastery.getAllMasteriesByPuuid("OPl5MMfUFkGFwdVrXaFz22kgUmANTcQcFlEDDOPvo3Hd9M00PLeY-4gPjzq8XSP6bb1mzT-iWC3ZzQ"))
    //println(champMastery.getTopMasteriesByPuuid("OPl5MMfUFkGFwdVrXaFz22kgUmANTcQcFlEDDOPvo3Hd9M00PLeY-4gPjzq8XSP6bb1mzT-iWC3ZzQ", 3))
    println(champMastery.getMasteryScoreByPuuid("OPl5MMfUFkGFwdVrXaFz22kgUmANTcQcFlEDDOPvo3Hd9M00PLeY-4gPjzq8XSP6bb1mzT-iWC3ZzQ"))

}


