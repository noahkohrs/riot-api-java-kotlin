package com.noahkohrs.riot.api.dtos

import com.noahkohrs.riot.api.manipulation.UnpredictableDto
import com.squareup.moshi.Json

// MIGHT NEED TO BE SEPERATED INTO MULTIPLE FILES LATER ON

/*
puuid	string
gameName	string	This field may be excluded from the response if the account doesn't have a gameName.
tagLine	string	This field may be excluded from the response if the account doesn't have a tagLine.
 */
internal data class AccountDto(
    @Json(name = "puuid")
    val puuid: String,
    @Json(name = "gameName")
    val gameName: String,
    @Json(name = "tagLine")
    val tagLine: String,
)

/*
{
    "puuid": "sVBrQFp5B9yuSRmhS81EnpcO_SAQWMEMpmpvZ8oKwA4YI3hzz4KyutGY5Et_t-Mum3WEAS0gVIWJTA",
    "game": "lor",
    "activeShard": "europe"
}
 */
internal data class ActiveShardDto(
    @Json(name = "puuid")
    internal val puuid: String,
    @Json(name = "game")
    internal val game: String,
    @Json(name = "activeShard")
    internal val activeShard: String,
)

internal data class ChampionInfo(
    @Json(name = "freeChampionIds")
    val freeChampionIds: List<Int>,
    @Json(name = "freeChampionIdsForNewPlayers")
    val freeChampionIdsForNewPlayers: List<Int>,
    @Json(name = "maxNewPlayerLevel")
    val maxNewPlayerLevel: Int,
)

internal data class RewardConfigDto(
    @Json(name = "rewardValue")
    val rewardValue: String,
    @Json(name = "rewardType")
    val rewardType: String,
    @Json(name = "maximalReward")
    val maximalReward: Int = 0,
)

internal data class NextSeasonMilestonesDto(
    @Json(name = "requireGradeCounts")
    val requireGradeCounts: Any,
    @Json(name = "rewardMarks")
    val rewardMarks: Int,
    @Json(name = "bonus")
    val bonus: Boolean,
    @Json(name = "rewardConfig")
    val rewardConfig: RewardConfigDto? = null,
)

