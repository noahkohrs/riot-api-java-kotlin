package com.noahkohrs.riot.api.lol.league

import com.noahkohrs.riot.api.dtos.LeagueEntryDto
import com.noahkohrs.riot.api.dtos.LeagueItemDto
import com.noahkohrs.riot.api.dtos.LeagueListDto
import com.noahkohrs.riot.api.dtos.MiniSeriesDto
import com.noahkohrs.riot.api.values.LoLDivision
import com.noahkohrs.riot.api.values.LoLQueue
import com.noahkohrs.riot.api.values.LoLTier

public class LeagueEntryResponse private constructor(
    @JvmField public val leagueId: String?,
    @JvmField public val summonerId: String,
    @JvmField public val queue: LoLQueue,
    @JvmField public val tier: LoLTier?,
    @JvmField public val division: LoLDivision?,
    @JvmField public val leaguePoints: Int,
    @JvmField public val wins: Int,
    @JvmField public val losses: Int,
    @JvmField public val veteran: Boolean,
    @JvmField public val inactive: Boolean,
    @JvmField public val freshBlood: Boolean,
    @JvmField public val hotStreak: Boolean,
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

public class LeagueListResponse private constructor(
    @JvmField public val leagueId: String?,
    @JvmField public val leaguePlayers: List<LeagueItemResponse>,
    @JvmField public val tier: LoLTier,
    @JvmField public val leagueName: String?,
    @JvmField public val queue: LoLQueue?,
) {
    internal companion object {
        fun fromDto(dto: LeagueListDto): LeagueListResponse {
            return LeagueListResponse(
                leagueId = dto.leagueId,
                leaguePlayers = dto.entries.map { LeagueItemResponse.fromDto(it) },
                tier = LoLTier.fromValue(dto.tier),
                leagueName = dto.name,
                queue = dto.queue?.let { LoLQueue.fromValue(it) },
            )
        }
    }
}

public class MiniSeriesResponse private constructor(
    @JvmField public val progress: String,
    @JvmField public val losses: Int,
    @JvmField public val target: Int,
    @JvmField public val wins: Int,
) {
    internal companion object {
        fun fromDto(dto: MiniSeriesDto): MiniSeriesResponse {
            return MiniSeriesResponse(
                progress = dto.progress,
                losses = dto.losses,
                target = dto.target,
                wins = dto.wins,
            )
        }
    }
}

public class LeagueItemResponse private constructor(
    @JvmField public val freshBlood: Boolean,
    @JvmField public val wins: Int,
    @JvmField public val miniSeries: MiniSeriesResponse?,
    @JvmField public val inactive: Boolean,
    @JvmField public val veteran: Boolean,
    @JvmField public val hotStreak: Boolean,
    @JvmField public val rank: LoLDivision,
    @JvmField public val leaguePoints: Int,
    @JvmField public val losses: Int,
    @JvmField public val summonerId: String,
) {
    internal companion object {
        fun fromDto(dto: LeagueItemDto): LeagueItemResponse {
            return LeagueItemResponse(
                freshBlood = dto.freshBlood,
                wins = dto.wins,
                miniSeries = dto.miniSeries?.let { MiniSeriesResponse.fromDto(it) },
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
