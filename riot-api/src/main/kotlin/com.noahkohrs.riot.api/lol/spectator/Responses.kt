package com.noahkohrs.riot.api.lol.spectator

import com.noahkohrs.riot.api.annotations.LinkToStaticApi
import com.noahkohrs.riot.api.dtos.*

public class CurrentGameParticipant private constructor(
    @LinkToStaticApi
    @JvmField public val championId: Long,
    @JvmField public val perks: Perks,
    @LinkToStaticApi
    @JvmField public val profileIconId: Long,
    @JvmField public val bot: Boolean,
    @JvmField public val teamId: Long,
    @JvmField public val summonerId: String,
    @JvmField public val puuid: String,
    @LinkToStaticApi
    @JvmField public val spell1Id: Long,
    @LinkToStaticApi
    @JvmField public val spell2Id: Long,
    @JvmField public val gameCustomizationObjects: List<GameCustomizationObject>,
) {
    internal companion object {
        internal fun fromDto(dto: CurrentGameParticipantDto): CurrentGameParticipant {
            return CurrentGameParticipant(
                championId = dto.championId,
                perks =
                    Perks(
                        perkIds = dto.perks.perkIds,
                        perkStyle = dto.perks.perkStyle,
                        perkSubStyle = dto.perks.perkSubStyle,
                    ),
                profileIconId = dto.profileIconId,
                bot = dto.bot,
                teamId = dto.teamId,
                summonerId = dto.summonerId,
                puuid = dto.puuid,
                spell1Id = dto.spell1Id,
                spell2Id = dto.spell2Id,
                gameCustomizationObjects = dto.gameCustomizationObjects.map { GameCustomizationObject(it.category, it.content) },
            )
        }
    }

    public class Perks(
        @LinkToStaticApi
        @JvmField public val perkIds: List<Long>,
        @JvmField public val perkStyle: Long,
        @JvmField public val perkSubStyle: Long,
    )

    public class GameCustomizationObject(
        @JvmField public val category: String,
        @JvmField public val content: String,
    )
}

public class CurrentGameInfo private constructor(
    @JvmField public val gameId: Long,
    @JvmField public val gameType: String,
    @JvmField public val gameStartTime: Long,
    @JvmField public val mapId: Long,
    @JvmField public val gameLength: Long,
    @JvmField public val platformId: String,
    // TODO: Investigate if we should refine this value as a enum or not
    @JvmField public val gameMode: String,
    @JvmField public val bannedChampions: List<BannedChampion>,
    @JvmField public val gameQueueConfigId: Long,
    @JvmField public val observers: Observers,
    @JvmField public val participants: List<CurrentGameParticipant>,
) {
    internal companion object {
        internal fun fromDto(dto: CurrentGameInfoDto): CurrentGameInfo {
            return CurrentGameInfo(
                gameId = dto.gameId,
                gameType = dto.gameType,
                gameStartTime = dto.gameStartTime,
                mapId = dto.mapId,
                gameLength = dto.gameLength,
                platformId = dto.platformId,
                gameMode = dto.gameMode,
                bannedChampions = dto.bannedChampions.map { BannedChampion(it.championId, it.teamId, it.pickTurn) },
                gameQueueConfigId = dto.gameQueueConfigId,
                observers = Observers(dto.observers.encryptionKey),
                participants = dto.participants.map { CurrentGameParticipant.fromDto(it) },
            )
        }
    }

    public class BannedChampion(
        @JvmField public val championId: Long,
        @JvmField public val teamId: Long,
        @JvmField public val pickTurn: Int,
    )

    public class Observers(
        @JvmField public val encryptionKey: String,
    )
}

public class FeaturedGameInfo private constructor(
    @JvmField public val gameMode: String,
    @JvmField public val gameLength: Long,
    @JvmField public val mapId: Long,
    @JvmField public val gameType: String,
    @JvmField public val bannedChampions: List<CurrentGameInfo.BannedChampion>,
    @JvmField public val gameId: Long,
    @JvmField public val observers: CurrentGameInfo.Observers,
    @JvmField public val gameQueueConfigId: Long,
    @JvmField public val participants: List<ParticipantsSpectator>,
    @JvmField public val platformId: String,
) {
    internal companion object {
        internal fun fromDto(dto: FeaturedGameInfoDto): FeaturedGameInfo {
            return FeaturedGameInfo(
                gameMode = dto.gameMode,
                gameLength = dto.gameLength,
                mapId = dto.mapId,
                gameType = dto.gameType,
                bannedChampions = dto.bannedChampions.map { CurrentGameInfo.BannedChampion(it.championId, it.teamId, it.pickTurn) },
                gameId = dto.gameId,
                observers = CurrentGameInfo.Observers(dto.observers.encryptionKey),
                gameQueueConfigId = dto.gameQueueConfigId,
                participants =
                    dto.participants.map {
                        ParticipantsSpectator(
                            isABot = it.bot,
                            profileIconId = it.profileIconId,
                            summonerId = it.summonerId,
                            puuid = it.puuid,
                            championId = it.championId,
                            teamId = it.teamId,
                            spell1Id = it.spell1Id,
                            spell2Id = it.spell2Id,
                        )
                    },
                platformId = dto.platformId,
            )
        }
    }

    public class ParticipantsSpectator(
        @JvmField public val isABot: Boolean,
        @LinkToStaticApi
        @JvmField public val spell1Id: Long,
        @LinkToStaticApi
        @JvmField public val spell2Id: Long,
        @LinkToStaticApi
        @JvmField public val profileIconId: Long,
        @JvmField public val summonerId: String,
        @JvmField public val puuid: String,
        @LinkToStaticApi
        @JvmField public val championId: Long,
        @JvmField public val teamId: Long,
    )
}

public class FeaturedGames private constructor(
    @JvmField public val clientRefreshInterval: Long,
    @JvmField public val gameList: List<FeaturedGameInfo>,
) {
    internal companion object {
        internal fun fromDto(dto: FeaturedGamesDto): FeaturedGames {
            return FeaturedGames(
                clientRefreshInterval = dto.clientRefreshInterval,
                gameList = dto.gameList.map { FeaturedGameInfo.fromDto(it) },
            )
        }
    }
}
