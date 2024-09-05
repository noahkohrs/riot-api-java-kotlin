package com.noahkohrs.riot.api.templates

import com.noahkohrs.riot.api.RegionApiClientFactory
import com.noahkohrs.riot.api.dtos.PlayerDto
import com.noahkohrs.riot.api.lol.clash.Player
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine


public class ClashApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        RegionApiClientFactory
            .create(apiKey, platform)
            .createApiClient(ClashApiClient::class.java)

    // TODO: Add the functions linked to the endpoints as wanted
    //  and with possible parameters and post treatments if needed
    public fun getPlayersBySummoner(summonerId: String): List<Player> {
        val res = apiClient.getPlayersBySummoner(summonerId)
        return res.map { Player.fromDto(it) }
    }

    private interface ClashApiClient
    {
        // GET /lol/clash/v1/player/by-summoner/{summonerId}
        @RequestLine("GET /lol/clash/v1/player/by-summoner/{summonerId}")
        fun getPlayersBySummoner(
            @Param("summonerId") summonerId: String
        ): List<PlayerDto>




    }
}

// TODO: Generate a test for this class by right-clicked on <ClassName> -> Generate -> Test... -> OK
// TODO: Add the test in the test folder.
