package com.noahkohrs.riot.api.lol.match

import com.noahkohrs.riot.api.annotations.UnstableApi
import com.noahkohrs.riot.api.dtos.*
import com.noahkohrs.riot.api.manipulation.JsonObject
import com.noahkohrs.riot.api.values.BuildingType
import com.noahkohrs.riot.api.values.LaneType
import com.noahkohrs.riot.api.values.TowerType
import org.jetbrains.annotations.Nullable

public class Position(
    @JvmField public val x: Int,
    @JvmField public val y: Int,
) {
    internal companion object {
        fun fromDto(dto: PositionDto): Position {
            return Position(dto.x, dto.y)
        }
    }
}

public class DamageStat(
    @JvmField public val dealt: Int,
    @JvmField public val dealtToChampions: Int,
    @JvmField public val taken: Int,
)

public class DamageStats private constructor(
    @JvmField public val magicDamage: DamageStat,
    @JvmField public val physicalDamage: DamageStat,
    @JvmField public val trueDamage: DamageStat,
    @JvmField public val totalDamage: DamageStat,
) {
    internal companion object {
        fun fromDto(dto: DamageStatsDto): DamageStats {
            return DamageStats(
                magicDamage =
                    DamageStat(
                        dto.magicDamageDone,
                        dto.magicDamageDoneToChampions,
                        dto.magicDamageTaken,
                    ),
                physicalDamage =
                    DamageStat(
                        dto.physicalDamageDone,
                        dto.physicalDamageDoneToChampions,
                        dto.physicalDamageTaken,
                    ),
                trueDamage =
                    DamageStat(
                        dto.trueDamageDone,
                        dto.trueDamageDoneToChampions,
                        dto.trueDamageTaken,
                    ),
                totalDamage =
                    DamageStat(
                        dto.totalDamageDone,
                        dto.totalDamageDoneToChampions,
                        dto.totalDamageTaken,
                    ),
            )
        }
    }
}

/**
 * Might need to be sorted by category later on.
 */
@UnstableApi
public class ChampionStats private constructor(
    @JvmField public val abilityHaste: Int = 0,
    @JvmField public val abilityPower: Int = 0,
    @JvmField public val armor: Int = 0,
    @JvmField public val armorPen: Int = 0,
    @JvmField public val armorPenPercent: Int = 0,
    @JvmField public val attackDamage: Int = 0,
    @JvmField public val attackSpeed: Int = 0,
    @JvmField public val bonusArmorPenPercent: Int = 0,
    @JvmField public val bonusMagicPenPercent: Int = 0,
    @JvmField public val ccReduction: Int = 0,
    @JvmField public val cooldownReduction: Int = 0,
    @JvmField public val health: Int = 0,
    @JvmField public val healthMax: Int = 0,
    @JvmField public val healthRegen: Int = 0,
    @JvmField public val lifesteal: Int = 0,
    @JvmField public val magicPen: Int = 0,
    @JvmField public val magicPenPercent: Int = 0,
    @JvmField public val magicResist: Int = 0,
    @JvmField public val movementSpeed: Int = 0,
    @JvmField public val omnivamp: Int = 0,
    @JvmField public val physicalVamp: Int = 0,
    @JvmField public val power: Int = 0,
    @JvmField public val powerMax: Int = 0,
    @JvmField public val powerRegen: Int = 0,
    @JvmField public val spellVamp: Int = 0,
) {
    internal companion object {
        fun fromDto(dto: ChampionStatsDto): ChampionStats {
            return ChampionStats(
                abilityHaste = dto.abilityHaste ?: 0,
                abilityPower = dto.abilityPower ?: 0,
                armor = dto.armor ?: 0,
                armorPen = dto.armorPen ?: 0,
                armorPenPercent = dto.armorPenPercent ?: 0,
                attackDamage = dto.attackDamage ?: 0,
                attackSpeed = dto.attackSpeed ?: 0,
                bonusArmorPenPercent = dto.bonusArmorPenPercent ?: 0,
                bonusMagicPenPercent = dto.bonusMagicPenPercent ?: 0,
                ccReduction = dto.ccReduction ?: 0,
                cooldownReduction = dto.cooldownReduction ?: 0,
                health = dto.health ?: 0,
                healthMax = dto.healthMax ?: 0,
                healthRegen = dto.healthRegen ?: 0,
                lifesteal = dto.lifesteal ?: 0,
                magicPen = dto.magicPen ?: 0,
                magicPenPercent = dto.magicPenPercent ?: 0,
                magicResist = dto.magicResist ?: 0,
                movementSpeed = dto.movementSpeed ?: 0,
                omnivamp = dto.omnivamp ?: 0,
                physicalVamp = dto.physicalVamp ?: 0,
                power = dto.power ?: 0,
                powerMax = dto.powerMax ?: 0,
                powerRegen = dto.powerRegen ?: 0,
                spellVamp = dto.spellVamp ?: 0,
            )
        }
    }
}

