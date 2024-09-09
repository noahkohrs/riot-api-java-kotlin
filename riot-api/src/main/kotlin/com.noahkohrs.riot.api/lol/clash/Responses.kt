package com.noahkohrs.riot.api.lol.clash

import com.noahkohrs.riot.api.dtos.*
import com.noahkohrs.riot.api.values.ClashRole

public class Player private constructor(
    @JvmField public val summonerId: String,
    @JvmField public val teamId: String,
    @JvmField public val position: Position,
    @JvmField public val role: ClashRole,
) {
    public enum class Position {
        TOP,
        JUNGLE,
        MIDDLE,
        BOTTOM,
        UTILITY,
        FILL,
        ;

        internal companion object {
            internal fun fromValue(str: String): Position {
                return when (str) {
                    "TOP" -> TOP
                    "JUNGLE" -> JUNGLE
                    "MIDDLE" -> MIDDLE
                    "BOTTOM" -> BOTTOM
                    "UTILITY" -> UTILITY
                    "FILL" -> FILL
                    else -> throw IllegalArgumentException("Unknown position: $str")
                }
            }
        }
    }

    internal companion object {
        internal fun fromDto(dto: PlayerDto): Player {
            return Player(
                summonerId = dto.summonerId,
                teamId = dto.teamId,
                position = Position.fromValue(dto.position),
                role = ClashRole.fromValue(dto.role),
            )
        }
    }
}

public class Team private constructor(
    @JvmField public val id: String,
    @JvmField public val tournamentId: Int,
    @JvmField public val name: String,
    @JvmField public val iconId: Int,
    @JvmField public val tier: Int,
    @JvmField public val captain: String,
    @JvmField public val abbreviation: String,
    @JvmField public val players: List<Player>,
) {
    internal companion object {
        internal fun fromDto(dto: TeamDtoClash): Team {
            return Team(
                id = dto.id,
                tournamentId = dto.tournamentId,
                name = dto.name,
                iconId = dto.iconId,
                tier = dto.tier,
                captain = dto.captain,
                abbreviation = dto.abbreviation,
                players = dto.players.map { Player.fromDto(it) },
            )
        }
    }
}

public class TournamentPhase private constructor(
    @JvmField public val id: Int,
    @JvmField public val registrationTime: Long,
    @JvmField public val startTime: Long,
    @JvmField public val cancelled: Boolean,
) {
    internal companion object {
        internal fun fromDto(dto: TournamentPhaseDto): TournamentPhase {
            return TournamentPhase(
                id = dto.id,
                registrationTime = dto.registrationTime,
                startTime = dto.startTime,
                cancelled = dto.cancelled,
            )
        }
    }
}

public class Tournament private constructor(
    @JvmField public val id: Int,
    @JvmField public val themeId: Int,
    @JvmField public val nameKey: String,
    @JvmField public val nameKeySecondary: String,
    @JvmField public val schedule: List<TournamentPhase>,
) {
    internal companion object {
        internal fun fromDto(dto: TournamentDto): Tournament {
            return Tournament(
                id = dto.id,
                themeId = dto.themeId,
                nameKey = dto.nameKey,
                nameKeySecondary = dto.nameKeySecondary,
                schedule = dto.schedule.map { TournamentPhase.fromDto(it) },
            )
        }
    }
}
