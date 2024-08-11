package apis.champion

import com.fasterxml.jackson.annotation.JsonProperty


data class ChampionResponse(
    @JsonProperty("freeChampionIds")
    val freeChampionIds: List<Int>,
    @JsonProperty("freeChampionIdsForNewPlayers")
    val freeChampionIdsForNewPlayers: List<Int>,
    @JsonProperty("maxNewPlayerLevel")
    val maxNewPlayerLevel: Int
)