internal data class ChampionMasteryDto(
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
internal data class ContentDto(
    @Json(name = "locale")
    val locale: String,
    @Json(name = "content")
    val content: String,
)

// UpdateDto
internal data class UpdateDto(
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
internal data class StatusDto(
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
internal data class PlatformDataDto(
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
internal data class SummonerDto(
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

internal data class MiniSeriesDto(
    @Json(name = "losses")
    val losses: Int,
    @Json(name = "progress")
    val progress: String,
    @Json(name = "target")
    val target: Int,
    @Json(name = "wins")
    val wins: Int,
)

/*
leagueId	string
entries	List[LeagueItemDTO]
tier	string
name	string
queue	string
 */
internal data class LeagueListDto(
    @Json(name = "leagueId")
    val leagueId: String? = null,
    @Json(name = "entries")
    val entries: List<LeagueItemDto> = emptyList(),
    @Json(name = "tier")
    val tier: String,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "queue")
    val queue: String? = null,
)

/*
freshBlood	boolean
wins	int	Winning team on Summoners Rift.
miniSeries	MiniSeriesDTO
inactive	boolean
veteran	boolean
hotStreak	boolean
rank	string
leaguePoints	int
losses	int	Losing team on Summoners Rift.
summonerId	string	Player's encrypted summonerId.
 */
internal data class LeagueItemDto(
    @Json(name = "freshBlood")
    val freshBlood: Boolean,
    @Json(name = "wins")
    val wins: Int,
    @Json(name = "miniSeries")
    val miniSeries: MiniSeriesDto? = null,
    @Json(name = "inactive")
    val inactive: Boolean,
    @Json(name = "veteran")
    val veteran: Boolean,
    @Json(name = "hotStreak")
    val hotStreak: Boolean,
    @Json(name = "rank")
    val rank: String,
    @Json(name = "leaguePoints")
    val leaguePoints: Int,
    @Json(name = "losses")
    val losses: Int,
    @Json(name = "summonerId")
    val summonerId: String,
)

/*
LeagueEntryDTO - represents a league entry
 */

internal data class LeagueEntryDto(
    @Json(name = "leagueId")
    val leagueId: String? = null,
    @Json(name = "summonerId")
    val summonerId: String,
    @Json(name = "queueType")
    val queueType: String,
    @Json(name = "tier")
    val tier: String?,
    @Json(name = "rank")
    val rank: String?,
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
    val miniSeries: MiniSeriesDto? = null,
)

internal data class MetadataDto(
    @Json(name = "dataVersion")
    val dataVersion: String,
    @Json(name = "matchId")
    val matchId: String,
    @Json(name = "participants")
    val participantsPuuids: List<String>,
)

/**
 * This dataclass is temporary part of the public API as it's really complex and need many time to restructure in a better way.
 */
public data class ChallengesDto(
//    12AssistStreakCount	int
    @Json(name = "AssistStreakCount")
    val assistStreakCount: Int = 0,
//    baronBuffGoldAdvantageOverThreshold	int
    @Json(name = "baronBuffGoldAdvantageOverThreshold")
    val baronBuffGoldAdvantageOverThreshold: Int = 0,
//    controlWardTimeCoverageInRiverOrEnemyHalf	float
    @Json(name = "controlWardTimeCoverageInRiverOrEnemyHalf")
    val controlWardTimeCoverageInRiverOrEnemyHalf: Float = 0f,
//    earliestBaron	int
    // Wrong type on docs, refer to https://github.com/RiotGames/developer-relations/issues/982
    @Json(name = "earliestBaron")
    val earliestBaron: Float = 0f,
//    earliestDragonTakedown	int
    // Wrong type on docs, refer to https://github.com/RiotGames/developer-relations/issues/982
    @Json(name = "earliestDragonTakedown")
    val earliestDragonTakedown: Float = 0f,
//    earliestElderDragon	int
    // Wrong type on docs, refer to https://github.com/RiotGames/developer-relations/issues/982
    @Json(name = "earliestElderDragon")
    val earliestElderDragon: Float = 0f,
//    earlyLaningPhaseGoldExpAdvantage	int
    @Json(name = "earlyLaningPhaseGoldExpAdvantage")
    val earlyLaningPhaseGoldExpAdvantage: Int = 0,
//    fasterSupportQuestCompletion	int
    @Json(name = "fasterSupportQuestCompletion")
    val fasterSupportQuestCompletion: Int = 0,
//    fastestLegendary	int
    // Wrong type on docs, refer to https://github.com/RiotGames/developer-relations/issues/982
    @Json(name = "fastestLegendary")
    val fastestLegendary: Float = 0f,
//    hadAfkTeammate	int
    @Json(name = "hadAfkTeammate")
    val hadAfkTeammate: Int = 0,
//    highestChampionDamage	int
    @Json(name = "highestChampionDamage")
    val highestChampionDamage: Int = 0,
//    highestCrowdControlScore	int
    @Json(name = "highestCrowdControlScore")
    val highestCrowdControlScore: Int = 0,
//    highestWardKills	int
    @Json(name = "highestWardKills")
    val highestWardKills: Int = 0,
//    junglerKillsEarlyJungle	int
    @Json(name = "junglerKillsEarlyJungle")
    val junglerKillsEarlyJungle: Int = 0,
//    killsOnLanersEarlyJungleAsJungler	int
    @Json(name = "killsOnLanersEarlyJungleAsJungler")
    val killsOnLanersEarlyJungleAsJungler: Int = 0,
//    laningPhaseGoldExpAdvantage	int
    @Json(name = "laningPhaseGoldExpAdvantage")
    val laningPhaseGoldExpAdvantage: Int = 0,
//    legendaryCount	int
    @Json(name = "legendaryCount")
    val legendaryCount: Int = 0,
//    maxCsAdvantageOnLaneOpponent	float
    @Json(name = "maxCsAdvantageOnLaneOpponent")
    val maxCsAdvantageOnLaneOpponent: Float = 0f,
//    maxLevelLeadLaneOpponent	int
    @Json(name = "maxLevelLeadLaneOpponent")
    val maxLevelLeadLaneOpponent: Int = 0,
//    mostWardsDestroyedOneSweeper	int
    @Json(name = "mostWardsDestroyedOneSweeper")
    val mostWardsDestroyedOneSweeper: Int = 0,
//    mythicItemUsed	int
    @Json(name = "mythicItemUsed")
    val mythicItemUsed: Int = 0,
//    playedChampSelectPosition	int
    @Json(name = "playedChampSelectPosition")
    val playedChampSelectPosition: Int = 0,
//    soloTurretsLategame	int
    @Json(name = "soloTurretsLategame")
    val soloTurretsLategame: Int = 0,
//    takedownsFirst25Minutes	int
    @Json(name = "takedownsFirst25Minutes")
    val takedownsFirst25Minutes: Int = 0,
//    teleportTakedowns	int
    @Json(name = "teleportTakedowns")
    val teleportTakedowns: Int = 0,
//    thirdInhibitorDestroyedTime	int
    // Wrong type on docs, refer to https://github.com/RiotGames/developer-relations/issues/982
    @Json(name = "thirdInhibitorDestroyedTime")
    val thirdInhibitorDestroyedTime: Float = 0f,
//    threeWardsOneSweeperCount	int
    @Json(name = "threeWardsOneSweeperCount")
    val threeWardsOneSweeperCount: Int = 0,
//    visionScoreAdvantageLaneOpponent	float
    @Json(name = "visionScoreAdvantageLaneOpponent")
    val visionScoreAdvantageLaneOpponent: Float = 0f,
//    InfernalScalePickup	int
    @Json(name = "InfernalScalePickup")
    val infernalScalePickup: Int = 0,
//    fistBumpParticipation	int
    @Json(name = "fistBumpParticipation")
    val fistBumpParticipation: Int = 0,
//    voidMonsterKill	int
    @Json(name = "voidMonsterKill")
    val voidMonsterKill: Int = 0,
//    abilityUses	int
    @Json(name = "abilityUses")
    val abilityUses: Int = 0,
//    acesBefore15Minutes	int
    @Json(name = "acesBefore15Minutes")
    val acesBefore15Minutes: Int = 0,
//    alliedJungleMonsterKills	float
    @Json(name = "alliedJungleMonsterKills")
    val alliedJungleMonsterKills: Float = 0f,
//    baronTakedowns	int
    @Json(name = "baronTakedowns")
    val baronTakedowns: Int = 0,
//    blastConeOppositeOpponentCount	int
    @Json(name = "blastConeOppositeOpponentCount")
    val blastConeOppositeOpponentCount: Int = 0,
//    bountyGold	int
    @Json(name = "bountyGold")
    val bountyGold: Int = 0,
//    buffsStolen	int
    @Json(name = "buffsStolen")
    val buffsStolen: Int = 0,
//    completeSupportQuestInTime	int
    @Json(name = "completeSupportQuestInTime")
    val completeSupportQuestInTime: Int = 0,
//    controlWardsPlaced	int
    @Json(name = "controlWardsPlaced")
    val controlWardsPlaced: Int = 0,
//    damagePerMinute	float
    @Json(name = "damagePerMinute")
    val damagePerMinute: Float = 0f,
//    damageTakenOnTeamPercentage	float
    @Json(name = "damageTakenOnTeamPercentage")
    val damageTakenOnTeamPercentage: Float = 0f,
//    dancedWithRiftHerald	int
    @Json(name = "dancedWithRiftHerald")
    val dancedWithRiftHerald: Int = 0,
//    deathsByEnemyChamps	int
    @Json(name = "deathsByEnemyChamps")
    val deathsByEnemyChamps: Int = 0,
//    dodgeSkillShotsSmallWindow	int
    @Json(name = "dodgeSkillShotsSmallWindow")
    val dodgeSkillShotsSmallWindow: Int = 0,
//    doubleAces	int
    @Json(name = "doubleAces")
    val doubleAces: Int = 0,
//    dragonTakedowns	int
    @Json(name = "dragonTakedowns")
    val dragonTakedowns: Int = 0,
//    legendaryItemUsed	List[int]
    @Json(name = "legendaryItemUsed")
    val legendaryItemUsed: List<Int> = emptyList(),
//    effectiveHealAndShielding	float
    @Json(name = "effectiveHealAndShielding")
    val effectiveHealAndShielding: Float = 0f,
//    elderDragonKillsWithOpposingSoul	int
    @Json(name = "elderDragonKillsWithOpposingSoul")
    val elderDragonKillsWithOpposingSoul: Int = 0,
//    elderDragonMultikills	int
    @Json(name = "elderDragonMultikills")
    val elderDragonMultikills: Int = 0,
//    enemyChampionImmobilizations	int
    @Json(name = "enemyChampionImmobilizations")
    val enemyChampionImmobilizations: Int = 0,
//    enemyJungleMonsterKills	float
    @Json(name = "enemyJungleMonsterKills")
    val enemyJungleMonsterKills: Float = 0f,
//    epicMonsterKillsNearEnemyJungler	int
    @Json(name = "epicMonsterKillsNearEnemyJungler")
    val epicMonsterKillsNearEnemyJungler: Int = 0,
//    epicMonsterKillsWithin30SecondsOfSpawn	int
    @Json(name = "epicMonsterKillsWithin30SecondsOfSpawn")
    val epicMonsterKillsWithin30SecondsOfSpawn: Int = 0,
//    epicMonsterSteals	int
    @Json(name = "epicMonsterSteals")
    val epicMonsterSteals: Int = 0,
//    epicMonsterStolenWithoutSmite	int
    @Json(name = "epicMonsterStolenWithoutSmite")
    val epicMonsterStolenWithoutSmite: Int = 0,
//    firstTurretKilled	int
    @Json(name = "firstTurretKilled")
    val firstTurretKilled: Int = 0,
//    firstTurretKilledTime	float
    @Json(name = "firstTurretKilledTime")
    val firstTurretKilledTime: Float = 0f,
//    flawlessAces	int
    @Json(name = "flawlessAces")
    val flawlessAces: Int = 0,
//    fullTeamTakedown	int
    @Json(name = "fullTeamTakedown")
    val fullTeamTakedown: Int = 0,
//    gameLength	float
    @Json(name = "gameLength")
    val gameLength: Float = 0f,
//    getTakedownsInAllLanesEarlyJungleAsLaner	int
    @Json(name = "getTakedownsInAllLanesEarlyJungleAsLaner")
    val getTakedownsInAllLanesEarlyJungleAsLaner: Int = 0,
//    goldPerMinute	float
    @Json(name = "goldPerMinute")
    val goldPerMinute: Float = 0f,
//    hadOpenNexus	int
    @Json(name = "hadOpenNexus")
    val hadOpenNexus: Int = 0,
//    immobilizeAndKillWithAlly	int
    @Json(name = "immobilizeAndKillWithAlly")
    val immobilizeAndKillWithAlly: Int = 0,
//    initialBuffCount	int
    @Json(name = "initialBuffCount")
    val initialBuffCount: Int = 0,
//    initialCrabCount	int
    @Json(name = "initialCrabCount")
    val initialCrabCount: Int = 0,
//    jungleCsBefore10Minutes	float
    @Json(name = "jungleCsBefore10Minutes")
    val jungleCsBefore10Minutes: Float = 0f,
//    junglerTakedownsNearDamagedEpicMonster	int
    @Json(name = "junglerTakedownsNearDamagedEpicMonster")
    val junglerTakedownsNearDamagedEpicMonster: Int = 0,
//    kda	float
    @Json(name = "kda")
    val kda: Float = 0f,
//    killAfterHiddenWithAlly	int
    @Json(name = "killAfterHiddenWithAlly")
    val killAfterHiddenWithAlly: Int = 0,
//    killedChampTookFullTeamDamageSurvived	int
    @Json(name = "killedChampTookFullTeamDamageSurvived")
    val killedChampTookFullTeamDamageSurvived: Int = 0,
//    killingSprees	int
    @Json(name = "killingSprees")
    val killingSprees: Int = 0,
//    killParticipation	float
    @Json(name = "killParticipation")
    val killParticipation: Float = 0f,
//    killsNearEnemyTurret	int
    @Json(name = "killsNearEnemyTurret")
    val killsNearEnemyTurret: Int = 0,
//    killsOnOtherLanesEarlyJungleAsLaner	int
    @Json(name = "killsOnOtherLanesEarlyJungleAsLaner")
    val killsOnOtherLanesEarlyJungleAsLaner: Int = 0,
//    killsOnRecentlyHealedByAramPack	int
    @Json(name = "killsOnRecentlyHealedByAramPack")
    val killsOnRecentlyHealedByAramPack: Int = 0,
//    killsUnderOwnTurret	int
    @Json(name = "killsUnderOwnTurret")
    val killsUnderOwnTurret: Int = 0,
//    killsWithHelpFromEpicMonster	int
    @Json(name = "killsWithHelpFromEpicMonster")
    val killsWithHelpFromEpicMonster: Int = 0,
//    knockEnemyIntoTeamAndKill	int
    @Json(name = "knockEnemyIntoTeamAndKill")
    val knockEnemyIntoTeamAndKill: Int = 0,
//    kTurretsDestroyedBeforePlatesFall	int
    @Json(name = "kTurretsDestroyedBeforePlatesFall")
    val kTurretsDestroyedBeforePlatesFall: Int = 0,
//    landSkillShotsEarlyGame	int
    @Json(name = "landSkillShotsEarlyGame")
    val landSkillShotsEarlyGame: Int = 0,
//    laneMinionsFirst10Minutes	int
    @Json(name = "laneMinionsFirst10Minutes")
    val laneMinionsFirst10Minutes: Int = 0,
//    lostAnInhibitor	int
    @Json(name = "lostAnInhibitor")
    val lostAnInhibitor: Int = 0,
//    maxKillDeficit	int
    @Json(name = "maxKillDeficit")
    val maxKillDeficit: Int = 0,
//    mejaisFullStackInTime	int
    @Json(name = "mejaisFullStackInTime")
    val mejaisFullStackInTime: Int = 0,
//    moreEnemyJungleThanOpponent	float
    @Json(name = "moreEnemyJungleThanOpponent")
    val moreEnemyJungleThanOpponent: Float = 0f,
//    multiKillOneSpell	int	This is an offshoot of the OneStone challenge. The code checks if a spell with the same instance ID does the final point of damage to at least 2 Champions. It doesn't matter if they're enemies, but you cannot hurt your friends.
    @Json(name = "multiKillOneSpell")
    val multiKillOneSpell: Int = 0,
// multikills	int
    @Json(name = "multikills")
    val multikills: Int = 0,
// multikillsAfterAggressiveFlash	int
    @Json(name = "multikillsAfterAggressiveFlash")
    val multikillsAfterAggressiveFlash: Int = 0,
// multiTurretRiftHeraldCount	int
    @Json(name = "multiTurretRiftHeraldCount")
    val multiTurretRiftHeraldCount: Int = 0,
// outerTurretExecutesBefore10Minutes	int
    @Json(name = "outerTurretExecutesBefore10Minutes")
    val outerTurretExecutesBefore10Minutes: Int = 0,
// outnumberedKills	int
    @Json(name = "outnumberedKills")
    val outnumberedKills: Int = 0,
// outnumberedNexusKill	int
    @Json(name = "outnumberedNexusKill")
    val outnumberedNexusKill: Int = 0,
// perfectDragonSoulsTaken	int
    @Json(name = "perfectDragonSoulsTaken")
    val perfectDragonSoulsTaken: Int = 0,
// perfectGame	int
    @Json(name = "perfectGame")
    val perfectGame: Int = 0,
// pickKillWithAlly	int
    @Json(name = "pickKillWithAlly")
    val pickKillWithAlly: Int = 0,
// poroExplosions	int
    @Json(name = "poroExplosions")
    val poroExplosions: Int = 0,
// quickCleanse	int
    @Json(name = "quickCleanse")
    val quickCleanse: Int = 0,
// quickFirstTurret	int
    @Json(name = "quickFirstTurret")
    val quickFirstTurret: Int = 0,
// quickSoloKills	int
    @Json(name = "quickSoloKills")
    val quickSoloKills: Int = 0,
// riftHeraldTakedowns	int
    @Json(name = "riftHeraldTakedowns")
    val riftHeraldTakedowns: Int = 0,
// saveAllyFromDeath	int
    @Json(name = "saveAllyFromDeath")
    val saveAllyFromDeath: Int = 0,
// scuttleCrabKills	int
    @Json(name = "scuttleCrabKills")
    val scuttleCrabKills: Int = 0,
// shortestTimeToAceFromFirstTakedown	float
    @Json(name = "shortestTimeToAceFromFirstTakedown")
    val shortestTimeToAceFromFirstTakedown: Float = 0f,
// skillshotsDodged	int
    @Json(name = "skillshotsDodged")
    val skillshotsDodged: Int = 0,
// skillshotsHit	int
    @Json(name = "skillshotsHit")
    val skillshotsHit: Int = 0,
// snowballsHit	int
    @Json(name = "snowballsHit")
    val snowballsHit: Int = 0,
// soloBaronKills	int
    @Json(name = "soloBaronKills")
    val soloBaronKills: Int = 0,
// SWARM_DefeatAatrox	int
    @Json(name = "SWARM_DefeatAatrox")
    val swarmDefeatAatrox: Int = 0,
// SWARM_DefeatBriar	int
    @Json(name = "SWARM_DefeatBriar")
    val swarmDefeatBriar: Int = 0,
// SWARM_DefeatMiniBosses	int
    @Json(name = "SWARM_DefeatMiniBosses")
    val swarmDefeatMiniBosses: Int = 0,
// SWARM_EvolveWeapon	int
    @Json(name = "SWARM_EvolveWeapon")
    val swarmEvolveWeapon: Int = 0,
// SWARM_Have3Passives	int
    @Json(name = "SWARM_Have3Passives")
    val swarmHave3Passives: Int = 0,
// SWARM_KillEnemy	int
    @Json(name = "SWARM_KillEnemy")
    val swarmKillEnemy: Int = 0,
// SWARM_PickupGold	float
    @Json(name = "SWARM_PickupGold")
    val swarmPickupGold: Float = 0f,
// SWARM_ReachLevel50	int
    @Json(name = "SWARM_ReachLevel50")
    val swarmReachLevel50: Int = 0,
// SWARM_Survive15Min	int
    @Json(name = "SWARM_Survive15Min")
    val swarmSurvive15Min: Int = 0,
// SWARM_WinWith5EvolvedWeapons	int
    @Json(name = "SWARM_WinWith5EvolvedWeapons")
    val swarmWinWith5EvolvedWeapons: Int = 0,
// soloKills	int
    @Json(name = "soloKills")
    val soloKills: Int = 0,
// stealthWardsPlaced	int
    @Json(name = "stealthWardsPlaced")
    val stealthWardsPlaced: Int = 0,
// survivedSingleDigitHpCount	int
    @Json(name = "survivedSingleDigitHpCount")
    val survivedSingleDigitHpCount: Int = 0,
// survivedThreeImmobilizesInFight	int
    @Json(name = "survivedThreeImmobilizesInFight")
    val survivedThreeImmobilizesInFight: Int = 0,
// takedownOnFirstTurret	int
    @Json(name = "takedownOnFirstTurret")
    val takedownOnFirstTurret: Int = 0,
// takedowns	int
    @Json(name = "takedowns")
    val takedowns: Int = 0,
// takedownsAfterGainingLevelAdvantage	int
    @Json(name = "takedownsAfterGainingLevelAdvantage")
    val takedownsAfterGainingLevelAdvantage: Int = 0,
// takedownsBeforeJungleMinionSpawn	int
    @Json(name = "takedownsBeforeJungleMinionSpawn")
    val takedownsBeforeJungleMinionSpawn: Int = 0,
// takedownsFirstXMinutes	int
    @Json(name = "takedownsFirstXMinutes")
    val takedownsFirstXMinutes: Int = 0,
// takedownsInAlcove	int
    @Json(name = "takedownsInAlcove")
    val takedownsInAlcove: Int = 0,
// takedownsInEnemyFountain	int
    @Json(name = "takedownsInEnemyFountain")
    val takedownsInEnemyFountain: Int = 0,
// teamBaronKills	int
    @Json(name = "teamBaronKills")
    val teamBaronKills: Int = 0,
// teamDamagePercentage	float
    @Json(name = "teamDamagePercentage")
    val teamDamagePercentage: Float = 0f,
// teamElderDragonKills	int
    @Json(name = "teamElderDragonKills")
    val teamElderDragonKills: Int = 0,
// teamRiftHeraldKills	int
    @Json(name = "teamRiftHeraldKills")
    val teamRiftHeraldKills: Int = 0,
// tookLargeDamageSurvived	int
    @Json(name = "tookLargeDamageSurvived")
    val tookLargeDamageSurvived: Int = 0,
// turretPlatesTaken	int
    @Json(name = "turretPlatesTaken")
    val turretPlatesTaken: Int = 0,
// turretsTakenWithRiftHerald	int	Any player who damages a tower that is destroyed within 30 seconds of a Rift Herald charge will receive credit. A player who does not damage the tower will not receive credit.
    @Json(name = "turretsTakenWithRiftHerald")
    val turretsTakenWithRiftHerald: Int = 0,
// turretTakedowns	int
    @Json(name = "turretTakedowns")
    val turretTakedowns: Int = 0,
// twentyMinionsIn3SecondsCount	int
    @Json(name = "twentyMinionsIn3SecondsCount")
    val twentyMinionsIn3SecondsCount: Int = 0,
// twoWardsOneSweeperCount	int
    @Json(name = "twoWardsOneSweeperCount")
    val twoWardsOneSweeperCount: Int = 0,
// unseenRecalls	int
    @Json(name = "unseenRecalls")
    val unseenRecalls: Int = 0,
// visionScorePerMinute	float
    @Json(name = "visionScorePerMinute")
    val visionScorePerMinute: Float = 0f,
// wardsGuarded	int
    @Json(name = "wardsGuarded")
    val wardsGuarded: Int = 0,
// wardTakedowns	int
    @Json(name = "wardTakedowns")
    val wardTakedowns: Int = 0,
// wardTakedownsBefore20M	int
    @Json(name = "wardTakedownsBefore20M")
    val wardTakedownsBefore20M: Int = 0,
)

internal data class MissionDto(
//    playerScore0	int
//    playerScore1	int
//    playerScore2	int
//    playerScore3	int
//    playerScore4	int
//    playerScore5	int
//    playerScore6	int
//    playerScore7	int
//    playerScore8	int
//    playerScore9	int
//    playerScore10	int
//    playerScore11	int
    @Json(name = "playerScore0")
    val playerScore0: Int = 0,
    @Json(name = "playerScore1")
    val playerScore1: Int = 0,
    @Json(name = "playerScore2")
    val playerScore2: Int = 0,
    @Json(name = "playerScore3")
    val playerScore3: Int = 0,
    @Json(name = "playerScore4")
    val playerScore4: Int = 0,
    @Json(name = "playerScore5")
    val playerScore5: Int = 0,
    @Json(name = "playerScore6")
    val playerScore6: Int = 0,
    @Json(name = "playerScore7")
    val playerScore7: Int = 0,
    @Json(name = "playerScore8")
    val playerScore8: Int = 0,
    @Json(name = "playerScore9")
    val playerScore9: Int = 0,
    @Json(name = "playerScore10")
    val playerScore10: Int = 0,
    @Json(name = "playerScore11")
    val playerScore11: Int = 0,
)

internal data class ObjectiveDto(
    @Json(name = "first")
    val first: Boolean = false,
    @Json(name = "kills")
    val kills: Int = 0,
)

internal data class ObjectivesDto(
    @Json(name = "baron")
    val baron: ObjectiveDto,
    @Json(name = "champion")
    val champion: ObjectiveDto,
    @Json(name = "dragon")
    val dragon: ObjectiveDto,
    @Json(name = "horde")
    val horde: ObjectiveDto,
    @Json(name = "inhibitor")
    val inhibitor: ObjectiveDto,
    @Json(name = "riftHerald")
    val riftHerald: ObjectiveDto,
    @Json(name = "tower")
    val tower: ObjectiveDto,
)

internal data class BanDto(
    // TODO: Check if this can be null when theres no champ ban
    @Json(name = "championId")
    val championId: Int,
    @Json(name = "pickTurn")
    val pickTurn: Int = 0,
)

internal data class TeamDto(
    @Json(name = "bans")
    val bans: List<BanDto> = emptyList(),
    @Json(name = "objectives")
    val objectives: ObjectivesDto,
    // 100 for blue side, 200 for red side. need to be checked for gamemode such as Arena
    @Json(name = "teamId")
    val teamId: Int = 0,
    @Json(name = "win")
    val win: Boolean,
)

// TODO: Investigate. No Idea what that means.
internal data class PerkStyleSelectionDto(
    @Json(name = "perk")
    val perk: Int = 0,
    @Json(name = "var1")
    val var1: Int = 0,
    @Json(name = "var2")
    val var2: Int = 0,
    @Json(name = "var3")
    val var3: Int = 0,
)

internal data class PerkStyleDto(
    @Json(name = "description")
    val description: String = "",
    @Json(name = "selections")
    val selections: List<PerkStyleSelectionDto> = emptyList(),
    @Json(name = "style")
    val style: Int = 0,
)

internal data class PerkStatsDto(
    @Json(name = "defense")
    val defense: Int = 0,
    @Json(name = "flex")
    val flex: Int = 0,
    @Json(name = "offense")
    val offense: Int = 0,
)

internal data class PerksDto(
    @Json(name = "statPerks")
    val statPerks: PerkStatsDto = PerkStatsDto(),
    @Json(name = "styles")
    val styles: List<PerkStyleDto> = emptyList(),
)

internal data class ParticipantDto(
//    allInPings	int	Yellow crossed swords
    @Json(name = "allInPings")
    val allInPings: Int = 0,
//    assistMePings	int	Green flag
    @Json(name = "assistMePings")
    val assistMePings: Int = 0,
//    assists	int
    @Json(name = "assists")
    val assists: Int = 0,
//    baronKills	int
    @Json(name = "baronKills")
    val baronKills: Int = 0,
//    bountyLevel	int
    @Json(name = "bountyLevel")
    val bountyLevel: Int = 0,
//    champExperience	int
    @Json(name = "champExperience")
    val champExperience: Int,
//    champLevel	int
    @Json(name = "champLevel")
    val champLevel: Int,
//    championId	int	Prior to patch 11.4, on Feb 18th, 2021, this field returned invalid championIds. We recommend determining the champion based on the championName field for matches played prior to patch 11.4.
    @Json(name = "championId")
    val championId: Int,
// championName	string
    @Json(name = "championName")
    val championName: String,
// commandPings	int	Blue generic ping (ALT+click)
    @Json(name = "commandPings")
    val commandPings: Int = 0,
// championTransform	int	This field is currently only utilized for Kayn's transformations. (Legal values: 0 - None, 1 - Slayer, 2 - Assassin)
    @Json(name = "championTransform")
    val championTransform: Int = 0,
// consumablesPurchased	int
    @Json(name = "consumablesPurchased")
    val consumablesPurchased: Int = 0,
// challenges	ChallengesDto
    @Json(name = "challenges")
    val challenges: ChallengesDto = ChallengesDto(),
// damageDealtToBuildings	int
    @Json(name = "damageDealtToBuildings")
    val damageDealtToBuildings: Int = 0,
// damageDealtToObjectives	int
    @Json(name = "damageDealtToObjectives")
    val damageDealtToObjectives: Int = 0,
// damageDealtToTurrets	int
    @Json(name = "damageDealtToTurrets")
    val damageDealtToTurrets: Int = 0,
// damageSelfMitigated	int
    @Json(name = "damageSelfMitigated")
    val damageSelfMitigated: Int = 0,
// deaths	int
    @Json(name = "deaths")
    val deaths: Int = 0,
// detectorWardsPlaced	int
    @Json(name = "detectorWardsPlaced")
    val detectorWardsPlaced: Int = 0,
// doubleKills	int
    @Json(name = "doubleKills")
    val doubleKills: Int = 0,
// dragonKills	int
    @Json(name = "dragonKills")
    val dragonKills: Int = 0,
// eligibleForProgression	boolean
    @Json(name = "eligibleForProgression")
    val eligibleForProgression: Boolean = false,
// enemyMissingPings	int	Yellow questionmark
    @Json(name = "enemyMissingPings")
    val enemyMissingPings: Int = 0,
// enemyVisionPings	int	Red eyeball
    @Json(name = "enemyVisionPings")
    val enemyVisionPings: Int = 0,
// firstBloodAssist	boolean
    @Json(name = "firstBloodAssist")
    val firstBloodAssist: Boolean = false,
// firstBloodKill	boolean
    @Json(name = "firstBloodKill")
    val firstBloodKill: Boolean = false,
// firstTowerAssist	boolean
    @Json(name = "firstTowerAssist")
    val firstTowerAssist: Boolean = false,
// firstTowerKill	boolean
    @Json(name = "firstTowerKill")
    val firstTowerKill: Boolean = false,
// gameEndedInEarlySurrender	boolean	This is an offshoot of the OneStone challenge. The code checks if a spell with the same instance ID does the final point of damage to at least 2 Champions. It doesn't matter if they're enemies, but you cannot hurt your friends.
    @Json(name = "gameEndedInEarlySurrender")
    val gameEndedInEarlySurrender: Boolean = false,
// gameEndedInSurrender	boolean
    @Json(name = "gameEndedInSurrender")
    val gameEndedInSurrender: Boolean = false,
// holdPings	int
    @Json(name = "holdPings")
    val holdPings: Int = 0,
// getBackPings	int	Yellow circle with horizontal line
    @Json(name = "getBackPings")
    val getBackPings: Int = 0,
// goldEarned	int
    @Json(name = "goldEarned")
    val goldEarned: Int = 0,
// goldSpent	int
    @Json(name = "goldSpent")
    val goldSpent: Int = 0,
// individualPosition	string	Both individualPosition and teamPosition are computed by the game server and are different versions of the most likely position played by a player. The individualPosition is the best guess for which position the player actually played in isolation of anything else. The teamPosition is the best guess for which position the player actually played if we add the constraint that each team must have one top player, one jungle, one middle, etc. Generally the recommendation is to use the teamPosition field over the individualPosition field.
    @Json(name = "individualPosition")
    val individualPosition: String = "Invalid",
// inhibitorKills	int
    @Json(name = "inhibitorKills")
    val inhibitorKills: Int = 0,
// inhibitorTakedowns	int
    @Json(name = "inhibitorTakedowns")
    val inhibitorTakedowns: Int = 0,
// inhibitorsLost	int
    @Json(name = "inhibitorsLost")
    val inhibitorsLost: Int = 0,
// item0	int
    @Json(name = "item0")
    val item0: Int? = 0,
// item1	int
    @Json(name = "item1")
    val item1: Int = 0,
// item2	int
    @Json(name = "item2")
    val item2: Int = 0,
// item3	int
    @Json(name = "item3")
    val item3: Int = 0,
// item4	int
    @Json(name = "item4")
    val item4: Int = 0,
// item5	int
    @Json(name = "item5")
    val item5: Int = 0,
// item6	int
    @Json(name = "item6")
    val item6: Int = 0,
// itemsPurchased	int
    @Json(name = "itemsPurchased")
    val itemsPurchased: Int = 0,
// killingSprees	int
    @Json(name = "killingSprees")
    val killingSprees: Int = 0,
// kills	int
    @Json(name = "kills")
    val kills: Int = 0,
// lane	string
    @Json(name = "lane")
    val lane: String = "NONE",
// largestCriticalStrike	int
    @Json(name = "largestCriticalStrike")
    val largestCriticalStrike: Int = 0,
// largestKillingSpree	int
    @Json(name = "largestKillingSpree")
    val largestKillingSpree: Int = 0,
// largestMultiKill	int
    @Json(name = "largestMultiKill")
    val largestMultiKill: Int = 0,
// longestTimeSpentLiving	int
    @Json(name = "longestTimeSpentLiving")
    val longestTimeSpentLiving: Int = 0,
// magicDamageDealt	int
    @Json(name = "magicDamageDealt")
    val magicDamageDealt: Int = 0,
// magicDamageDealtToChampions	int
    @Json(name = "magicDamageDealtToChampions")
    val magicDamageDealtToChampions: Int = 0,
// magicDamageTaken	int
    @Json(name = "magicDamageTaken")
    val magicDamageTaken: Int = 0,
// missions	MissionsDto
    @Json(name = "missions")
    val missions: MissionDto = MissionDto(),
// neutralMinionsKilled	int	neutralMinionsKilled = mNeutralMinionsKilled, which is incremented on kills of kPet and kJungleMonster
    @Json(name = "neutralMinionsKilled")
    val neutralMinionsKilled: Int = 0,
// needVisionPings	int	Green ward
    @Json(name = "needVisionPings")
    val needVisionPings: Int = 0,
// nexusKills	int
    @Json(name = "nexusKills")
    val nexusKills: Int = 0,
// nexusTakedowns	int
    @Json(name = "nexusTakedowns")
    val nexusTakedowns: Int = 0,
// nexusLost	int
    @Json(name = "nexusLost")
    val nexusLost: Int = 0,
// objectivesStolen	int
    @Json(name = "objectivesStolen")
    val objectivesStolen: Int = 0,
// objectivesStolenAssists	int
    @Json(name = "objectivesStolenAssists")
    val objectivesStolenAssists: Int = 0,
// onMyWayPings	int	Blue arrow pointing at ground
    @Json(name = "onMyWayPings")
    val onMyWayPings: Int = 0,
// participantId	int
    @Json(name = "participantId")
    val participantId: Int = 0,
// pentaKills	int
    @Json(name = "pentaKills")
    val pentaKills: Int = 0,
// perks	PerksDto
    @Json(name = "perks")
    val perks: PerksDto = PerksDto(),
// physicalDamageDealt	int
    @Json(name = "physicalDamageDealt")
    val physicalDamageDealt: Int = 0,
// physicalDamageDealtToChampions	int
    @Json(name = "physicalDamageDealtToChampions")
    val physicalDamageDealtToChampions: Int = 0,
// physicalDamageTaken	int
    @Json(name = "physicalDamageTaken")
    val physicalDamageTaken: Int = 0,
// placement	int
    @Json(name = "placement")
    val placement: Int = 0,
// playerAugment1	int
    @Json(name = "playerAugment1")
    val playerAugment1: Int = 0,
// playerAugment2	int
    @Json(name = "playerAugment2")
    val playerAugment2: Int = 0,
// playerAugment3	int
    @Json(name = "playerAugment3")
    val playerAugment3: Int = 0,
// playerAugment4	int
    @Json(name = "playerAugment4")
    val playerAugment4: Int = 0,
// playerAugment5	int
    @Json(name = "playerAugment5")
    val playerAugment5: Int = 0,
// playerAugment6	int
    @Json(name = "playerAugment6")
    val playerAugment6: Int = 0,
// playerSubteamId	int
    @Json(name = "playerSubteamId")
    val playerSubteamId: Int = 0,
// pushPings	int	Green minion
    @Json(name = "pushPings")
    val pushPings: Int = 0,
// profileIcon	int
    @Json(name = "profileIcon")
    val profileIcon: Int = 0,
// puuid	string
    @Json(name = "puuid")
    val puuid: String,
// quadraKills	int
    @Json(name = "quadraKills")
    val quadraKills: Int = 0,
// riotIdGameName	string
    @Json(name = "riotIdGameName")
    val riotIdGameName: String,
// riotIdName	string
    @Deprecated("Should not be used by riot api anymore")
    @Json(name = "riotIdName")
    val riotIdName: String? = null,
// riotIdTagline	string
    @Json(name = "riotIdTagline")
    val riotIdTagline: String = "NONE",
// role	string
    // Should not happen I guess but need investigations.
    @Json(name = "role")
    val role: String = "UNKNOWN",
// sightWardsBoughtInGame	int
    @Json(name = "sightWardsBoughtInGame")
    val sightWardsBoughtInGame: Int = 0,
// spell1Casts	int
    @Json(name = "spell1Casts")
    val spell1Casts: Int = 0,
// spell2Casts	int
    @Json(name = "spell2Casts")
    val spell2Casts: Int = 0,
// spell3Casts	int
    @Json(name = "spell3Casts")
    val spell3Casts: Int = 0,
// spell4Casts	int
    @Json(name = "spell4Casts")
    val spell4Casts: Int = 0,
// subteamPlacement	int
    @Json(name = "subteamPlacement")
    val subteamPlacement: Int = 0,
// summoner1Casts	int
    @Json(name = "summoner1Casts")
    val summoner1Casts: Int = 0,
// summoner1Id	int
    @Json(name = "summoner1Id")
    val summoner1Id: Int = 0,
// summoner2Casts	int
    @Json(name = "summoner2Casts")
    val summoner2Casts: Int = 0,
// summoner2Id	int
    @Json(name = "summoner2Id")
    val summoner2Id: Int = 0,
// summonerId	string
    @Json(name = "summonerId")
    val summonerId: String,
// summonerLevel	int
    @Json(name = "summonerLevel")
    val summonerLevel: Int = 0,
// summonerName	string
    @Json(name = "summonerName")
    val summonerName: String,
// teamEarlySurrendered	boolean
    @Json(name = "teamEarlySurrendered")
    val teamEarlySurrendered: Boolean = false,
// teamId	int
    @Json(name = "teamId")
    val teamId: Long = 0,
// teamPosition	string	Both individualPosition and teamPosition are computed by the game server and are different versions of the most likely position played by a player. The individualPosition is the best guess for which position the player actually played in isolation of anything else. The teamPosition is the best guess for which position the player actually played if we add the constraint that each team must have one top player, one jungle, one middle, etc. Generally the recommendation is to use the teamPosition field over the individualPosition field.
    @Json(name = "teamPosition")
    val teamPosition: String = "",
// timeCCingOthers	int
    @Json(name = "timeCCingOthers")
    val timeCCingOthers: Int = 0,
// timePlayed	int
    @Json(name = "timePlayed")
    val timePlayed: Int = 0,
// totalAllyJungleMinionsKilled	int
    @Json(name = "totalAllyJungleMinionsKilled")
    val totalAllyJungleMinionsKilled: Int = 0,
// totalDamageDealt	int
    @Json(name = "totalDamageDealt")
    val totalDamageDealt: Int = 0,
// totalDamageDealtToChampions	int
    @Json(name = "totalDamageDealtToChampions")
    val totalDamageDealtToChampions: Int = 0,
// totalDamageShieldedOnTeammates	int
    @Json(name = "totalDamageShieldedOnTeammates")
    val totalDamageShieldedOnTeammates: Int = 0,
// totalDamageTaken	int
    @Json(name = "totalDamageTaken")
    val totalDamageTaken: Int = 0,
// totalEnemyJungleMinionsKilled	int
    @Json(name = "totalEnemyJungleMinionsKilled")
    val totalEnemyJungleMinionsKilled: Int = 0,
// totalHeal	int	Whenever positive health is applied (which translates to all heals in the game but not things like regeneration), totalHeal is incremented by the amount of health received. This includes healing enemies, jungle monsters, yourself, etc
    @Json(name = "totalHeal")
    val totalHeal: Int = 0,
// totalHealsOnTeammates	int	Whenever positive health is applied (which translates to all heals in the game but not things like regeneration), totalHealsOnTeammates is incremented by the amount of health received. This is post modified, so if you heal someone missing 5 health for 100 you will get +5 totalHealsOnTeammates
    @Json(name = "totalHealsOnTeammates")
    val totalHealsOnTeammates: Int = 0,
// totalMinionsKilled	int	totalMillionsKilled = mMinionsKilled, which is only incremented on kills of kTeamMinion, kMeleeLaneMinion, kSuperLaneMinion, kRangedLaneMinion and kSiegeLaneMinion
    @Json(name = "totalMinionsKilled")
    val totalMinionsKilled: Int = 0,
// totalTimeCCDealt	int
    @Json(name = "totalTimeCCDealt")
    val totalTimeCCDealt: Int = 0,
// totalTimeSpentDead	int
    @Json(name = "totalTimeSpentDead")
    val totalTimeSpentDead: Int = 0,
// totalUnitsHealed	int
    @Json(name = "totalUnitsHealed")
    val totalUnitsHealed: Int = 0,
// tripleKills	int
    @Json(name = "tripleKills")
    val tripleKills: Int = 0,
// trueDamageDealt	int
    @Json(name = "trueDamageDealt")
    val trueDamageDealt: Int = 0,
// trueDamageDealtToChampions	int
    @Json(name = "trueDamageDealtToChampions")
    val trueDamageDealtToChampions: Int = 0,
// trueDamageTaken	int
    @Json(name = "trueDamageTaken")
    val trueDamageTaken: Int = 0,
// turretKills	int
    @Json(name = "turretKills")
    val turretKills: Int = 0,
// turretTakedowns	int
    @Json(name = "turretTakedowns")
    val turretTakedowns: Int = 0,
// turretsLost	int
    @Json(name = "turretsLost")
    val turretsLost: Int = 0,
// unrealKills	int
    @Json(name = "unrealKills")
    val unrealKills: Int = 0,
// visionScore	int
    @Json(name = "visionScore")
    val visionScore: Int = 0,
// visionClearedPings	int
    @Json(name = "visionClearedPings")
    val visionClearedPings: Int = 0,
// visionWardsBoughtInGame	int
    @Json(name = "visionWardsBoughtInGame")
    val visionWardsBoughtInGame: Int = 0,
// wardsKilled	int
    @Json(name = "wardsKilled")
    val wardsKilled: Int = 0,
// wardsPlaced	int
    @Json(name = "wardsPlaced")
    val wardsPlaced: Int = 0,
// win	boolean
    @Json(name = "win")
    val win: Boolean = false,
)

internal data class InfoDto(
//    endOfGameResult	string	Refer to indicate if the game ended in termination.
    @Json(name = "endOfGameResult")
    val endOfGameResult: String,
//    gameCreation	long	Unix timestamp for when the game is created on the game server (i.e., the loading screen).
    @Json(name = "gameCreation")
    val gameCreation: Long,
// gameDuration	long	Prior to patch 11.20, this field returns the game length in milliseconds calculated from gameEndTimestamp - gameStartTimestamp. Post patch 11.20, this field returns the max timePlayed of any participant in the game in seconds, which makes the behavior of this field consistent with that of match-v4. The best way to handling the change in this field is to treat the value as milliseconds if the gameEndTimestamp field isn't in the response and to treat the value as seconds if gameEndTimestamp is in the response.
    @Json(name = "gameDuration")
    val gameDuration: Long,
// gameEndTimestamp	long	Unix timestamp for when match ends on the game server. This timestamp can occasionally be significantly longer than when the match "ends". The most reliable way of determining the timestamp for the end of the match would be to add the max time played of any participant to the gameStartTimestamp. This field was added to match-v5 in patch 11.20 on Oct 5th, 2021.
    @Json(name = "gameEndTimestamp")
    val gameEndTimestamp: Long,
// gameId	long
    @Json(name = "gameId")
    val gameId: Long,
// gameMode	string	Refer to the Game Constants documentation.
    @Json(name = "gameMode")
    val gameMode: String,
// gameName	string
    @Json(name = "gameName")
    val gameName: String,
// gameStartTimestamp	long	Unix timestamp for when match starts on the game server.
    @Json(name = "gameStartTimestamp")
    val gameStartTimestamp: Long,
// gameType	string
    @Json(name = "gameType")
    val gameType: String,
// gameVersion	string	The first two parts can be used to determine the patch a game was played on.
    @Json(name = "gameVersion")
    val gameVersion: String,
// mapId	int	Refer to the Game Constants documentation.
    @Json(name = "mapId")
    val mapId: Int,
// participants	List[ParticipantDto]
    @Json(name = "participants")
    val participants: List<ParticipantDto> = emptyList(),
// platformId	string	Platform where the match was played.
    @Json(name = "platformId")
    val platformId: String,
// queueId	int	Refer to the Game Constants documentation.
    @Json(name = "queueId")
    val queueId: Int,
// teams	List[TeamDto]
    @Json(name = "teams")
    val teams: List<TeamDto> = emptyList(),
// tournamentCode	string	Tournament code used to generate the match. This field was added to match-v5 in patch 11.13 on June 23rd, 2021.
    @Json(name = "tournamentCode")
    val tournamentCode: String = "",
)

internal data class MatchDto(
    @Json(name = "info")
    val info: InfoDto,
    @Json(name = "metadata")
    val metadata: MetadataDto,
)

// Timeline Match

internal data class PositionDto(
    @Json(name = "x")
    val x: Int = 0,
    @Json(name = "y")
    val y: Int = 0,
)

// magicDamageDone	int
// magicDamageDoneToChampions	int
// magicDamageTaken	int
// physicalDamageDone	int
// physicalDamageDoneToChampions	int
// physicalDamageTaken	int
// totalDamageDone	int
// totalDamageDoneToChampions	int
// totalDamageTaken	int
// trueDamageDone	int
// trueDamageDoneToChampions	int
// trueDamageTaken	int

internal data class DamageStatsDto(
    @Json(name = "magicDamageDone")
    val magicDamageDone: Int = 0,
    @Json(name = "magicDamageDoneToChampions")
    val magicDamageDoneToChampions: Int = 0,
    @Json(name = "magicDamageTaken")
    val magicDamageTaken: Int = 0,
    @Json(name = "physicalDamageDone")
    val physicalDamageDone: Int = 0,
    @Json(name = "physicalDamageDoneToChampions")
    val physicalDamageDoneToChampions: Int = 0,
    @Json(name = "physicalDamageTaken")
    val physicalDamageTaken: Int = 0,
    @Json(name = "totalDamageDone")
    val totalDamageDone: Int = 0,
    @Json(name = "totalDamageDoneToChampions")
    val totalDamageDoneToChampions: Int = 0,
    @Json(name = "totalDamageTaken")
    val totalDamageTaken: Int = 0,
    @Json(name = "trueDamageDone")
    val trueDamageDone: Int = 0,
    @Json(name = "trueDamageDoneToChampions")
    val trueDamageDoneToChampions: Int = 0,
    @Json(name = "trueDamageTaken")
    val trueDamageTaken: Int = 0,
)

// abilityHaste	int
// abilityPower	int
// armor	int
// armorPen	int
// armorPenPercent	int
// attackDamage	int
// attackSpeed	int
// bonusArmorPenPercent	int
// bonusMagicPenPercent	int
// ccReduction	int
// cooldownReduction	int
// health	int
// healthMax	int
// healthRegen	int
// lifesteal	int
// magicPen	int
// magicPenPercent	int
// magicResist	int
// movementSpeed	int
// omnivamp	int
// physicalVamp	int
// power	int
// powerMax	int
// powerRegen	int
// spellVamp	int
internal data class ChampionStatsDto(
    @Json(name = "abilityHaste")
    val abilityHaste: Int = 0,
    @Json(name = "abilityPower")
    val abilityPower: Int = 0,
    @Json(name = "armor")
    val armor: Int = 0,
    @Json(name = "armorPen")
    val armorPen: Int = 0,
    @Json(name = "armorPenPercent")
    val armorPenPercent: Int = 0,
    @Json(name = "attackDamage")
    val attackDamage: Int = 0,
    @Json(name = "attackSpeed")
    val attackSpeed: Int = 0,
    @Json(name = "bonusArmorPenPercent")
    val bonusArmorPenPercent: Int = 0,
    @Json(name = "bonusMagicPenPercent")
    val bonusMagicPenPercent: Int = 0,
    @Json(name = "ccReduction")
    val ccReduction: Int = 0,
    @Json(name = "cooldownReduction")
    val cooldownReduction: Int = 0,
    @Json(name = "health")
    val health: Int = 0,
    @Json(name = "healthMax")
    val healthMax: Int = 0,
    @Json(name = "healthRegen")
    val healthRegen: Int = 0,
    @Json(name = "lifesteal")
    val lifesteal: Int = 0,
    @Json(name = "magicPen")
    val magicPen: Int = 0,
    @Json(name = "magicPenPercent")
    val magicPenPercent: Int = 0,
    @Json(name = "magicResist")
    val magicResist: Int = 0,
    @Json(name = "movementSpeed")
    val movementSpeed: Int = 0,
    @Json(name = "omnivamp")
    val omnivamp: Int = 0,
    @Json(name = "physicalVamp")
    val physicalVamp: Int = 0,
    @Json(name = "power")
    val power: Int = 0,
    @Json(name = "powerMax")
    val powerMax: Int = 0,
    @Json(name = "powerRegen")
    val powerRegen: Int = 0,
    @Json(name = "spellVamp")
    val spellVamp: Int = 0,
)

// championStats	ChampionStatsDto
// currentGold	int
// damageStats	DamageStatsDto
// goldPerSecond	int
// jungleMinionsKilled	int
// level	int
// minionsKilled	int
// participantId	int
// position	PositionDto
// timeEnemySpentControlled	int
// totalGold	int
// xp	int
internal data class ParticipantFrameDto(
    @Json(name = "championStats")
    val championStats: ChampionStatsDto = ChampionStatsDto(),
    @Json(name = "currentGold")
    val currentGold: Int = 0,
    @Json(name = "damageStats")
    val damageStats: DamageStatsDto = DamageStatsDto(),
    @Json(name = "goldPerSecond")
    val goldPerSecond: Int = 0,
    @Json(name = "jungleMinionsKilled")
    val jungleMinionsKilled: Int = 0,
    @Json(name = "level")
    val level: Int = 0,
    @Json(name = "minionsKilled")
    val minionsKilled: Int = 0,
    @Json(name = "participantId")
    val participantId: Int = 0,
    @Json(name = "position")
    val position: PositionDto = PositionDto(),
    @Json(name = "timeEnemySpentControlled")
    val timeEnemySpentControlled: Int = 0,
    @Json(name = "totalGold")
    val totalGold: Int = 0,
    @Json(name = "xp")
    val xp: Int = 0,
)

// timestamp	long
// realTimestamp	long
// type	string

/**
 * EventDto extends UnpredictableDto because it's field depends on the type.
 */
internal data class EventTimelineDto(
    @Json(name = "timestamp")
    val timestamp: Long,
    @Json(name = "type")
    val type: String,
) : UnpredictableDto()

// events	List[EventsTimeLineDto]
// participantFrames	ParticipantFramesDto
// timestamp	int

internal data class FramesTimelineDto(
    @Json(name = "events")
    val events: List<EventTimelineDto> = emptyList(),
    @Json(name = "participantFrames")
    val participantFrames: Map<String, ParticipantFrameDto> = emptyMap(),
    @Json(name = "timestamp")
    val timestamp: Long = 0,
)

// participantId	int
// puuid	string

internal data class ParticipantTimelineDto(
    @Json(name = "participantId")
    val participantId: Int = 0,
    @Json(name = "puuid")
    val puuid: String,
)

// endOfGameResult	string	Refer to indicate if the game ended in termination.
// frameInterval	long
// gameId	long
// participants	List[ParticipantTimeLineDto]
// frames	List[FramesTimeLineDto]

internal data class InfoTimelineDto(
    @Json(name = "endOfGameResult")
    val endOfGameResult: String,
    @Json(name = "frameInterval")
    val frameInterval: Long,
    @Json(name = "gameId")
    val gameId: Long,
    @Json(name = "participants")
    val participants: List<ParticipantTimelineDto> = emptyList(),
    @Json(name = "frames")
    val frames: List<FramesTimelineDto> = emptyList(),
)

// dataVersion	string	Match data version.
// matchId	string	Match id.
// participants	List[string]	A list of participant PUUIDs.

internal data class MetadataTimelineDto(
    @Json(name = "dataVersion")
    val dataVersion: String,
    @Json(name = "matchId")
    val matchId: String,
    @Json(name = "participants")
    val participants: List<String> = emptyList(),
)

internal data class TimelineDto(
    @Json(name = "info")
    val info: InfoTimelineDto,
    @Json(name = "metadata")
    val metadata: MetadataTimelineDto,
)

internal data class ChallengeDto(
//    "description": "",
    @Json(name = "description")
    val description: String,
// "name": "??????????????????",
    @Json(name = "name")
    val name: String,
// "shortDescription": "?????????? ???????? ????????????????????"
    @Json(name = "shortDescription")
    val shortDescription: String,
)

internal data class ChallengeConfigInfoDto(
//    id	long
    @Json(name = "id")
    val id: Long,
//    localizedNames	Map[String, Map[String, string]]
    @Json(name = "localizedNames")
    val localizedNames: Map<String, ChallengeDto>,
//    startTimestamp	long
    // startTimestamp is never defined
    // endTimestamp is rarely defined.
//    leaderboard	boolean
    @Json(name = "leaderboard")
    val leaderboard: Boolean,
//    thresholds	Map[String, double]
    @Json(name = "thresholds")
    val thresholds: Map<String, Int>,
)

internal data class ApexPlayerInfoDto(
//    puuid	string
    @Json(name = "puuid")
    val puuid: String,
//    value	double
    @Json(name = "value")
    val value: Double,
//    position	int
    @Json(name = "position")
    val position: Int,
)

internal data class ChallengeInfoDto(
//    "challengeId": 402407,
    @Json(name = "challengeId")
    val challengeId: Long,
//    "percentile": 0.11,
    @Json(name = "percentile")
    val percentile: Double,
//    "level": "PLATINUM",
    @Json(name = "level")
    val level: String,
//    "value": 40696,
    @Json(name = "value")
    val value: Long,
//    "achievedTime": 1719772956279
    @Json(name = "achievedTime")
    val achievedTime: Long? = null,
)

internal data class ChallengePointsDto(
//    "level": "GOLD",
    @Json(name = "level")
    val level: String,
//    "current": 6185,
    @Json(name = "current")
    val current: Long,
//    "max": 29680,
    @Json(name = "max")
    val max: Long,
//    "percentile": 0.158
    @Json(name = "percentile")
    val percentile: Double,
)

internal data class PlayerClientReferencesDto(
//    "bannerAccent": "1",
    @Json(name = "bannerAccent")
    val bannerAccent: String,
//    "title": "50400005",
    @Json(name = "title")
    val title: String,
//    "challengeIds": [
//    601002,
//    504001,
//    501008
//    ],
    @Json(name = "challengeIds")
    val challengeIds: List<Long> = emptyList(),
//    "crestBorder": "1",
    @Json(name = "crestBorder")
    val crestBorder: String,
//    "prestigeCrestBorderLevel": 500
    @Json(name = "prestigeCrestBorderLevel")
    val prestigeCrestBorderLevel: Int,
)

internal data class PlayerInfoDto(
//    challenges	List[ChallengeInfo]
    @Json(name = "challenges")
    val challenges: List<ChallengeInfoDto> = emptyList(),
//    preferences	PlayerClientPreferences
    @Json(name = "preferences")
    val preferences: PlayerClientReferencesDto,
//    totalPoints	ChallengePoints
    @Json(name = "totalPoints")
    val totalPoints: ChallengePointsDto,
//    categoryPoints	Map[String, ChallengePoints]
    @Json(name = "categoryPoints")
    val categoryPoints: Map<String, ChallengePointsDto> = emptyMap(),
)

// =============Clash DTO==================

internal data class PlayerDto(
    @Json(name = "summonerId")
    val summonerId: String,
    @Json(name = "teamId")
    val teamId: String,
    // legal values: "UNSELECTED", "FILL", "TOP", "JUNGLE", "MIDDLE", "BOTTOM", "UTILITY"
    @Json(name = "position")
    val position: String,
    // legal values: "CAPTAIN", "MEMBER"
    @Json(name = "role")
    val role: String,
)

internal data class TeamDtoClash(
    @Json(name = "id")
    val id: String,
    @Json(name = "tournamentId")
    val tournamentId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "iconId")
    val iconId: Int,
    @Json(name = "tier")
    val tier: Int,
    @Json(name = "captain")
    val captain: String,
    @Json(name = "abbreviation")
    val abbreviation: String,
    @Json(name = "players")
    val players: List<PlayerDto> = emptyList(),
)

internal data class TournamentPhaseDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "registrationTime")
    val registrationTime: Long,
    @Json(name = "startTime")
    val startTime: Long,
    @Json(name = "cancelled")
    val cancelled: Boolean,
)

internal data class TournamentDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "themeId")
    val themeId: Int,
    @Json(name = "nameKey")
    val nameKey: String,
    @Json(name = "nameKeySecondary")
    val nameKeySecondary: String,
    @Json(name = "schedule")
    val schedule: List<TournamentPhaseDto> = emptyList(),
)