public class ParticipantFrame private constructor(
    @JvmField public val championStats: ChampionStats,
    @JvmField public val damageStats: DamageStats,
    @JvmField public val gold: Int,
    @JvmField public val jungleMinionsKilled: Int,
    @JvmField public val level: Int,
    @JvmField public val minionsKilled: Int,
    @JvmField public val position: Position,
    @JvmField public val timeEnemySpentControlled: Int,
    @JvmField public val totalGold: Int,
    @JvmField public val xp: Int,
) {
    internal companion object {
        fun fromDto(dto: ParticipantFrameDto): ParticipantFrame {
            return ParticipantFrame(
                championStats = ChampionStats.fromDto(dto.championStats),
                damageStats = DamageStats.fromDto(dto.damageStats),
                gold = dto.currentGold,
                jungleMinionsKilled = dto.jungleMinionsKilled,
                level = dto.level,
                minionsKilled = dto.minionsKilled,
                position = Position.fromDto(dto.position),
                timeEnemySpentControlled = dto.timeEnemySpentControlled,
                totalGold = dto.totalGold,
                xp = dto.xp,
            )
        }
    }
}

public enum class TimelineEventType {
    // Type: PAUSE_END, Champs: [realTimestamp]
    PAUSE_END,

    // Type: PAUSE_START, Champs: [realTimestamp]
    PAUSE_START,

    // Type: LEVEL_UP, Champs: [level, participantId]
    LEVEL_UP,

    // Type: WARD_PLACED, Champs: [creatorId, wardType]
    WARD_PLACED,

    // Type: CHAMPION_KILL, Champs: [bounty, killStreakLength, killerId, position, shutdownBounty, victimId, victimDamageReceived, victimDamageDealt, assistingParticipantIds]
    CHAMPION_KILL,

    // Type: ITEM_DESTROYED, Champs: [itemId, participantId]
    ITEM_DESTROYED,

    // Type: GAME_END, Champs: [gameId, realTimestamp, winningTeam]
    GAME_END,

    // Type: SKILL_LEVEL_UP, Champs: [levelUpType, participantId, skillSlot]
    SKILL_LEVEL_UP,

    // Type: ITEM_PURCHASED, Champs: [itemId, participantId]
    ITEM_PURCHASED,

    // Type: CHAMPION_SPECIAL_KILL, Champs: [killType, killerId, position, multiKillLength]
    CHAMPION_SPECIAL_KILL,

    // Type: ITEM_SOLD, Champs: [itemId, participantId]
    ITEM_SOLD,

    // Type: ITEM_UNDO, Champs: [afterId, beforeId, goldGain, participantId]
    ITEM_UNDO,

    // Type: TURRET_PLATE_DESTROYED, Champs: [killerId, laneType, position, teamId]
    TURRET_PLATE_DESTROYED,

