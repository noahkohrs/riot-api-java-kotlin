package com.noahkohrs.riot.staticapi

import com.squareup.moshi.Json

internal data class ResponseWrapper<T>(
    @Json(name = "data")
    val data: T,
)

// TODO: Consider if the static Api should work with a separation between responses and dtos as the normal api.
public data class ChampionDto(
// "version": "14.18.1",
// "id": "Aatrox",
    @Json(name = "id")
    val id: String,
// "key": "266",
    @Json(name = "key")
    val key: String,
// "name": "Aatrox",
    @Json(name = "name")
    val name: String,
// "title": "the Darkin Blade",
    @Json(name = "title")
    val title: String,
// "blurb": "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
    @Json(name = "blurb")
    val blurb: String,
// "info": {
//    "attack": 8,
//    "defense": 4,
//    "magic": 3,
//    "difficulty": 4
// },
    @Json(name = "info")
    val info: Info,
    // TODO: Consider how to handle the image field
    // Might be nice to give access to solid object which is able to render the img.
// "image": {
//    "full": "Aatrox.png",
//    "sprite": "champion0.png",
//    "group": "champion",
//    "x": 0,
//    "y": 0,
//    "w": 48,
//    "h": 48
// },
// "tags": [
// "Fighter"
// ],
    // TODO: Consider rewriting this to return enumeration field
    @Json(name = "tags")
    val tags: List<String>,
// "partype": "Blood Well",
    @Json(name = "partype")
    val partype: String,
// "stats": {
//    "hp": 650,
//    "hpperlevel": 114,
//    "mp": 0,
//    "mpperlevel": 0,
//    "movespeed": 345,
//    "armor": 38,
//    "armorperlevel": 4.8,
//    "spellblock": 32,
//    "spellblockperlevel": 2.05,
//    "attackrange": 175,
//    "hpregen": 3,
//    "hpregenperlevel": 0.5,
//    "mpregen": 0,
//    "mpregenperlevel": 0,
//    "crit": 0,
//    "critperlevel": 0,
//    "attackdamage": 60,
//    "attackdamageperlevel": 5,
//    "attackspeedperlevel": 2.5,
//    "attackspeed": 0.651
// }
    @Json(name = "stats")
    val stats: Stats,
) {
    public data class Info(
        @Json(name = "attack")
        val attack: Int,
        @Json(name = "defense")
        val defense: Int,
        @Json(name = "magic")
        val magic: Int,
        @Json(name = "difficulty")
        val difficulty: Int,
    )

    // TODO: Make the naming more readable
    public data class Stats(
        @Json(name = "hp")
        val hp: Int,
        @Json(name = "hpperlevel")
        val hpperlevel: Int,
        @Json(name = "mp")
        val mp: Int,
        @Json(name = "mpperlevel")
        val mpperlevel: Float,
        @Json(name = "movespeed")
        val movespeed: Int,
        @Json(name = "armor")
        val armor: Int,
        @Json(name = "armorperlevel")
        val armorperlevel: Float,
        @Json(name = "spellblock")
        val spellblock: Int,
        @Json(name = "spellblockperlevel")
        val spellblockperlevel: Float,
        @Json(name = "attackrange")
        val attackrange: Int,
        @Json(name = "hpregen")
        val hpregen: Float,
        @Json(name = "hpregenperlevel")
        val hpregenperlevel: Float,
        @Json(name = "mpregen")
        val mpregen: Float,
        @Json(name = "mpregenperlevel")
        val mpregenperlevel: Float,
        @Json(name = "crit")
        val crit: Int,
        @Json(name = "critperlevel")
        val critperlevel: Int,
        @Json(name = "attackdamage")
        val attackdamage: Int,
        @Json(name = "attackdamageperlevel")
        val attackdamageperlevel: Float,
        @Json(name = "attackspeedperlevel")
        val attackspeedperlevel: Float,
        @Json(name = "attackspeed")
        val attackspeed: Float,
    )
}
