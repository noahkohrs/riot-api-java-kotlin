package com.noahkohrs.riot.api.lol.summoner

import com.noahkohrs.riot.api.annotations.LinkToStaticApi
import com.noahkohrs.riot.api.dtos.SummonerDto
import java.util.Date

public class SummonerResponse private constructor(
    public val id: String,
    public val accountId: String,
    public val puuid: String,
    @LinkToStaticApi
    public val profileIconId: Int,
    public val revisionDate: Date,
    public val summonerLevel: Long,
) {
    internal companion object {
        fun fromDto(dto: SummonerDto): SummonerResponse {
            return SummonerResponse(
                id = dto.id,
                accountId = dto.accountId,
                puuid = dto.puuid,
                profileIconId = dto.profileIconId,
                revisionDate = Date(dto.revisionDate),
                summonerLevel = dto.summonerLevel,
            )
        }
    }
}
