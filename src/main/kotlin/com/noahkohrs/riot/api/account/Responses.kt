package com.noahkohrs.riot.api.account

import com.noahkohrs.riot.api.dtos.*

public data class Account(
    val accountDto: AccountDto
) {
    public val puuid: String = accountDto.puuid
    public val gameName: String? = accountDto.gameName
    public val tagLine: String? = accountDto.tagLine
}
// Or the same with private main constructor:


public data class ActiveShard(
    val activeShardDto: ActiveShardDto
) {
    public val puuid: String = activeShardDto.puuid
    public val game: String = activeShardDto.game
    public val activeShard: String = activeShardDto.activeShard
}