package apis.lol.status

import com.noahkohrs.riot.api.RegionApiClientFactory
import feign.RequestLine
import com.noahkohrs.riot.api.values.Region


public class StatusApi(
    apiKey: String,
    region: Region,
) {
    private val apiClient = RegionApiClientFactory
        .create(apiKey, region)
        .createApiClient(StatusApiClient::class.java)


    public fun getPlatformData(): PlatformResponse = apiClient.getPlatformData()

    private interface StatusApiClient {
        @RequestLine("GET /lol/status/v4/platform-data")
        fun getPlatformData() : PlatformResponse
    }
}