    // Type: ELITE_MONSTER_KILL, Champs: [bounty, killerId, killerTeamId, monsterSubType, monsterType, position, assistingParticipantIds]
    ELITE_MONSTER_KILL,

    // Type: WARD_KILL, Champs: [killerId, wardType]
    WARD_KILL,

    // Type: BUILDING_KILL, Champs: [bounty, buildingType, killerId, laneType, position, teamId, towerType, assistingParticipantIds]
    BUILDING_KILL,

    // Type: DRAGON_SOUL_GIVEN, Champs: [name, teamId]
    DRAGON_SOUL_GIVEN,

    // Type: OBJECTIVE_BOUNTY_PRESTART, Champs: [actualStartTime, teamId]
    OBJECTIVE_BOUNTY_PRESTART,

    // Type: OBJECTIVE_BOUNTY_FINISH, Champs: [teamId]
    OBJECTIVE_BOUNTY_FINISH,

    // Type: CHAMPION_TRANSFORM, Champs: [participantId, transformType]
    CHAMPION_TRANSFORM,
    UNKNOWN,
}

public abstract class TimelineEvent(
    @JvmField
    public val type: TimelineEventType,
    public open val timestamp: Long,
) {
    // Type: PAUSE_END, Champs: [realTimestamp]
    public class PauseEnd(
        public override val timestamp: Long,
        @JvmField public val realTimestamp: Long,
    ) : TimelineEvent(TimelineEventType.PAUSE_END, timestamp)

    // Type: PAUSE_START, Champs: [realTimestamp]
    public class PauseStart(
        public override val timestamp: Long,
        @JvmField public val realTimestamp: Long,
    ) : TimelineEvent(TimelineEventType.PAUSE_START, timestamp)

    // Type: LEVEL_UP, Champs: [level, participantId]
    public class LevelUp(
        public override val timestamp: Long,
        @JvmField public val level: Int,
        @JvmField public val participantId: Int,
    ) : TimelineEvent(TimelineEventType.LEVEL_UP, timestamp)

    // Type: WARD_PLACED, Champs: [creatorId, wardType]
    public class WardPlaced(
        public override val timestamp: Long,
        @JvmField public val creatorId: Int,
        @JvmField public val wardType: String,
    ) : TimelineEvent(TimelineEventType.WARD_PLACED, timestamp)

    // Type: CHAMPION_KILL, Champs: [bounty, killStreakLength, killerId, position, shutdownBounty, victimId, victimDamageReceived, victimDamageDealt, assistingParticipantIds]
    public class ChampionKill(
        public override val timestamp: Long,
        @JvmField public val bounty: Int,
        @JvmField public val killStreakLength: Int,
        @JvmField public val killerId: Int,
        @JvmField public val position: Position,
        @JvmField public val shutdownBounty: Int,
        @JvmField public val victimId: Int,
        @JvmField public val victimDamageReceived: List<VictimDamage>,
        @JvmField public val victimDamageDealt: List<VictimDamage>,
        @JvmField public val assistingParticipantIds: List<Int>,
    ) : TimelineEvent(TimelineEventType.CHAMPION_KILL, timestamp) {
        public class VictimDamage(
            @JvmField public val basic: Boolean,
            @JvmField public val magicDamage: Int,
            @JvmField public val name: String,
            @JvmField public val participantId: Int,
            @JvmField public val physicalDamage: Int,
            @JvmField public val spellName: String,
            @JvmField public val spellSlot: Int,
            @JvmField public val trueDamage: Int,
            @JvmField public val type: String,
        )
    }

    // Type: ITEM_DESTROYED, Champs: [itemId, participantId]
    public class ItemDestroyed(
        public override val timestamp: Long,
        @JvmField public val itemId: Int,
        @JvmField public val participantId: Int,
    ) : TimelineEvent(TimelineEventType.ITEM_DESTROYED, timestamp)

    // Type: GAME_END, Champs: [gameId, realTimestamp, winningTeam]
    public class GameEnd(
        public override val timestamp: Long,
        @JvmField public val gameId: Long,
        @JvmField public val realTimestamp: Long,
        @JvmField public val winningTeam: Long,
    ) : TimelineEvent(TimelineEventType.GAME_END, timestamp)

    // Type: SKILL_LEVEL_UP, Champs: [levelUpType, participantId, skillSlot]
    public class SkillLevelUp(
        public override val timestamp: Long,
        @JvmField public val levelUpType: String,
        @JvmField public val participantId: Int,
        @JvmField public val skillSlot: Int,
    ) : TimelineEvent(TimelineEventType.SKILL_LEVEL_UP, timestamp)

    // Type: ITEM_PURCHASED, Champs: [itemId, participantId]
    public class ItemPurchased(
        public override val timestamp: Long,
        @JvmField public val itemId: Int,
        @JvmField public val participantId: Int,
    ) : TimelineEvent(TimelineEventType.ITEM_PURCHASED, timestamp)

    // Type: CHAMPION_SPECIAL_KILL, Champs: [killType, killerId, position, multiKillLength]
    public class ChampionSpecialKill(
        public override val timestamp: Long,
        @JvmField public val killType: String,
        @JvmField public val killerId: Int,
        @JvmField public val position: Position,
        @JvmField public val multiKillLength: Int,
    ) : TimelineEvent(TimelineEventType.CHAMPION_SPECIAL_KILL, timestamp)

    // Type: ITEM_SOLD, Champs: [itemId, participantId]
    public class ItemSold(
        public override val timestamp: Long,
        @JvmField public val itemId: Int,
        @JvmField public val participantId: Int,
    ) : TimelineEvent(TimelineEventType.ITEM_SOLD, timestamp)

    // Type: ITEM_UNDO, Champs: [afterId, beforeId, goldGain, participantId]
    public class ItemUndo(
        public override val timestamp: Long,
        @JvmField public val afterId: Int,
        @JvmField public val beforeId: Int,
        @JvmField public val goldGain: Int,
        @JvmField public val participantId: Int,
    ) : TimelineEvent(TimelineEventType.ITEM_UNDO, timestamp)

    // Type: TURRET_PLATE_DESTROYED, Champs: [killerId, laneType, position, teamId]
    public class TurretPlateDestroyed(
        public override val timestamp: Long,
        @JvmField public val killerId: Int,
        @JvmField public val laneType: LaneType,
        @JvmField public val position: Position,
        public val teamId: Long,
    ) : TimelineEvent(TimelineEventType.TURRET_PLATE_DESTROYED, timestamp)

    // Type: ELITE_MONSTER_KILL, Champs: [bounty, killerId, killerTeamId, monsterSubType, monsterType, position, assistingParticipantIds]
    @UnstableApi
    public class EliteMonsterKill(
        public override val timestamp: Long,
        @JvmField public val bounty: Int,
        @JvmField public val killerId: Int,
        @JvmField public val killerTeamId: Long,
        @JvmField public val monsterSubType: String,
        @JvmField public val monsterType: String,
        @JvmField public val position: Position,
        @JvmField public val assistingParticipantIds: List<Int>,
    ) : TimelineEvent(TimelineEventType.ELITE_MONSTER_KILL, timestamp)

    // Type: WARD_KILL, Champs: [killerId, wardType]
    public class WardKill(
        public override val timestamp: Long,
        public val killerId: Int,
        public val wardType: WardType,
    ) : TimelineEvent(TimelineEventType.WARD_KILL, timestamp) {
        public enum class WardType {
            YELLOW_TRINKET,
            CONTROL_WARD,
            SIGHT_WARD,
            UNKNOWN,
            ;

            internal companion object {
                fun fromValue(str: String): WardType {
                    return when (str) {
                        "YELLOW_TRINKET" -> YELLOW_TRINKET
                        "CONTROL_WARD" -> CONTROL_WARD
                        "SIGHT_WARD" -> SIGHT_WARD
                        else -> UNKNOWN
                    }
                }
            }
        }
    }

    // Type: BUILDING_KILL, Champs: [bounty, buildingType, killerId, laneType, position, teamId, towerType, assistingParticipantIds]
    @UnstableApi
    public class BuildingKill(
        public override val timestamp: Long,
        @JvmField public val bounty: Int,
        @JvmField public val buildingType: BuildingType,
        @JvmField public val killerId: Int,
        @JvmField public val laneType: LaneType,
        @JvmField public val position: Position,
        @JvmField public val team: Long,
        @Nullable
        @JvmField public val towerType: TowerType?,
        @JvmField public val assistingParticipantIds: List<Int>,
    ) : TimelineEvent(TimelineEventType.BUILDING_KILL, timestamp)

    // Type: DRAGON_SOUL_GIVEN, Champs: [name, teamId]
    public class DragonSoulGiven(
        public override val timestamp: Long,
        @JvmField public val name: String,
        @JvmField public val team: Long,
    ) : TimelineEvent(TimelineEventType.DRAGON_SOUL_GIVEN, timestamp)

    // Type: OBJECTIVE_BOUNTY_PRESTART, Champs: [actualStartTime, teamId]
    public class ObjectiveBountyPrestart(
        public override val timestamp: Long,
        public val actualStartTime: Long,
        @JvmField public val team: Long,
    ) : TimelineEvent(TimelineEventType.OBJECTIVE_BOUNTY_PRESTART, timestamp)

    // Type: OBJECTIVE_BOUNTY_FINISH, Champs: [teamId]
    public class ObjectiveBountyFinish(
        public override val timestamp: Long,
        @JvmField public val team: Long,
    ) : TimelineEvent(TimelineEventType.OBJECTIVE_BOUNTY_FINISH, timestamp)

    // Type: CHAMPION_TRANSFORM, Champs: [participantId, transformType]
    public class ChampionTransform(
        public override val timestamp: Long,
        @JvmField public val participantId: Int,
        @JvmField public val transformType: String,
    ) : TimelineEvent(TimelineEventType.CHAMPION_TRANSFORM, timestamp)

    // UNKNOWN

    /**
     * In best bases, you should not happen to use this class.
     *
     * If you run into it, we recommend considering contributing to the project on Github, either by adding the missing event, or just opening a ticket including a matchId to reproduce the behaviour.
     */
    @UnstableApi
    public class Unknown(
        public override val timestamp: Long,
        /**
         * > Warning: This JsonObject will also contain timestamp and type, be aware while manipulating.
         */
        @JvmField public val lostProperties: JsonObject,
    ) : TimelineEvent(TimelineEventType.UNKNOWN, timestamp)

    internal companion object {
        fun fromDto(dto: EventTimelineDto): TimelineEvent {
            return when (dto.type) {
                "PAUSE_END" -> {
                    val realTimestamp = dto.additionalData.getLong("realTimestamp") ?: 0
                    PauseEnd(dto.timestamp, realTimestamp)
                }

                "PAUSE_START" -> {
                    val realTimestamp = dto.additionalData.getLong("realTimestamp") ?: 0
                    PauseStart(dto.timestamp, realTimestamp)
                }

                "LEVEL_UP" -> {
                    val level = dto.additionalData.getInt("level") ?: 0
                    val participantId = dto.additionalData.getInt("participantId") ?: 0
                    LevelUp(dto.timestamp, level, participantId)
                }

                "WARD_PLACED" -> {
                    val creatorId = dto.additionalData.getInt("creatorId") ?: 0
                    val wardType = dto.additionalData.getString("wardType") ?: ""
                    WardPlaced(dto.timestamp, creatorId, wardType)
                }

                "CHAMPION_KILL" -> {
                    val bounty = dto.additionalData.getInt("bounty") ?: 0
                    val killStreakLength = dto.additionalData.getInt("killStreakLength") ?: 0
                    val killerId = dto.additionalData.getInt("killerId") ?: 0
                    val position =
                        Position(
                            dto.additionalData.getInt("positionX") ?: 0,
                            dto.additionalData.getInt("positionY") ?: 0,
                        )
                    val shutdownBounty = dto.additionalData.getInt("shutdownBounty") ?: 0
                    val victimId = dto.additionalData.getInt("victimId") ?: 0
                    val victimDamageReceived =
                        dto.additionalData.getArray<JsonObject>("victimDamageReceived")?.map {
                            ChampionKill.VictimDamage(
                                it.getBoolean("basic") ?: false,
                                it.getInt("magicDamage") ?: 0,
                                it.getString("name") ?: "",
                                it.getInt("participantId") ?: 0,
                                it.getInt("physicalDamage") ?: 0,
                                it.getString("spellName") ?: "",
                                it.getInt("spellSlot") ?: 0,
                                it.getInt("trueDamage") ?: 0,
                                it.getString("type") ?: "",
                            )
                        } ?: emptyList()
                    val victimDamageDealt =
                        dto.additionalData.getArray<JsonObject>("victimDamageDealt")?.map {
                            ChampionKill.VictimDamage(
                                it.getBoolean("basic") ?: false,
                                it.getInt("magicDamage") ?: 0,
                                it.getString("name") ?: "",
                                it.getInt("participantId") ?: 0,
                                it.getInt("physicalDamage") ?: 0,
                                it.getString("spellName") ?: "",
                                it.getInt("spellSlot") ?: 0,
                                it.getInt("trueDamage") ?: 0,
                                it.getString("type") ?: "",
                            )
                        } ?: emptyList()
                    val assistingParticipantIds =
                        dto.additionalData.getArray<Int>("assistingParticipantIds") ?: emptyList()
                    ChampionKill(
                        dto.timestamp,
                        bounty,
                        killStreakLength,
                        killerId,
                        position,
                        shutdownBounty,
                        victimId,
                        victimDamageReceived,
                        victimDamageDealt,
                        assistingParticipantIds,
                    )
                }

                "ITEM_DESTROYED" -> {
                    val itemId = dto.additionalData.getInt("itemId") ?: 0
                    val participantId = dto.additionalData.getInt("participantId") ?: 0
                    ItemDestroyed(dto.timestamp, itemId, participantId)
                }

                "GAME_END" -> {
                    val gameId = dto.additionalData.getLong("gameId") ?: 0
                    val realTimestamp = dto.additionalData.getLong("realTimestamp") ?: 0
                    val winningTeam = dto.additionalData.getLong("winningTeam") ?: 0
                    GameEnd(dto.timestamp, gameId, realTimestamp, winningTeam)
                }

                "SKILL_LEVEL_UP" -> {
                    val levelUpType = dto.additionalData.getString("levelUpType") ?: ""
                    val participantId = dto.additionalData.getInt("participantId") ?: 0
                    val skillSlot = dto.additionalData.getInt("skillSlot") ?: 0
                    SkillLevelUp(dto.timestamp, levelUpType, participantId, skillSlot)
                }

                "ITEM_PURCHASED" -> {
                    val itemId = dto.additionalData.getInt("itemId") ?: 0
                    val participantId = dto.additionalData.getInt("participantId") ?: 0
                    ItemPurchased(dto.timestamp, itemId, participantId)
                }

                "CHAMPION_SPECIAL_KILL" -> {
                    val killType = dto.additionalData.getString("killType") ?: ""
                    val killerId = dto.additionalData.getInt("killerId") ?: 0
                    val position =
                        Position(
                            dto.additionalData.getInt("positionX") ?: 0,
                            dto.additionalData.getInt("positionY") ?: 0,
                        )
                    val multiKillLength = dto.additionalData.getInt("multiKillLength") ?: 0
                    ChampionSpecialKill(dto.timestamp, killType, killerId, position, multiKillLength)
                }

                "ITEM_SOLD" -> {
                    val itemId = dto.additionalData.getInt("itemId") ?: 0
                    val participantId = dto.additionalData.getInt("participantId") ?: 0
                    ItemSold(dto.timestamp, itemId, participantId)
                }

                "ITEM_UNDO" -> {
                    val afterId = dto.additionalData.getInt("afterId") ?: 0
                    val beforeId = dto.additionalData.getInt("beforeId") ?: 0
                    val goldGain = dto.additionalData.getInt("goldGain") ?: 0
                    val participantId = dto.additionalData.getInt("participantId") ?: 0
                    ItemUndo(dto.timestamp, afterId, beforeId, goldGain, participantId)
                }

                "TURRET_PLATE_DESTROYED" -> {
                    val killerId = dto.additionalData.getInt("killerId") ?: 0
                    val laneType = LaneType.fromValue(dto.additionalData.getString("laneType") ?: "")
                    val position =
                        Position(
                            dto.additionalData.getInt("positionX") ?: 0,
                            dto.additionalData.getInt("positionY") ?: 0,
                        )
                    val teamId = dto.additionalData.getLong("teamId") ?: 0
                    TurretPlateDestroyed(dto.timestamp, killerId, laneType, position, teamId)
                }

                "ELITE_MONSTER_KILL" -> {
                    val bounty = dto.additionalData.getInt("bounty") ?: 0
                    val killerId = dto.additionalData.getInt("killerId") ?: 0
                    val killerTeamId = dto.additionalData.getLong("killerTeamId") ?: 0
                    val monsterSubType = dto.additionalData.getString("monsterSubType") ?: ""
                    val monsterType = dto.additionalData.getString("monsterType") ?: ""
                    val position =
                        Position(
                            dto.additionalData.getInt("positionX") ?: 0,
                            dto.additionalData.getInt("positionY") ?: 0,
                        )
                    val assistingParticipantIds =
                        dto.additionalData.getArray<Int>("assistingParticipantIds") ?: emptyList()
                    EliteMonsterKill(
                        dto.timestamp,
                        bounty,
                        killerId,
                        killerTeamId,
                        monsterSubType,
                        monsterType,
                        position,
                        assistingParticipantIds,
                    )
                }

                "WARD_KILL" -> {
                    val killerId = dto.additionalData.getInt("killerId") ?: 0
                    val wardType = WardKill.WardType.fromValue(dto.additionalData.getString("wardType") ?: "")
                    WardKill(dto.timestamp, killerId, wardType)
                }

                "BUILDING_KILL" -> {
                    val bounty = dto.additionalData.getInt("bounty") ?: 0
                    val buildingType = BuildingType.fromValue(dto.additionalData.getString("buildingType") ?: "")
                    val killerId = dto.additionalData.getInt("killerId") ?: 0
                    val laneType = LaneType.fromValue(dto.additionalData.getString("laneType") ?: "")
                    val position =
                        Position(
                            dto.additionalData.getInt("positionX") ?: 0,
                            dto.additionalData.getInt("positionY") ?: 0,
                        )
                    val team = dto.additionalData.getLong("teamId") ?: 0
                    val towerType = TowerType.fromValue(dto.additionalData.getString("towerType") ?: "")
                    val assistingParticipantIds =
                        dto.additionalData.getArray<Int>("assistingParticipantIds") ?: emptyList()
                    BuildingKill(
                        dto.timestamp,
                        bounty,
                        buildingType,
                        killerId,
                        laneType,
                        position,
                        team,
                        towerType,
                        assistingParticipantIds,
                    )
                }

                "DRAGON_SOUL_GIVEN" -> {
                    val name = dto.additionalData.getString("name") ?: ""
                    val teamId = dto.additionalData.getLong("teamId") ?: 0
                    DragonSoulGiven(dto.timestamp, name, teamId)
                }

                "OBJECTIVE_BOUNTY_PRESTART" -> {
                    val actualStartTime = dto.additionalData.getLong("actualStartTime") ?: 0
                    val teamId = dto.additionalData.getLong("teamId") ?: 0
                    ObjectiveBountyPrestart(dto.timestamp, actualStartTime, teamId)
                }

                "OBJECTIVE_BOUNTY_FINISH" -> {
                    val teamId = dto.additionalData.getLong("teamId") ?: 0
                    ObjectiveBountyFinish(dto.timestamp, teamId)
                }

                "CHAMPION_TRANSFORM" -> {
                    val participantId = dto.additionalData.getInt("participantId") ?: 0
                    val transformType = dto.additionalData.getString("transformType") ?: ""
                    ChampionTransform(dto.timestamp, participantId, transformType)
                }

                else -> Unknown(dto.timestamp, dto.additionalData)
            }
        }
    }
}

