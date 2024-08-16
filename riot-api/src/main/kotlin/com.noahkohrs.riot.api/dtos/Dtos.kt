package com.noahkohrs.riot.api.dtos

import com.squareup.moshi.Json

// MIGHT NEED TO BE SEPERATED INTO MULTIPLE FILES LATER ON

/*
puuid	string
gameName	string	This field may be excluded from the response if the account doesn't have a gameName.
tagLine	string	This field may be excluded from the response if the account doesn't have a tagLine.
 */
public data class AccountDto(
    @Json(name = "puuid")
    public val puuid: String,
    @Json(name = "gameName")
    public val gameName: String? = null,
    @Json(name = "tagLine")
    public val tagLine: String? = null,
)

/*
{
    "puuid": "sVBrQFp5B9yuSRmhS81EnpcO_SAQWMEMpmpvZ8oKwA4YI3hzz4KyutGY5Et_t-Mum3WEAS0gVIWJTA",
    "game": "lor",
    "activeShard": "europe"
}
 */
public data class ActiveShardDto(
    @Json(name = "puuid")
    public val puuid: String,
    @Json(name = "game")
    public val game: String,
    @Json(name = "activeShard")
    public val activeShard: String,
)

public data class ChampionInfo(
    @Json(name = "freeChampionIds")
    val freeChampionIds: List<Int>,
    @Json(name = "freeChampionIdsForNewPlayers")
    val freeChampionIdsForNewPlayers: List<Int>,
    @Json(name = "maxNewPlayerLevel")
    val maxNewPlayerLevel: Int,
)

public data class RewardConfigDto(
    @Json(name = "rewardValue")
    val rewardValue: String,
    @Json(name = "rewardType")
    val rewardType: String,
    @Json(name = "maximalReward")
    val maximalReward: Int = 0,
)

public data class NextSeasonMilestonesDto(
    @Json(name = "requireGradeCounts")
    val requireGradeCounts: Any,
    @Json(name = "rewardMarks")
    val rewardMarks: Int,
    @Json(name = "bonus")
    val bonus: Boolean,
    @Json(name = "rewardConfig")
    val rewardConfig: RewardConfigDto? = null,
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
    @Json(name = "milestoneGrades") val milestoneGrades: List<String> = emptyList(),
)

// ContentDto
public data class ContentDto(
    @Json(name = "locale")
    val locale: String,
    @Json(name = "content")
    val content: String,
)

// UpdateDto
public data class UpdateDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "author")
    val author: String,
    @Json(name = "publish")
    val publish: Boolean,
    @Json(name = "publish_locations")
    val publishLocations: List<String>,
    @Json(name = "translations")
    val translations: List<ContentDto>,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
)

// StatusDto
public data class StatusDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "maintenance_status")
    val maintenanceStatus: String,
    @Json(name = "incident_severity")
    val incidentSeverity: String?,
    @Json(name = "titles")
    val titles: List<ContentDto>,
    @Json(name = "updates")
    val updates: List<UpdateDto>,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "archive_at")
    val archiveAt: String,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "platforms")
    val platforms: List<String>,
)

// PlatformDataDto
public data class PlatformDataDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "locales")
    val locales: List<String>,
    @Json(name = "maintenances")
    val maintenances: List<StatusDto>,
    @Json(name = "incidents")
    val incidents: List<StatusDto>,
)

/*
SummonerDTO - represents a summoner
Name	Data Type	Description
accountId	string	Encrypted account ID. Max length 56 characters.
profileIconId	int	ID of the summoner icon associated with the summoner.
revisionDate	long	Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
id	string	Encrypted summoner ID. Max length 63 characters.
puuid	string	Encrypted PUUID. Exact length of 78 characters.
summonerLevel	long	Summoner level associated with the summoner.
 */
public data class SummonerDto(
    @Json(name = "accountId")
    val accountId: String,
    @Json(name = "profileIconId")
    val profileIconId: Int,
    @Json(name = "revisionDate")
    val revisionDate: Long,
    @Json(name = "id")
    val id: String,
    @Json(name = "puuid")
    val puuid: String,
    @Json(name = "summonerLevel")
    val summonerLevel: Long,
)


/*
LeagueEntryDTO - represents a league entry
 */

public data class LeagueEntryDto(
    @Json(name = "leagueId")
    val leagueId: String,
    @Json(name = "summonerId")
    val summonerId: String,
    @Json(name = "queueType")
    val queueType: String,
    @Json(name = "tier")
    val tier: String,
    @Json(name = "rank")
    val rank: String,
    @Json(name = "leaguePoints")
    val leaguePoints: Int,
    @Json(name = "wins")
    val wins: Int,
    @Json(name = "losses")
    val losses: Int,
    @Json(name = "hotStreak")
    val hotStreak: Boolean,
    @Json(name = "veteran")
    val veteran: Boolean,
    @Json(name = "freshBlood")
    val freshBlood: Boolean,
    @Json(name = "inactive")
    val inactive: Boolean,
    @Json(name = "miniSeries")
    val miniSeries: MiniSeriesDto? = null
)

public data class MiniSeriesDto(
    @Json(name = "losses")
    val losses: Int,
    @Json(name = "progress")
    val progress: String,
    @Json(name = "target")
    val target: Int,
    @Json(name = "wins")
    val wins: Int
)
