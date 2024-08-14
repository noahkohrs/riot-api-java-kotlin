package com.noahkohrs.riot.api

import apis.lol.LoLApi
import apis.lor.LoRApi
import apis.tft.TftApi
import apis.valorant.ValorantApi
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Region

public class RiotApi(
    apiKey : String,
    region : Region,
    globalRegion: GlobalRegion
) {

    public constructor(apiKey: String, region: Region) : this(apiKey, region, GlobalRegion.fromRegion(region))

    @JvmField
    public val lol: LoLApi = LoLApi(
        apiKey,
        region
    )

    @JvmField
    public val lor: LoRApi = LoRApi(
        apiKey,
        region,
        globalRegion
    )
    //public val riot: Any = object {} // Might just be placed in this class since it's only sub-api

    @JvmField
    public val tft: TftApi = TftApi(
        apiKey,
        region,
        globalRegion
    )

    @JvmField
    public val valorant: ValorantApi = ValorantApi(
        apiKey,
        region,
        globalRegion
    )
}
