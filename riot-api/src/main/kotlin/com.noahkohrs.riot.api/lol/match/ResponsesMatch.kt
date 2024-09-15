package com.noahkohrs.riot.api.lol.match

import com.noahkohrs.riot.api.annotations.LinkToStaticApi
import com.noahkohrs.riot.api.dtos.*
import com.noahkohrs.riot.api.values.Position
import com.noahkohrs.riot.api.values.Role

public class Metadata private constructor(
    @JvmField public val dataVersion: String,
    @JvmField public val matchId: String,
    @JvmField public val participantsPuuids: List<String>,
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

public class Objective private constructor(
    @JvmField public val first: Boolean,
    @JvmField public val kills: Int,
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

public class Objectives private constructor(
    @JvmField public val baron: Objective,
    @JvmField public val champion: Objective,
    @JvmField public val dragon: Objective,
    @JvmField public val inhibitor: Objective,
    @JvmField public val riftHerald: Objective,
    @JvmField public val tower: Objective,
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

public class Ban private constructor(
    @JvmField public val championId: Int,
    @JvmField public val pickTurn: Int,
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

public class Team private constructor(
    @JvmField public val bans: List<Ban>,
    @JvmField public val objectives: Objectives,
    @JvmField public val teamId: Int,
    @JvmField public val win: Boolean,
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

public class Mission private constructor(
    @JvmField public val playerScore0: Int,
    @JvmField public val playerScore1: Int,
    @JvmField public val playerScore2: Int,
    @JvmField public val playerScore3: Int,
    @JvmField public val playerScore4: Int,
    @JvmField public val playerScore5: Int,
    @JvmField public val playerScore6: Int,
    @JvmField public val playerScore7: Int,
    @JvmField public val playerScore8: Int,
    @JvmField public val playerScore9: Int,
    @JvmField public val playerScore10: Int,
    @JvmField public val playerScore11: Int,
) {
    internal companion object {
        fun fromDto(dto: MissionDto): Mission {
            return Mission(
                playerScore0 = dto.playerScore0,
                playerScore1 = dto.playerScore1,
                playerScore2 = dto.playerScore2,
                playerScore3 = dto.playerScore3,
                playerScore4 = dto.playerScore4,
                playerScore5 = dto.playerScore5,
                playerScore6 = dto.playerScore6,
                playerScore7 = dto.playerScore7,
                playerScore8 = dto.playerScore8,
                playerScore9 = dto.playerScore9,
                playerScore10 = dto.playerScore10,
                playerScore11 = dto.playerScore11,
            )
        }
    }
}

public class Participant private constructor(
    @JvmField public val participantId: Int,
    // kills obtained from kills stats.
    @JvmField public val assists: Int,
    @JvmField public val deaths: Int,
    @JvmField public val challenges: ChallengesDto,
    @JvmField public val mission: Mission,
    @LinkToStaticApi
    @JvmField public val items: List<Int>,
    @LinkToStaticApi
    @JvmField public val augmentsIds: List<Int>,
    @JvmField public val userData: UserData,
    @JvmField public val pings: PingsStats,
    @JvmField public val castsStats: CastsStats,
    @JvmField public val teamStats: TeamStats,
    @JvmField public val playerStats: PlayerStats,
    @JvmField public val damagesStats: DamagesStats,
    @JvmField public val wardStats: WardStats,
    @JvmField public val championStats: ChampionStats,
    @JvmField public val objectivesStats: ObjectivesStats,
    @JvmField public val killsStats: KillsStats,
) {
    /**
     * Player's kills
     */
    public val kills: Int
        get() = killsStats.kills

    public class UserData(
        @JvmField public val profileIcon: Int,
        @JvmField public val riotIdGameName: String,
        @JvmField public val riotIdTagLine: String,
        @JvmField public val summonerName: String,
        @JvmField public val summonerLevel: Int,
    )

    public class PingsStats(
        /**
         * Yellow crossed swords
         */
        @JvmField public val allIn: Int,
        /**
         * Green flag
         */
        @JvmField public val assistMe: Int,
        /**
         * Blue generic ping
         */
        @JvmField public val commandPings: Int,
        /**
         * Yellow question mark
         */
        @JvmField public val enemyMissing: Int,
        @JvmField public val holdOn: Int,
        @JvmField public val getBack: Int,
        /**
         * Green ward
         */
        @JvmField public val needVision: Int,
        @JvmField public val onMyWay: Int,
        @JvmField public val push: Int,
        @JvmField public val visionCleared: Int,
    )

    public class TeamStats(
        @JvmField public val gameEndedInSurrender: Boolean,
        @JvmField public val gameEndedInEarlySurrender: Boolean,
        @JvmField public val win: Boolean,
        @JvmField public val teamId: Long,
        @JvmField public val teamEarlySurrendered: Boolean,
    )

    public class PlayerStats(
        @JvmField public val bountyLevel: Int,
        @JvmField public val goldEarned: Int,
        @JvmField public val goldSpent: Int,
        @JvmField public val eligibleForProgression: Boolean,
        @JvmField public val largestCriticalStrike: Int,
        @JvmField public val longestTimeSpentLiving: Int,
        /**
         * Both `individualPosition` and `teamPosition` are the most likely position for a player, done by different calculations.
         */
        @JvmField public val individualPosition: Position,
        /**
         * Both `individualPosition` and `teamPosition` are the most likely position for a player, done by different calculations.
         */
        @JvmField public val teamPosition: Position,
        @JvmField public val role: Role,
        @JvmField public val placement: Int,
        @JvmField public val timeCCingOthers: Int,
        @JvmField public val timePlayed: Int,
        @JvmField public val totalTimeSpentDead: Int,
        @JvmField public val totalTimeCCDealt: Int,
        @JvmField public val consumablesPurchased: Int,
        @LinkToStaticApi
        @JvmField public val summoner1Id: Int,
        @LinkToStaticApi
        @JvmField public val summoner2Id: Int,
    )

    /**
     * Number of each spell and summoner cast.
     */
    public class CastsStats(
        @JvmField public val spell1Casts: Int,
        @JvmField public val spell2Casts: Int,
        @JvmField public val spell3Casts: Int,
        @JvmField public val spell4Casts: Int,
        @JvmField public val summoner1Casts: Int,
        @JvmField public val summoner2Casts: Int,
    )

    public class DamagesStats(
        @JvmField public val physical: Damage,
        @JvmField public val magic: Damage,
        @JvmField public val trueDmg: Damage,
        @JvmField public val total: Damage,
        @JvmField public val dealtToBuildings: Int,
        @JvmField public val dealtToObjectives: Int,
        @JvmField public val dealtToTurrets: Int,
        @JvmField public val selfMitigated: Int,
        @JvmField public val totalShieldedOnTeammates: Int,
        @JvmField public val totalHealed: Int,
        @JvmField public val totalHealedOnTeammates: Int,
        @JvmField public val totalUnitsHealed: Int,
    ) {
        public class Damage(
            @JvmField public val dealt: Int,
            @JvmField public val dealtToChampions: Int,
            @JvmField public val taken: Int,
        )

        public val totalHealedOnSelf: Int
            get() = totalHealed - totalHealedOnTeammates
    }

    public class WardStats(
        @JvmField public val placed: Int,
        @JvmField public val destroyed: Int,
        @JvmField public val detectorWardsPlaced: Int,
        @JvmField public val sightWardsBought: Int,
        @JvmField public val visionWardsBought: Int,
    )

    public class ChampionStats(
        @JvmField public val id: Int,
        @JvmField public val level: Int,
        @JvmField public val experience: Int,
        @JvmField public val name: String,
        /**
         * This is only used by Kayn for now.
         */
        @JvmField public val transform: Int,
    )

    public class ObjectivesStats(
        @JvmField public val firstTowerKill: Boolean,
        @JvmField public val firstTowerAssist: Boolean,
        @JvmField public val inhibitorKills: Int,
        @JvmField public val inhibitorTakedowns: Int,
        @JvmField public val inhibitorsLost: Int,
        @JvmField public val baronKills: Int,
        @JvmField public val dragonKills: Int,
        @JvmField public val nexusKills: Int,
        @JvmField public val nexusTakedowns: Int,
        @JvmField public val turretKills: Int,
        @JvmField public val turretsLost: Int,
        @JvmField public val objectivesStolen: Int,
        @JvmField public val objectivesStolenAssists: Int,
    )

    public class KillsStats(
        @JvmField public val firstBloodKill: Boolean,
        @JvmField public val firstBloodAssist: Boolean,
        @JvmField public val killingSprees: Int,
        @JvmField public val largestKillingSpree: Int,
        @JvmField public val largestMultiKill: Int,
        @JvmField public val unrealKills: Int,
        @JvmField public val kills: Int,
        @JvmField public val doubleKills: Int,
        @JvmField public val tripleKills: Int,
        @JvmField public val quadraKills: Int,
        @JvmField public val pentaKills: Int,
        @JvmField public val neutralMinionsKilled: Int,
        @JvmField public val totalAllyJungleMinionsKilled: Int,
        @JvmField public val totalEnemyJungleMinionsKilled: Int,
        @JvmField public val totalMinionsKilled: Int,
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
                mission = Mission.fromDto(dto.missions),
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
                        teamId = dto.teamId,
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

public class Info private constructor(
    @JvmField public val endOfGameResult: String,
    /**
     * The timestamp of the game creation.
     */
    @JvmField public val gameCreation: Long,
    /**
     * The duration of the game in milliseconds.
     */
    @JvmField public val gameDuration: Long,
    /**
     * The timestamp of the game end.
     */
    @JvmField public val gameEndTimestamp: Long,
    @JvmField public val gameId: Long,
    @JvmField public val gameMode: String,
    @JvmField public val gameName: String,
    @JvmField public val gameStartTimestamp: Long,
    @JvmField public val gameType: String,
    @JvmField public val gameVersion: String,
    @JvmField public val mapId: Int,
    @JvmField public val participants: List<Participant>,
    @JvmField public val platformId: String,
    @JvmField public val queueId: Int,
    @JvmField public val teams: List<Team>,
    @JvmField public val tournamentCode: String,
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

public class Match private constructor(
    @JvmField public val metadata: Metadata,
    @JvmField public val info: Info,
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
