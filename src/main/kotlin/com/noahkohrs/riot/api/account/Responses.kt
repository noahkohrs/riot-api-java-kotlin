package com.noahkohrs.riot.api.account
/*
puuid	string
gameName	string	This field may be excluded from the response if the account doesn't have a gameName.
tagLine	string	This field may be excluded from the response if the account doesn't have a tagLine.
 */
public data class AccountDto(
    public val puuid: String,
    public val gameName: String? = null,
    public val tagLine: String? = null
)

/*
{
    "puuid": "sVBrQFp5B9yuSRmhS81EnpcO_SAQWMEMpmpvZ8oKwA4YI3hzz4KyutGY5Et_t-Mum3WEAS0gVIWJTA",
    "game": "lor",
    "activeShard": "europe"
}
 */
public data class ActiveShardDto(
    public val puuid: String,
    public val game: String,
    public val activeShard: String
)
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