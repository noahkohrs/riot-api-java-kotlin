package com.noahkohrs.riot.api.lol.match

import com.noahkohrs.riot.api.dtos.*
import com.noahkohrs.riot.api.values.Position
import com.noahkohrs.riot.api.values.Role
import com.noahkohrs.riot.api.values.TeamSide

@JvmRecord
public data class Metadata(
    public val dataVersion: String,
    public val matchId: String,
    public val participantsPuuids: List<String>,
) {
    internal companion object {
        fun fromDto(dto: MetadataDto): Metadata {
            return Metadata(
                dataVersion = dto.dataVersion,
                matchId = dto.matchId,
                participantsPuuids = dto.participantsPuuids,
            )
        }
    }
}

// public data class ChallengeDto
// Let's just use the dto for now. Might be nice to have a Challenge class later.
// We cdould sort by things such as wards, dragon, ...

// Mission has a List called playerScores.

@JvmRecord
public data class Objective(
    val first: Boolean,
    val kills: Int,
) {
    internal companion object {
        fun fromDto(dto: ObjectiveDto): Objective {
            return Objective(
                first = dto.first,
                kills = dto.kills,
            )
        }
    }
}

@JvmRecord
public data class Objectives(
    val baron: Objective,
    val champion: Objective,
    val dragon: Objective,
    val inhibitor: Objective,
    val riftHerald: Objective,
    val tower: Objective,
) {
    internal companion object {
        fun fromDto(dto: ObjectivesDto): Objectives {
            return Objectives(
                baron = Objective.fromDto(dto.baron),
                champion = Objective.fromDto(dto.champion),
                dragon = Objective.fromDto(dto.dragon),
                inhibitor = Objective.fromDto(dto.inhibitor),
                riftHerald = Objective.fromDto(dto.riftHerald),
                tower = Objective.fromDto(dto.tower),
            )
        }
    }
}

@JvmRecord
public data class Ban(
    val championId: Int,
    val pickTurn: Int,
) {
    internal companion object {
        fun fromDto(dto: BanDto): Ban {
            return Ban(
                championId = dto.championId,
                pickTurn = dto.pickTurn,
            )
        }
    }
}

@JvmRecord
public data class Team(
    val bans: List<Ban>,
    val objectives: Objectives,
    val teamId: Int,
    val win: Boolean,
) {
    internal companion object {
        fun fromDto(dto: TeamDto): Team {
            return Team(
                bans = dto.bans.map { Ban.fromDto(it) },
                objectives = Objectives.fromDto(dto.objectives),
                teamId = dto.teamId,
                win = dto.win,
            )
        }
    }
}

