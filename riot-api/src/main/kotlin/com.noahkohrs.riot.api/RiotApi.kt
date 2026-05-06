package com.noahkohrs.riot.api

import com.noahkohrs.riot.api.account.AccountApi
import com.noahkohrs.riot.api.lol.LoLApi
import com.noahkohrs.riot.api.lor.LoRApi
import com.noahkohrs.riot.api.tft.TftApi
import com.noahkohrs.riot.api.valorant.ValorantApi
import com.noahkohrs.riot.api.values.GlobalRegion
import com.noahkohrs.riot.api.values.Platform

public class RiotApi(
    apiKey: String,
    platform: Platform,
    globalRegion: GlobalRegion,
) {
    public constructor(apiKey: String, platform: Platform) : this(apiKey, platform, GlobalRegion.fromRegion(platform))

    @JvmField
    public val lol: LoLApi =
        LoLApi(
            apiKey,
            platform,
        )

    private val lor: LoRApi =
        LoRApi(
            apiKey,
            platform,
            globalRegion,
        )

    private val tft: TftApi =
        TftApi(
            apiKey,
            platform,
            globalRegion,
        )

    private val valorant: ValorantApi =
        ValorantApi(
            apiKey,
            platform,
            globalRegion,
        )

    @JvmField
    public val account: AccountApi =
        AccountApi(
            apiKey,
            platform,
        )
}
