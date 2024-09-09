package com.noahkohrs.riot.api.lol.lolchallenges

import com.noahkohrs.riot.api.annotations.UnstableApi
import com.noahkohrs.riot.api.dtos.*
import com.noahkohrs.riot.api.util.associateBySafe
import com.noahkohrs.riot.api.values.Languages
import com.noahkohrs.riot.api.values.LoLTier
import java.util.*

public class Challenge(
    @JvmField public val description: String,
    @JvmField public val name: String,
    @JvmField public val shortDescription: String,
) {
    internal companion object {
        fun fromDto(challengeDto: ChallengeDto): Challenge {
            return Challenge(
                description = challengeDto.description,
                name = challengeDto.name,
                shortDescription = challengeDto.shortDescription,
            )
        }
    }
}

@UnstableApi
public class ChallengeConfigInfo(
    @JvmField public val id: Long,
    @JvmField public val localizedNames: Map<Languages, Challenge>,
    @JvmField public val leaderboard: Boolean,
    @JvmField public val thresholds: Map<LoLTier, Int>,
) {
    internal companion object {
        fun fromDto(challengeConfigInfo: ChallengeConfigInfoDto): ChallengeConfigInfo {
            return ChallengeConfigInfo(
                id = challengeConfigInfo.id,
                localizedNames =
                    challengeConfigInfo.localizedNames.map {
                            (k, v) ->
                        Languages.fromValue(k) to Challenge.fromDto(v)
                    }.toMap(),
                leaderboard = challengeConfigInfo.leaderboard,
                thresholds = challengeConfigInfo.thresholds.map { (k, v) -> LoLTier.fromValue(k) to v }.toMap(),
            )
        }
    }
}

public class ChallengeInfo(
    @JvmField public val challengeId: Long,
    @JvmField public val percentile: Double,
    @JvmField public val level: LoLTier,
    @JvmField public val value: Long,
    @JvmField public val achievedTime: Date? = null,
) {
    internal companion object {
        fun fromDto(challengeInfoDto: ChallengeInfoDto): ChallengeInfo {
            return ChallengeInfo(
                challengeId = challengeInfoDto.challengeId,
                percentile = challengeInfoDto.percentile,
                level = LoLTier.fromValue(challengeInfoDto.level),
                value = challengeInfoDto.value,
                achievedTime = challengeInfoDto.achievedTime?.let { Date(it) },
            )
        }
    }
}

public class ChallengePoints(
    @JvmField public val rank: LoLTier,
    @JvmField public val points: Long,
    @JvmField public val maximumPoints: Long,
    @JvmField public val percentile: Double,
) {
    internal companion object {
        fun fromDto(challengePoints: ChallengePointsDto): ChallengePoints {
            return ChallengePoints(
                rank = LoLTier.fromValue(challengePoints.level),
                points = challengePoints.current,
                maximumPoints = challengePoints.max,
                percentile = challengePoints.percentile,
            )
        }
    }
}

public data class ApexPlayerInfo(
    @JvmField public val puuid: String,
    @JvmField public val value: Double,
    @JvmField public val position: Int,
) {
    internal companion object {
        fun fromDto(apexPlayerInfoDto: ApexPlayerInfoDto): ApexPlayerInfo {
            return ApexPlayerInfo(
                puuid = apexPlayerInfoDto.puuid,
                value = apexPlayerInfoDto.value,
                position = apexPlayerInfoDto.position,
            )
        }
    }
}

public class PlayerClientReferences(
    @JvmField public val bannerAccent: String,
    @JvmField public val title: String,
    @JvmField public val challengeIds: List<Long>,
    @JvmField public val crestBorder: String,
    @JvmField public val prestigeCrestBorderLevel: Int,
) {
    internal companion object {
        fun fromDto(playerClientReferences: PlayerClientReferencesDto): PlayerClientReferences {
            return PlayerClientReferences(
                bannerAccent = playerClientReferences.bannerAccent,
                title = playerClientReferences.title,
                challengeIds = playerClientReferences.challengeIds,
                crestBorder = playerClientReferences.crestBorder,
                prestigeCrestBorderLevel = playerClientReferences.prestigeCrestBorderLevel,
            )
        }
    }
}

public class PlayerInfo(
    @JvmField public val challenges: Map<Long, ChallengeInfo>,
    @JvmField public val preferences: PlayerClientReferences,
    @JvmField public val totalPoints: ChallengePoints,
    @JvmField public val categories: Map<String, ChallengePoints>,
) {
    internal companion object {
        fun fromDto(playerInfoDto: PlayerInfoDto): PlayerInfo {
            return PlayerInfo(
                challenges = playerInfoDto.challenges.associateBySafe { it.challengeId to ChallengeInfo.fromDto(it) },
                preferences = PlayerClientReferences.fromDto(playerInfoDto.preferences),
                totalPoints = ChallengePoints.fromDto(playerInfoDto.totalPoints),
                categories = playerInfoDto.categoryPoints.map { it.key to ChallengePoints.fromDto(it.value) }.toMap(),
            )
        }
    }
}