@JvmRecord
public data class Participant(
    val participantId: Int,
    // kills obtained from kills stats.
    val assists: Int,
    val deaths: Int,
    val challenges: ChallengesDto,
    val mission: MissionDto,
    val items: List<Int>,
    val augmentsIds: List<Int>,
    val userData: UserData,
    val pings: PingsStats,
    val castsStats: CastsStats,
    val teamStats: TeamStats,
    val playerStats: PlayerStats,
    val damagesStats: DamagesStats,
    val wardStats: WardStats,
    val championStats: ChampionStats,
    val objectivesStats: ObjectivesStats,
    val killsStats: KillsStats,
) {
    /**
     * Player's kills
     */
    public val kills: Int
        get() = killsStats.kills

    @JvmRecord
    public data class UserData(
        val profileIcon: Int,
        val riotIdGameName: String,
        val riotIdTagLine: String,
        val summonerName: String,
        val summonerLevel: Int,
    )

    @JvmRecord
    public data class PingsStats(
        /**
         * Yellow crossed swords
         */
        val allIn: Int,
        /**
         * Green flag
         */
        val assistMe: Int,
        /**
         * Blue generic ping
         */
        val commandPings: Int,
        /**
         * Yellow question mark
         */
        val enemyMissing: Int,
        val holdOn: Int,
        val getBack: Int,
        /**
         * Green ward
         */
        val needVision: Int,
        val onMyWay: Int,
        val push: Int,
        val visionCleared: Int,
    )

    @JvmRecord
    public data class TeamStats(
        val gameEndedInSurrender: Boolean,
        val gameEndedInEarlySurrender: Boolean,
        val win: Boolean,
        val teamId: TeamSide,
        val teamEarlySurrendered: Boolean,
    )

    @JvmRecord
    public data class PlayerStats(
        val bountyLevel: Int,
        val goldEarned: Int,
        val goldSpent: Int,
        val eligibleForProgression: Boolean,
        val largestCriticalStrike: Int,
        val longestTimeSpentLiving: Int,
        /**
         * Both `individualPosition` and `teamPosition` are the most likely position for a player, done by different calculations.
         */
        val individualPosition: Position,
        /**
         * Both `individualPosition` and `teamPosition` are the most likely position for a player, done by different calculations.
         */
        val teamPosition: Position,
        val role: Role,
        val placement: Int,
        val timeCCingOthers: Int,
        val timePlayed: Int,
        val totalTimeSpentDead: Int,
        val totalTimeCCDealt: Int,
        val consumablesPurchased: Int,
        val summoner1Id: Int,
        val summoner2Id: Int,
    )

    @JvmRecord
    public data class CastsStats(
        val spell1Casts: Int,
        val spell2Casts: Int,
        val spell3Casts: Int,
        val spell4Casts: Int,
        val summoner1Casts: Int,
        val summoner2Casts: Int,
    )

    @JvmRecord
    public data class DamagesStats(
        val physical: Damage,
        val magic: Damage,
        val trueDmg: Damage,
        val total: Damage,
        val dealtToBuildings: Int,
        val dealtToObjectives: Int,
        val dealtToTurrets: Int,
        val selfMitigated: Int,
        val totalShieldedOnTeammates: Int,
        val totalHealed: Int,
        val totalHealedOnTeammates: Int,
        val totalUnitsHealed: Int,
    ) {
        @JvmRecord
        public data class Damage(
            val dealt: Int,
            val dealtToChampions: Int,
            val taken: Int,
        )

        val totalHealedOnSelf: Int
            get() = totalHealed - totalHealedOnTeammates
    }

    @JvmRecord
    public data class WardStats(
        val placed: Int,
        val destroyed: Int,
        val detectorWardsPlaced: Int,
        val sightWardsBought: Int,
        val visionWardsBought: Int,
    )

    @JvmRecord
    public data class ChampionStats(
        val id: Int,
        val level: Int,
        val experience: Int,
        val name: String,
        /**
         * This is only used by Kayn for now.
         */
        val transform: Int,
    )

    @JvmRecord
    public data class ObjectivesStats(
        val firstTowerKill: Boolean,
        val firstTowerAssist: Boolean,
        val inhibitorKills: Int,
        val inhibitorTakedowns: Int,
        val inhibitorsLost: Int,
        val baronKills: Int,
        val dragonKills: Int,
        val nexusKills: Int,
        val nexusTakedowns: Int,
        val turretKills: Int,
        val turretsLost: Int,
        val objectivesStolen: Int,
        val objectivesStolenAssists: Int,
    )

    @JvmRecord
    public data class KillsStats(
        val firstBloodKill: Boolean,
        val firstBloodAssist: Boolean,
        val killingSprees: Int,
        val largestKillingSpree: Int,
        val largestMultiKill: Int,
        val unrealKills: Int,
        val kills: Int,
        val doubleKills: Int,
        val tripleKills: Int,
        val quadraKills: Int,
        val pentaKills: Int,
        val neutralMinionsKilled: Int,
        val totalAllyJungleMinionsKilled: Int,
        val totalEnemyJungleMinionsKilled: Int,
        val totalMinionsKilled: Int,
    ) {
        public val nonNeutralMinions: Int
            get() = totalMinionsKilled - neutralMinionsKilled

        public val totalJungleMinions: Int
            get() = totalAllyJungleMinionsKilled + totalEnemyJungleMinionsKilled
    }

    internal companion object {
        fun fromDto(dto: ParticipantDto): Participant {
            return Participant(
                participantId = dto.participantId,
                assists = dto.assists,
                deaths = dto.deaths,
                challenges = dto.challenges,
                mission = dto.missions,
                items = listOfNotNull(dto.item0, dto.item1, dto.item2, dto.item3, dto.item4, dto.item5, dto.item6),
                augmentsIds =
                    listOfNotNull(
                        dto.playerAugment1,
                        dto.playerAugment2,
                        dto.playerAugment3,
                        dto.playerAugment4,
                        dto.playerAugment5,
                    ),
                userData =
                    UserData(
                        profileIcon = dto.profileIcon,
                        riotIdGameName = dto.riotIdGameName,
                        riotIdTagLine = dto.riotIdTagline,
                        summonerName = dto.summonerName,
                        summonerLevel = dto.summonerLevel,
                    ),
                pings =
                    PingsStats(
                        allIn = dto.allInPings,
                        assistMe = dto.assistMePings,
                        commandPings = dto.commandPings,
                        enemyMissing = dto.enemyMissingPings,
                        holdOn = dto.holdPings,
                        getBack = dto.getBackPings,
                        needVision = dto.needVisionPings,
                        onMyWay = dto.onMyWayPings,
                        push = dto.pushPings,
                        visionCleared = dto.visionClearedPings,
                    ),
                castsStats =
                    CastsStats(
                        spell1Casts = dto.spell1Casts,
                        spell2Casts = dto.spell2Casts,
                        spell3Casts = dto.spell3Casts,
                        spell4Casts = dto.spell4Casts,
                        summoner1Casts = dto.summoner1Casts,
                        summoner2Casts = dto.summoner2Casts,
                    ),
                teamStats =
                    TeamStats(
                        gameEndedInSurrender = dto.gameEndedInSurrender,
                        gameEndedInEarlySurrender = dto.gameEndedInEarlySurrender,
                        win = dto.win,
                        teamId = TeamSide.fromValue(dto.teamId),
                        teamEarlySurrendered = dto.teamEarlySurrendered,
                    ),
                playerStats =
                    PlayerStats(
                        bountyLevel = dto.bountyLevel,
                        goldEarned = dto.goldEarned,
                        goldSpent = dto.goldSpent,
                        eligibleForProgression = dto.eligibleForProgression,
                        largestCriticalStrike = dto.largestCriticalStrike,
                        longestTimeSpentLiving = dto.longestTimeSpentLiving,
                        individualPosition = Position.fromValue(dto.individualPosition),
                        teamPosition = Position.fromValue(dto.teamPosition),
                        role = Role.fromValue(dto.role),
                        placement = dto.placement,
                        timeCCingOthers = dto.timeCCingOthers,
                        timePlayed = dto.timePlayed,
                        totalTimeSpentDead = dto.totalTimeSpentDead,
                        totalTimeCCDealt = dto.totalTimeCCDealt,
                        consumablesPurchased = dto.consumablesPurchased,
                        summoner1Id = dto.summoner1Id,
                        summoner2Id = dto.summoner2Id,
                    ),
                damagesStats =
                    DamagesStats(
                        physical =
                            DamagesStats.Damage(
                                dealt = dto.physicalDamageDealt,
                                dealtToChampions = dto.physicalDamageDealtToChampions,
                                taken = dto.physicalDamageTaken,
                            ),
                        magic =
                            DamagesStats.Damage(
                                dealt = dto.magicDamageDealt,
                                dealtToChampions = dto.magicDamageDealtToChampions,
                                taken = dto.magicDamageTaken,
                            ),
                        trueDmg =
                            DamagesStats.Damage(
                                dealt = dto.trueDamageDealt,
                                dealtToChampions = dto.trueDamageDealtToChampions,
                                taken = dto.trueDamageTaken,
                            ),
                        total =
                            DamagesStats.Damage(
                                dealt = dto.totalDamageDealt,
                                dealtToChampions = dto.totalDamageDealtToChampions,
                                taken = dto.totalDamageTaken,
                            ),
                        dealtToBuildings = dto.damageDealtToBuildings,
                        dealtToObjectives = dto.damageDealtToObjectives,
                        dealtToTurrets = dto.damageDealtToTurrets,
                        selfMitigated = dto.damageSelfMitigated,
                        totalShieldedOnTeammates = dto.totalDamageShieldedOnTeammates,
                        totalHealed = dto.totalHeal,
                        totalHealedOnTeammates = dto.totalHealsOnTeammates,
                        totalUnitsHealed = dto.totalUnitsHealed,
                    ),
                wardStats =
                    WardStats(
                        placed = dto.wardsPlaced,
                        destroyed = dto.wardsKilled,
                        detectorWardsPlaced = dto.detectorWardsPlaced,
                        sightWardsBought = dto.sightWardsBoughtInGame,
                        visionWardsBought = dto.visionWardsBoughtInGame,
                    ),
                championStats =
                    ChampionStats(
                        id = dto.championId,
                        level = dto.champLevel,
                        experience = dto.champExperience,
                        name = dto.championName,
                        transform = dto.championTransform,
                    ),
                objectivesStats =
                    ObjectivesStats(
                        firstTowerKill = dto.firstTowerKill,
                        firstTowerAssist = dto.firstTowerAssist,
                        inhibitorKills = dto.inhibitorKills,
                        inhibitorTakedowns = dto.inhibitorTakedowns,
                        inhibitorsLost = dto.inhibitorsLost,
                        baronKills = dto.baronKills,
                        dragonKills = dto.dragonKills,
                        nexusKills = dto.nexusKills,
                        nexusTakedowns = dto.nexusTakedowns,
                        turretKills = dto.turretKills,
                        turretsLost = dto.turretsLost,
                        objectivesStolen = dto.objectivesStolen,
                        objectivesStolenAssists = dto.objectivesStolenAssists,
                    ),
                killsStats =
                    KillsStats(
                        firstBloodKill = dto.firstBloodKill,
                        firstBloodAssist = dto.firstBloodAssist,
                        killingSprees = dto.killingSprees,
                        largestKillingSpree = dto.largestKillingSpree,
                        largestMultiKill = dto.largestMultiKill,
                        unrealKills = dto.unrealKills,
                        kills = dto.kills,
                        doubleKills = dto.doubleKills,
                        tripleKills = dto.tripleKills,
                        quadraKills = dto.quadraKills,
                        pentaKills = dto.pentaKills,
                        neutralMinionsKilled = dto.neutralMinionsKilled,
                        totalAllyJungleMinionsKilled = dto.totalAllyJungleMinionsKilled,
                        totalEnemyJungleMinionsKilled = dto.totalEnemyJungleMinionsKilled,
                        totalMinionsKilled = dto.totalMinionsKilled,
                    ),
            )
        }
    }
}

