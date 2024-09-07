package com.noahkohrs.riot.api.lol.champion

import com.noahkohrs.riot.api.annotations.LinkToStaticApi
import com.noahkohrs.riot.api.dtos.ChampionInfo

public class ChampionRotation private constructor(
    @LinkToStaticApi
    @JvmField public val freeChampionIds: List<Int>,
    @LinkToStaticApi
    @JvmField public val freeChampionIdsForNewPlayers: List<Int>,
    @JvmField public val maxNewPlayerLevel: Int,
) {
    internal companion object {
        fun fromDto(championInfoDto: ChampionInfo): ChampionRotation {
            return ChampionRotation(
                freeChampionIds = championInfoDto.freeChampionIds,
                freeChampionIdsForNewPlayers = championInfoDto.freeChampionIdsForNewPlayers,
                maxNewPlayerLevel = championInfoDto.maxNewPlayerLevel,
            )
        }
    }
}
