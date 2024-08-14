package apis.lol.champion

import com.squareup.moshi.Json


public data class ChampionResponse(
    @Json(name = "freeChampionIds")
    val freeChampionIds: List<Int>,
    @Json(name = "freeChampionIdsForNewPlayers")
    val freeChampionIdsForNewPlayers: List<Int>,
    @Json(name = "maxNewPlayerLevel")
    val maxNewPlayerLevel: Int
)