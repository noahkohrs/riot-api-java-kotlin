package com.noahkohrs.riot.api.lol.spectator

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.CurrentGameInfoDto
import com.noahkohrs.riot.api.dtos.FeaturedGamesDto
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

/**
 * The Spectator API provides information about the current game and featured games.
 *
 * [Riot Spectator API](https://developer.riotgames.com/apis#spectator-v5)
 */

public class SpectatorApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(SpectatorApiClient::class.java)

    /**
     * Get the current game information for a summoner.
     */
    public fun getCurrentGameInfoByPuuid(Puuid: String): CurrentGameInfo {
        val res = apiClient.getCurrentGameInfoBySummoner(Puuid)
        return CurrentGameInfo.fromDto(res)
    }

    /**
     * Get the featured games.
     */
    public fun getFeaturedGames(): FeaturedGames {
        val res = apiClient.getFeaturedGames()
        return FeaturedGames.fromDto(res)
    }

    private interface SpectatorApiClient {
        // GET /lol/spectator/v5/active-games/by-summoner/{puuid}
        @RequestLine("GET /lol/spectator/v5/active-games/by-summoner/{puuid}")
        fun getCurrentGameInfoBySummoner(
            @Param("puuid") puuid: String,
        ): CurrentGameInfoDto

        // GET /lol/spectator/v5/featured-games
        @RequestLine("GET /lol/spectator/v5/featured-games")
        fun getFeaturedGames(): FeaturedGamesDto
    }
}
