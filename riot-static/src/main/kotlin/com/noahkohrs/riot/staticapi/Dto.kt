package com.noahkohrs.riot.staticapi

import com.squareup.moshi.Json

internal data class ResponseWrapper<T>(
    @Json(name = "data")
    val data: T,
)

// TODO: Consider if the static Api should work with a separation between responses and dtos as the normal api.
public open class ChampionSummaryDto(
    @Json(name = "id")
    public val id: String,
    @Json(name = "key")
    public val key: String,
    @Json(name = "name")
    public val name: String,
    @Json(name = "title")
    public val title: String,
    @Json(name = "blurb")
    public val blurb: String,
    @Json(name = "info")
    public val info: Info,
    @Json(name = "image")
    public val image: Image,
    // TODO: Consider rewriting this to return enumeration field
    @Json(name = "tags")
    public val tags: List<String>,
    @Json(name = "partype")
    public val partype: String,
    @Json(name = "stats")
    public val stats: Stats,
)

public class ChampionDto(
    id: String,
    key: String,
    name: String,
    title: String,
    blurb: String,
    info: Info,
    image: Image,
    @Json(name = "skins") public val skins: List<Skin>,
    @Json(name = "lore") public val lore: String,
    @Json(name = "allytips") public val allyTips: List<String>,
    @Json(name = "enemytips") public val enemyTips: List<String>,
    tags: List<String>,
    partype: String,
    stats: Stats,
    @Json(name = "spells") public val spells: List<Spell>,
    @Json(name = "passive") public val passive: Passive,
) : ChampionSummaryDto(
        id = id,
        key = key,
        name = name,
        title = title,
        blurb = blurb,
        info = info,
        image = image,
        tags = tags,
        partype = partype,
        stats = stats,
    )

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

public data class Info(
    @Json(name = "attack") val attack: Int,
    @Json(name = "defense") val defense: Int,
    @Json(name = "magic") val magic: Int,
    @Json(name = "difficulty") val difficulty: Int,
)

public data class Image(
    @Json(name = "full") val full: String,
    @Json(name = "sprite") val sprite: String,
    @Json(name = "group") val group: String,
    @Json(name = "x") val x: Int,
    @Json(name = "y") val y: Int,
    @Json(name = "w") val width: Int,
    @Json(name = "h") val height: Int,
)

public data class Skin(
    @Json(name = "id") val id: String,
    @Json(name = "num") val num: Int,
    @Json(name = "name") val name: String,
    @Json(name = "chromas") val chromas: Boolean,
)

public data class Spell(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "tooltip") val tooltip: String,
    @Json(name = "maxrank") val maxRank: Int,
    @Json(name = "cooldown") val cooldown: List<Int>,
    @Json(name = "cooldownBurn") val cooldownBurn: String,
    @Json(name = "cost") val cost: List<Int>,
    @Json(name = "costBurn") val costBurn: String,
    @Json(name = "range") val range: List<Int>,
    @Json(name = "rangeBurn") val rangeBurn: String,
    @Json(name = "image") val image: Image,
    @Json(name = "resource") val resource: String,
)

public data class Passive(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "image") val image: Image,
)
