package com.noahkohrs.riot.api.lol.summoner

import com.noahkohrs.riot.api.dtos.SummonerDto

public data class SummonerResponse(private val dto: SummonerDto) {
    public val id: String = dto.id
    public val accountId: String = dto.accountId
    public val puuid: String = dto.puuid
    public val profileIconId: Int = dto.profileIconId
    public val revisionDate: Long = dto.revisionDate
    public val summonerLevel: Long = dto.summonerLevel
}