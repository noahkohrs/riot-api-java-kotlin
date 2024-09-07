package com.noahkohrs.riot.api.lol.championmastery

import com.noahkohrs.riot.api.annotations.LinkToStaticApi
import com.noahkohrs.riot.api.dtos.ChampionMasteryDto
import com.noahkohrs.riot.api.dtos.NextSeasonMilestonesDto
import com.noahkohrs.riot.api.dtos.RewardConfigDto

public class RewardConfig private constructor(
    @JvmField public val rewardValue: String,
    @JvmField public val rewardType: String,
    @JvmField public val maximalReward: Int = 0,
) {
    internal companion object {
        fun fromDto(rewardConfigDto: RewardConfigDto): RewardConfig {
            return RewardConfig(
                rewardValue = rewardConfigDto.rewardValue,
                rewardType = rewardConfigDto.rewardType,
                maximalReward = rewardConfigDto.maximalReward,
            )
        }
    }
}

public class NextSeasonMilestones private constructor(
    @JvmField public val requireGradeCounts: Any,
    @JvmField public val rewardMarks: Int,
    @JvmField public val bonus: Boolean,
    @JvmField public val rewardConfig: RewardConfig? = null,
) {
    internal companion object {
        fun fromDto(nextSeasonMilestonesDto: NextSeasonMilestonesDto): NextSeasonMilestones {
            return NextSeasonMilestones(
                requireGradeCounts = nextSeasonMilestonesDto.requireGradeCounts,
                rewardMarks = nextSeasonMilestonesDto.rewardMarks,
                bonus = nextSeasonMilestonesDto.bonus,
                rewardConfig = nextSeasonMilestonesDto.rewardConfig?.let { RewardConfig.fromDto(it) },
            )
        }
    }
}

public class ChampionMastery private constructor(
    @JvmField public val puuid: String,
    @JvmField public val championPointsUntilNextLevel: Long,
    @JvmField public val chestGranted: Boolean = false,
    @LinkToStaticApi
    @JvmField public val championId: Long,
    @JvmField public val lastPlayTime: Long,
    @JvmField public val championLevel: Int,
    @JvmField public val championPoints: Int,
    @JvmField public val championPointsSinceLastLevel: Long,
    @JvmField public val markRequiredForNextLevel: Int,
    @JvmField public val championSeasonMilestone: Int,
    @JvmField public val nextSeasonMilestone: NextSeasonMilestones,
    @JvmField public val tokensEarned: Int,
    @JvmField public val milestoneGrades: List<String> = emptyList(),
) {
    internal companion object {
        fun fromDto(championMasteryDto: ChampionMasteryDto): ChampionMastery {
            return ChampionMastery(
                puuid = championMasteryDto.puuid,
                championPointsUntilNextLevel = championMasteryDto.championPointsUntilNextLevel,
                chestGranted = championMasteryDto.chestGranted,
                championId = championMasteryDto.championId,
                lastPlayTime = championMasteryDto.lastPlayTime,
                championLevel = championMasteryDto.championLevel,
                championPoints = championMasteryDto.championPoints,
                championPointsSinceLastLevel = championMasteryDto.championPointsSinceLastLevel,
                markRequiredForNextLevel = championMasteryDto.markRequiredForNextLevel,
                championSeasonMilestone = championMasteryDto.championSeasonMilestone,
                nextSeasonMilestone = NextSeasonMilestones.fromDto(championMasteryDto.nextSeasonMilestone),
                tokensEarned = championMasteryDto.tokensEarned,
                milestoneGrades = championMasteryDto.milestoneGrades,
            )
        }
    }
}
