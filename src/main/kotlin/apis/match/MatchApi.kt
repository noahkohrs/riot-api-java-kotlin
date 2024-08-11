package apis.match

import feign.Param
import feign.RequestLine
import values.GlobalRegion

class MatchApi(
    val apiKey : String,
    val region : GlobalRegion
)
{

    //https://{region}.api.riotgames.com/riot/account/v1/accounts/by-riot-id/{name}/{tag}"

}