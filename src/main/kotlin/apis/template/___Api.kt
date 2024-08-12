package apis.template

import RegionApiClientFactory
import feign.RequestLine
import values.GlobalRegion
import values.Region

// TODO: Replace ___ with the name of the API
class ___Api(
    apiKey: String,
    // TODO: Remove either region or globalRegion depending on the https://developer.riotgames.com/apis
    region: Region,
    globalRegion: GlobalRegion
) {
    private val apiClient = RegionApiClientFactory
        .create(apiKey, region)
        .createApiClient(___ApiClient::class.java)

    // TODO: Add the functions linked to the endpoints as wanted
    //  and with possible parameters and post treatments if needed
    fun getSomeValue(): SomeResponse = apiClient.getSomeValue()

    private interface ___ApiClient {
        // TODO: Add the endpoints: https://developer.riotgames.com/apis
        @RequestLine("GET /path/to/the/endpoint")
        fun getSomeValue() : SomeResponse
    }
}

//TODO: Generate a test for this class by right-clicked on <ClassName> -> Generate -> Test... -> OK
// TODO: Add the test in the test folder.