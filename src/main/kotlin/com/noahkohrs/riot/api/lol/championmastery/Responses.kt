package apis.lol.championmastery

import com.fasterxml.jackson.annotation.JsonProperty

public data class RewardConfigDto(
    @JsonProperty("rewardValue")
    val rewardValue: String,
    @JsonProperty("rewardType")
    val rewardType: String,
    @JsonProperty("maximalReward")
    val maximalReward: Int
)

public data class NextSeasonMilestonesDto(
    @JsonProperty("requireGradeCounts")
    val requireGradeCounts: Any,
    @JsonProperty("rewardMarks")
    val rewardMarks: Int,
    @JsonProperty("bonus")
    val bonus: Boolean,
    @JsonProperty("rewardConfig")
    val rewardConfig: RewardConfigDto?
    )

public data class ChampionMasteryDto(
    @JsonProperty("puuid") val puuid: String,
    @JsonProperty("championPointsUntilNextLevel") val championPointsUntilNextLevel: Long,
    @JsonProperty("chestGranted") val chestGranted: Boolean,
    @JsonProperty("championId") val championId: Long,
    @JsonProperty("lastPlayTime") val lastPlayTime: Long,
    @JsonProperty("championLevel") val championLevel: Int,
    @JsonProperty("championPoints") val championPoints: Int,
    @JsonProperty("championPointsSinceLastLevel") val championPointsSinceLastLevel: Long,
    @JsonProperty("markRequiredForNextLevel") val markRequiredForNextLevel: Int,
    @JsonProperty("championSeasonMilestone") val championSeasonMilestone: Int,
    @JsonProperty("nextSeasonMilestone") val nextSeasonMilestone: NextSeasonMilestonesDto?,
    @JsonProperty("tokensEarned") val tokensEarned: Int,
    @JsonProperty("milestoneGrades") val milestoneGrades: List<String>?
)