public data class Info(
    val endOfGameResult: String,
    /**
     * The timestamp of the game creation.
     */
    val gameCreation: Long,
    /**
     * The duration of the game in milliseconds.
     */
    val gameDuration: Long,
    /**
     * The timestamp of the game end.
     */
    val gameEndTimestamp: Long,
    val gameId: Long,
    val gameMode: String,
    val gameName: String,
    val gameStartTimestamp: Long,
    val gameType: String,
    val gameVersion: String,
    val mapId: Int,
    val participants: List<Participant>,
    val platformId: String,
    val queueId: Int,
    val teams: List<Team>,
    val tournamentCode: String,
) {
    internal companion object {
        fun fromDto(dto: InfoDto): Info {
            return Info(
                endOfGameResult = dto.endOfGameResult,
                gameCreation = dto.gameCreation,
                gameDuration = dto.gameDuration,
                gameEndTimestamp = dto.gameEndTimestamp,
                gameId = dto.gameId,
                gameMode = dto.gameMode,
                gameName = dto.gameName,
                gameStartTimestamp = dto.gameStartTimestamp,
                gameType = dto.gameType,
                gameVersion = dto.gameVersion,
                mapId = dto.mapId,
                participants = dto.participants.map { Participant.fromDto(it) },
                platformId = dto.platformId,
                queueId = dto.queueId,
                teams = dto.teams.map { Team.fromDto(it) },
                tournamentCode = dto.tournamentCode,
            )
        }
    }
}

public data class Match(
    val metadata: Metadata,
    val info: Info,
) {
    internal companion object {
        fun fromDto(dto: MatchDto): Match {
            return Match(
                metadata = Metadata.fromDto(dto.metadata),
                info = Info.fromDto(dto.info),
            )
        }
    }
}
