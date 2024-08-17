package com.noahkohrs.riot.api.lol.league

import com.noahkohrs.riot.api.dtos.LeagueEntryDto
import com.noahkohrs.riot.api.dtos.LeagueItemDto
import com.noahkohrs.riot.api.dtos.LeagueListDto
import com.noahkohrs.riot.api.dtos.MiniSeriesDto
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLQueue
import com.noahkohrs.riot.api.values.LoLTier

public data class LeagueEntryResponse(
    val leagueId: String?,
    val summonerId: String,
    val queue: LoLQueue,
    val tier: LoLTier?,
    val division: LoLDivision?,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val veteran: Boolean,
    val inactive: Boolean,
    val freshBlood: Boolean,
    val hotStreak: Boolean,
) {
    internal companion object {
        fun fromDto(dto: LeagueEntryDto): LeagueEntryResponse {
            return LeagueEntryResponse(
                leagueId = dto.leagueId,
                summonerId = dto.summonerId,
                queue = LoLQueue.fromValue(dto.queueType),
                tier = dto.tier?.let { LoLTier.fromValue(it) },
                division = dto.rank?.let { LoLDivision.fromValue(it) },
                leaguePoints = dto.leaguePoints,
                wins = dto.wins,
                losses = dto.losses,
                veteran = dto.veteran,
                inactive = dto.inactive,
                freshBlood = dto.freshBlood,
                hotStreak = dto.hotStreak,
            )
        }
    }
}

public data class LeagueListResponse(
    val leagueId: String,
    val leaguePlayers: List<LeagueItemResponse>,
    val tier: LoLTier,
    val leagueName: String,
    val queue: LoLQueue,
) {
    internal companion object {
        fun fromDto(dto: LeagueListDto): LeagueListResponse {
            return LeagueListResponse(
                leagueId = dto.leagueId,
                leaguePlayers = dto.entries.map { LeagueItemResponse.fromDto(it) },
                tier = LoLTier.fromValue(dto.tier),
                leagueName = dto.name,
                queue = LoLQueue.fromValue(dto.queue),
            )
        }
    }
}

public data class LeagueItemResponse(
    val freshBlood: Boolean,
    val wins: Int,
    val miniSeries: MiniSeriesDto?,
    val inactive: Boolean,
    val veteran: Boolean,
    val hotStreak: Boolean,
    val rank: LoLDivision,
    val leaguePoints: Int,
    val losses: Int,
    val summonerId: String,
) {
    internal companion object {
        fun fromDto(dto: LeagueItemDto): LeagueItemResponse {
            return LeagueItemResponse(
                freshBlood = dto.freshBlood,
                wins = dto.wins,
                miniSeries = dto.miniSeries,
                inactive = dto.inactive,
                veteran = dto.veteran,
                hotStreak = dto.hotStreak,
                rank = LoLDivision.fromValue(dto.rank),
                leaguePoints = dto.leaguePoints,
                losses = dto.losses,
                summonerId = dto.summonerId,
            )
        }
    }
}
