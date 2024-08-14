package apis.lol.championmastery

import com.noahkohrs.riot.api.RegionApiClientFactory
import feign.RequestLine
import com.noahkohrs.riot.api.values.Region
import feign.Param


public class ChampionMasteryApi(
    apiKey: String,
    region: Region,
) {
    private val apiClient = RegionApiClientFactory
        .create(apiKey, region)
        .createApiClient(ChampionMasteryApiClient::class.java)

    // TODO: Add the functions linked to the endpoints as wanted
    //  and with possible parameters and post treatments if needed
    public fun getAllMasteriesByPuuid(puuid : String) : List<ChampionMasteryDto> = apiClient.getAllMasteriesByPuuid(puuid)

    private interface ChampionMasteryApiClient {
        // TODO: Add the endpoints: https://developer.riotgames.com/apis
        @RequestLine("GET /lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}")
        fun getAllMasteriesByPuuid(@Param("encryptedPUUID") puuid : String) : List<ChampionMasteryDto>



    }
}

//TODO: Generate a test for this class by right-clicked on <ClassName> -> Generate -> Test... -> OK
// TODO: Add the test in the test folder.