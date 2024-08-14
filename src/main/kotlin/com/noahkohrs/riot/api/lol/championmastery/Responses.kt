package apis.lol.championmastery

import com.squareup.moshi.Json
import org.jetbrains.annotations.NotNull

public data class RewardConfigDto(
    @Json(name = "rewardValue")
    val rewardValue: String,
    @Json(name = "rewardType")
    val rewardType: String,
    @Json(name = "maximalReward")
    val maximalReward: Int=0
)

public data class NextSeasonMilestonesDto(
    @Json(name = "requireGradeCounts")
    val requireGradeCounts: Any,
    @Json(name = "rewardMarks")
    val rewardMarks: Int,
    @Json(name = "bonus")
    val bonus: Boolean,
    @Json(name = "rewardConfig")
    val rewardConfig: RewardConfigDto? = null
    )

public data class ChampionMasteryDto(
    @Json(name = "puuid") val puuid: String,
    @Json(name = "championPointsUntilNextLevel") val championPointsUntilNextLevel: Long,
    @Json(name = "chestGranted") val chestGranted: Boolean = false,
    @Json(name = "championId") val championId: Long,
    @Json(name = "lastPlayTime") val lastPlayTime: Long,
    @Json(name = "championLevel") val championLevel: Int,
    @Json(name = "championPoints") val championPoints: Int,
    @Json(name = "championPointsSinceLastLevel") val championPointsSinceLastLevel: Long,
    @Json(name = "markRequiredForNextLevel") val markRequiredForNextLevel: Int,
    @Json(name = "championSeasonMilestone") val championSeasonMilestone: Int,
    @Json(name = "nextSeasonMilestone") val nextSeasonMilestone: NextSeasonMilestonesDto,
    @Json(name = "tokensEarned") val tokensEarned: Int,
    @Json(name = "milestoneGrades") val milestoneGrades: List<String> = emptyList()
)




