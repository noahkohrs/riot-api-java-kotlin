package com.noahkohrs.riot.api.account

import com.noahkohrs.riot.api.AccountApiClientFactory
import com.noahkohrs.riot.api.dtos.AccountDto
import com.noahkohrs.riot.api.dtos.ActiveShardDto
import com.noahkohrs.riot.api.values.AccountRegion
import com.noahkohrs.riot.api.values.Platform
import feign.Param
import feign.RequestLine

/**
 * The Account API provides information about accounts.
 *
 * [Riot Account API](https://developer.riotgames.com/apis#account-v1)
 */
public class AccountApi internal constructor(
    apiKey: String,
    platform: Platform,
) {
    private val apiClient =
        AccountApiClientFactory
            .create(apiKey, AccountRegion.fromRegion(platform), debug = false)
            .createApiClient(AccountApiClient::class.java)

    /**
     * Get account by puuid
     */
    public fun getAccountByPuuid(puuid: String): Account = Account.fromDto(apiClient.getAccountByPuuid(puuid))

    /**
     * Get account by riot id
     */
    public fun getAccountByRiotId(
        gameName: String,
        tagLine: String,
    ): Account = Account.fromDto(apiClient.getAccountByRiotId(gameName, tagLine))

    /**
     * Get active shard for a player
     */
    public fun getActiveShardsByGameAndPuuid(
        game: String,
        puuid: String,
    ): ActiveShard = ActiveShard.fromDto(apiClient.getActiveShardsByGameAndPuuid(game, puuid))

    /**
     * Get my account
     *
     * Warning: This method is restricted.
     */
    public fun getMyAccount(): Account = Account.fromDto(apiClient.getMyAccount())

    private interface AccountApiClient {
        // GET /riot/account/v1/accounts/by-puuid/{puuid} Get account by puuid
        @RequestLine("GET /riot/account/v1/accounts/by-puuid/{puuid}")
        fun getAccountByPuuid(
            @Param("puuid") puuid: String,
        ): AccountDto

        // GET /riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine} Get account by riot id
        @RequestLine("GET /riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
        fun getAccountByRiotId(
            @Param("gameName") gameName: String,
            @Param("tagLine") tagLine: String,
        ): AccountDto

        // GET /riot/account/v1/active-shards/by-game/{game}/by-puuid/{puuid} Get active shard for a player
        @RequestLine("GET /riot/account/v1/active-shards/by-game/{game}/by-puuid/{puuid}")
        fun getActiveShardsByGameAndPuuid(
            @Param("game") game: String,
            @Param("puuid") puuid: String,
        ): ActiveShardDto

        // GET /riot/account/v1/accounts/me Get my account
        @RequestLine("GET /riot/account/v1/accounts/me")
        fun getMyAccount(): AccountDto
    }
}