// =============Spectator DTO==================

internal data class GameCustomizationObjectDto(
    @Json(name = "category")
    val category: String,
    @Json(name = "content")
    val content: String,
)

internal data class PerksDtoSpectator(
    @Json(name = "perkIds")
    val perkIds: List<Long> = emptyList(),
    @Json(name = "perkStyle")
    val perkStyle: Long = 0,
    @Json(name = "perkSubStyle")
    val perkSubStyle: Long = 0,
)

internal data class CurrentGameParticipantDto(
    @Json(name = "championId")
    val championId: Long = 0,
    @Json(name = "perks")
    val perks: PerksDtoSpectator = PerksDtoSpectator(),
    @Json(name = "profileIconId")
    val profileIconId: Long = 0,
    @Json(name = "bot")
    val bot: Boolean,
    @Json(name = "teamId")
    val teamId: Long = 0,
    @Json(name = "summonerId")
    val summonerId: String,
    @Json(name = "puuid")
    val puuid: String,
    @Json(name = "spell1Id")
    val spell1Id: Long = 0,
    @Json(name = "spell2Id")
    val spell2Id: Long = 0,
    @Json(name = "gameCustomizationObjects")
    val gameCustomizationObjects: List<GameCustomizationObjectDto> = emptyList(),
)

internal data class ObserverDto(
    @Json(name = "encryptionKey")
    val encryptionKey: String = "",
)

