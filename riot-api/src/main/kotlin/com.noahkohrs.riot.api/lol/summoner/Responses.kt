package com.noahkohrs.riot.api.lol.summoner

import com.noahkohrs.riot.api.annotations.LinkToStaticApi
import com.noahkohrs.riot.api.dtos.SummonerDto
import java.util.Date

public class SummonerResponse private constructor(
    /**
     * Encrypted account ID.
     */
    public val id: String,
    /**
     * Encrypted summoner ID.
     */
    public val accountId: String,
    /**
     * Encrypted PUUID.
     */
    public val puuid: String,
    /**
     * The id of the summoner icon associated with the summoner.
     */
    @LinkToStaticApi
    public val profileIconId: Int,
    /**
     * Date summoner was last modified specified as epoch milliseconds.
     */
    public val revisionDate: Date,
    /**
     * Summoner level associated with the summoner.
     */
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
