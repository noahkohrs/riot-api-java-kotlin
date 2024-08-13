package apis.lol.champion

import com.fasterxml.jackson.annotation.JsonProperty


public data class ChampionResponse(
    @JsonProperty("freeChampionIds")
    val freeChampionIds: List<Int>,
    @JsonProperty("freeChampionIdsForNewPlayers")
    val freeChampionIdsForNewPlayers: List<Int>,
    @JsonProperty("maxNewPlayerLevel")
    val maxNewPlayerLevel: Int
)