package com.noahkohrs.riot.api.templates

import com.noahkohrs.riot.api.PlatformApiClientFactory
import com.noahkohrs.riot.api.dtos.PlayerDto
import com.noahkohrs.riot.api.dtos.TeamDtoClash
import com.noahkohrs.riot.api.dtos.TournamentDto
import com.noahkohrs.riot.api.lol.clash.Player
import com.noahkohrs.riot.api.lol.clash.Team
import com.noahkohrs.riot.api.lol.clash.Tournament
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

/**
 * The Clash API provides information about clash.
 *
 * [Riot Clash API](https://developer.riotgames.com/apis#clash-v1)
 */
public class ClashApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        PlatformApiClientFactory
            .create(apiKey, platform)
            .createApiClient(ClashApiClient::class.java)

    /**
     * Get a list of players by summoner id.
     */
    public fun getPlayersBySummoner(summonerId: String): List<Player> {
        val res = apiClient.getPlayersBySummoner(summonerId)
        return res.map { Player.fromDto(it) }
    }

    /**
     * Get a team by team id.
     */
    public fun getTeamById(teamId: String): Team {
        val res = apiClient.getTeamById(teamId)
        return Team.fromDto(res)
    }

    /**
     * Get a list of tournaments.
     */
    public fun getTournaments(): List<Tournament> {
        val res = apiClient.getTournaments()
        return res.map { Tournament.fromDto(it) }
    }

    /**
     * Get a tournament by team id.
     */
    public fun getTournamentByTeam(teamId: String): Tournament {
        val res = apiClient.getTournamentByTeam(teamId)
        return Tournament.fromDto(res)
    }

    /**
     * Get a tournament by tournament id.
     */
    public fun getTournamentById(tournamentId: Int): Tournament {
        val res = apiClient.getTournamentById(tournamentId)
        return Tournament.fromDto(res)
    }

    private interface ClashApiClient {
        // GET /lol/clash/v1/player/by-summoner/{summonerId}
        @RequestLine("GET /lol/clash/v1/players/by-summoner/{summonerId}")
        fun getPlayersBySummoner(
            @Param("summonerId") summonerId: String,
        ): List<PlayerDto>

        // GET /lol/clash/v1/team/{teamId}
        @RequestLine("GET /lol/clash/v1/teams/{teamId}")
        fun getTeamById(
            @Param("teamId") teamId: String,
        ): TeamDtoClash

        // GET /lol/clash/v1/tournaments
        @RequestLine("GET /lol/clash/v1/tournaments")
        fun getTournaments(): List<TournamentDto>

        // GET /lol/clash/v1/tournaments/by-team/{teamId}
        @RequestLine("GET /lol/clash/v1/tournaments/by-team/{teamId}")
        fun getTournamentByTeam(
            @Param("teamId") teamId: String,
        ): TournamentDto

        // GET /lol/clash/v1/tournaments/{tournamentId}
        @RequestLine("GET /lol/clash/v1/tournaments/{tournamentId}")
        fun getTournamentById(
            @Param("tournamentId") tournamentId: Int,
        ): TournamentDto
    }
}
