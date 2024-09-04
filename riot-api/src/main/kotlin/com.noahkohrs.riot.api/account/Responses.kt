package com.noahkohrs.riot.api.account

import com.noahkohrs.riot.api.dtos.*

/**
 * The Account represent the account of a player.
 *
 * > Note: Most endpoints utilizes the `puuid` of the account to identify the player. However, some endpoints might uses different identifiers such as `summonerId`.
 */
public class Account private constructor(
    /**
     * The puuid of the account.
     */
    @JvmField public val puuid: String,
    /**
     * The game name of the account. e.g. RandomPlayer in **RandomPlayer**#123.
     */
    @JvmField public val gameName: String,
    /**
     * The tag line of the account. e.g. 123 in RandomPlayer#**123**.
     */
    @JvmField public val tagLine: String,
) {
    internal companion object {
        fun fromDto(accountDto: AccountDto): Account {
            return Account(
                puuid = accountDto.puuid,
                gameName = accountDto.gameName,
                tagLine = accountDto.tagLine,
            )
        }
    }
}

public class ActiveShard private constructor(
    @JvmField public val puuid: String,
    @JvmField public val game: String,
    @JvmField public val activeShard: String,
) {
    internal companion object {
        fun fromDto(activeShardDto: ActiveShardDto): ActiveShard {
            return ActiveShard(
                puuid = activeShardDto.puuid,
                game = activeShardDto.game,
                activeShard = activeShardDto.activeShard,
            )
        }
    }
}
