import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Region

private data class Player(val name: String, val tag: String)

private val DNS_FLOPPA = Player("DNS Floppa", "007")
private val DNS_YOTSUBA = Player("DNS Yotsuba", "EUW")

public fun main() {
    val apiKey by lazy {
        System.getenv("RIOT_API_KEY") ?: throw IllegalStateException("RIOT_API_KEY not set")
    }
    val riotApi =
        RiotApi(
            apiKey,
            Region.EUW1,
        )
    println(riotApi.lol.champion.getChampionRotations())
    val DnsFloppaPuuid = riotApi.account.getAccountByRiotId(DNS_FLOPPA.name, DNS_FLOPPA.tag).puuid
    val DnsYotsubaPuuid = riotApi.account.getAccountByRiotId(DNS_YOTSUBA.name, DNS_YOTSUBA.tag).puuid


    println(riotApi.lol.status.getPlatformData())

    val champMastery = riotApi.lol.championMastery
    // Puuid of DNS Floppa#007
    println(champMastery.getChampMasteriesByPuuid(DnsFloppaPuuid, 69))
    println(champMastery.getAllMasteriesByPuuid(DnsFloppaPuuid)[0])
    println(champMastery.getTopMasteriesByPuuid(DnsFloppaPuuid, 3))
    println(champMastery.getMasteryScoreByPuuid(DnsFloppaPuuid))
}