internal data class BannedChampionDto(
    @Json(name = "pickTurn")
    val pickTurn: Int = 0,
    @Json(name = "championId")
    val championId: Long = 0,
    @Json(name = "teamId")
    val teamId: Long = 0,
)

internal data class CurrentGameInfoDto(
    @Json(name = "gameId")
    val gameId: Long = 0,
    @Json(name = "gameType")
    val gameType: String,
    @Json(name = "gameStartTime")
    val gameStartTime: Long = 0,
    @Json(name = "mapId")
    val mapId: Long = 0,
    @Json(name = "gameLength")
    val gameLength: Long = 0,
    @Json(name = "platformId")
    val platformId: String,
    @Json(name = "gameMode")
    val gameMode: String,
    @Json(name = "bannedChampions")
    val bannedChampions: List<BannedChampionDto> = emptyList(),
    @Json(name = "gameQueueConfigId")
    val gameQueueConfigId: Long = 0,
    @Json(name = "observers")
    val observers: ObserverDto = ObserverDto(),
    @Json(name = "participants")
    val participants: List<CurrentGameParticipantDto> = emptyList(),
)

internal data class ParticipantDtoSpectator(
    @Json(name = "bot")
    val bot: Boolean,
    @Json(name = "spell2Id")
    val spell2Id: Long = 0,
    @Json(name = "profileIconId")
    val profileIconId: Long = 0,
    @Json(name = "summonerId")
    val summonerId: String,
    @Json(name = "puuid")
    val puuid: String,
    @Json(name = "championId")
    val championId: Long = 0,
    @Json(name = "teamId")
    val teamId: Long = 0,
    @Json(name = "spell1Id")
    val spell1Id: Long = 0,
)

internal data class FeaturedGameInfoDto(
    @Json(name = "gameMode")
    val gameMode: String,
    @Json(name = "gameLength")
    val gameLength: Long = 0,
    @Json(name = "mapId")
    val mapId: Long = 0,
    @Json(name = "gameType")
    val gameType: String,
    @Json(name = "bannedChampions")
    val bannedChampions: List<BannedChampionDto> = emptyList(),
    @Json(name = "gameId")
    val gameId: Long = 0,
    @Json(name = "observers")
    val observers: ObserverDto = ObserverDto(),
    @Json(name = "gameQueueConfigId")
    val gameQueueConfigId: Long = 0,
    @Json(name = "participants")
    val participants: List<ParticipantDtoSpectator> = emptyList(),
    @Json(name = "platformId")
    val platformId: String,
)

internal data class FeaturedGamesDto(
    @Json(name = "gameList")
    val gameList: List<FeaturedGameInfoDto> = emptyList(),
    @Json(name = "clientRefreshInterval")
    val clientRefreshInterval: Long = 0,
)