public class TimelineFrames private constructor(
    @JvmField public val participantFrames: Map<Int, ParticipantFrame>,
    @JvmField public val events: List<TimelineEvent>,
    @JvmField public val timestamp: Long,
) {
    public fun getEventByType(type: TimelineEventType): List<TimelineEvent> {
        return events.filter { it.type == type }
    }

    public fun getEventByTimestamp(min: Long, max: Long): List<TimelineEvent> {
        return events.filter { it.timestamp in min..max }
    }

    internal companion object {
        fun fromDto(dto: FramesTimelineDto): TimelineFrames {
            return TimelineFrames(
                // Transform each key from String to Int and each Dto in Response Object
                participantFrames = dto.participantFrames.mapKeys { it.key.toInt() }.mapValues { ParticipantFrame.fromDto(it.value) },
                events = dto.events.map { TimelineEvent.fromDto(it) },
                timestamp = dto.timestamp,
            )
        }
    }
}

public class TimelineParticipant private constructor(
    @JvmField public val participantId: Int,
    @JvmField public val puuid: String,
) {
    internal companion object {
        fun fromDto(dto: ParticipantTimelineDto): TimelineParticipant {
            return TimelineParticipant(
                dto.participantId,
                dto.puuid,
            )
        }
    }
}

public class TimelineInfo private constructor(
    @JvmField public val endOfGameResult: String,
    @JvmField public val frameInterval: Long,
    @JvmField public val gameId: Long,
    @JvmField public val participants: List<TimelineParticipant>,
    @JvmField public val frames: List<TimelineFrames>,
) {
    internal companion object {
        fun fromDto(dto: InfoTimelineDto): TimelineInfo {
            return TimelineInfo(
                endOfGameResult = dto.endOfGameResult,
                frameInterval = dto.frameInterval,
                gameId = dto.gameId,
                participants = dto.participants.map { TimelineParticipant.fromDto(it) },
                frames = dto.frames.map { TimelineFrames.fromDto(it) },
            )
        }
    }
}

public class TimelineMetadata private constructor(
    @JvmField public val dataVersion: String,
    @JvmField public val matchId: String,
    @JvmField public val participants: List<String>,
) {
    internal companion object {
        fun fromDto(dto: MetadataTimelineDto): TimelineMetadata {
            return TimelineMetadata(
                dataVersion = dto.dataVersion,
                matchId = dto.matchId,
                participants = dto.participants,
            )
        }
    }
}

public class MatchTimeline private constructor(
    @JvmField public val info: TimelineInfo,
    @JvmField public val metadata: TimelineMetadata,
) {
    internal companion object {
        fun fromDto(dto: TimelineDto): MatchTimeline {
            return MatchTimeline(
                info = TimelineInfo.fromDto(dto.info),
                metadata = TimelineMetadata.fromDto(dto.metadata),
            )
        }
    }
}
