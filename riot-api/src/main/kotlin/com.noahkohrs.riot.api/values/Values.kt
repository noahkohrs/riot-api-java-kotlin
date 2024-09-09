package com.noahkohrs.riot.api.values

public enum class Platform(public val value: String) {
    BR1("br1"),
    EUN1("eun1"),
    EUW1("euw1"),
    JP1("jp1"),
    KR("kr"),
    LA1("la1"),
    LA2("la2"),
    ME1("me1"),
    NA1("na1"),
    OC1("oc1"),
    PH2("ph2"),
    RU("ru"),
    SG2("sg2"),
    TH2("th2"),
    TR1("tr1"),
    TW2("tw2"),
    VN2("vn2"),
}

public enum class GlobalRegion(public val value: String) {
    AMERICAS("americas"),
    ASIA("asia"),
    EUROPE("europe"),
    SEA("sea"),
    ;

    public companion object {
        public fun fromRegion(platform: Platform): GlobalRegion {
            return when (platform) {
                Platform.BR1, Platform.LA1, Platform.LA2, Platform.NA1, Platform.OC1 -> AMERICAS
                Platform.EUN1, Platform.EUW1, Platform.TR1, Platform.RU, Platform.ME1 -> EUROPE
                Platform.JP1, Platform.KR -> ASIA
                Platform.PH2, Platform.SG2, Platform.TH2, Platform.TW2, Platform.VN2 -> SEA
            }
        }
    }
}

public enum class AccountRegion(public val value: String) {
    AMERICAS("americas"),
    ASIA("asia"),
    EUROPE("europe"),
    ESPORTS("esports"),
    ;

    public companion object {
        public fun fromRegion(platform: Platform): AccountRegion {
            return when (platform) {
                Platform.BR1, Platform.LA1, Platform.LA2, Platform.NA1, Platform.OC1 -> AMERICAS
                Platform.EUN1, Platform.EUW1, Platform.TR1, Platform.RU, Platform.ME1 -> EUROPE
                Platform.JP1, Platform.KR -> ASIA
                else -> EUROPE
            }
        }
    }
}

public enum class LoLRankedQueue(public val value: String) {
    RankedSoloQueue("RANKED_SOLO_5x5"),
    RankedFlexQueue("RANKED_FLEX_SR"),
    ;

    // Twisted Treeline is deprecated on riot's end
//    RANKED_FLEX_TT("RANKED_FLEX_TT"),
    // TFT is deprecated too on riot's end
//    RANKED_TFT("RANKED_TFT"),

    internal companion object {
        fun fromValue(value: String): LoLRankedQueue {
            return when (value) {
                "RANKED_SOLO_5x5" -> RankedSoloQueue
                "RANKED_FLEX_SR" -> RankedFlexQueue
                else -> throw IllegalArgumentException("Invalid value for LoLQueue: $value")
            }
        }
    }
}

public enum class LoLQueue(value: String) {
    RankedSoloQueue("RANKED_SOLO_5x5"),
    RankedFlexQueue("RANKED_FLEX_SR"),
    Arena("CHERRY"),
    ;

    internal companion object {
        fun fromValue(value: String): LoLQueue {
            return when (value) {
                "RANKED_SOLO_5x5" -> RankedSoloQueue
                "RANKED_FLEX_SR" -> RankedFlexQueue
                "CHERRY" -> Arena
                else -> throw IllegalArgumentException("Invalid value for LoLQueue: $value")
            }
        }
    }
}

public enum class LoLTier(public val value: String) {
    IRON("IRON"),
    BRONZE("BRONZE"),
    SILVER("SILVER"),
    GOLD("GOLD"),
    PLATINUM("PLATINUM"),
    EMERALD("EMERALD"),
    DIAMOND("DIAMOND"),
    MASTER("MASTER"),
    GRANDMASTER("GRANDMASTER"),
    CHALLENGER("CHALLENGER"),
    ;

    /**
     * Override valueOf
     */
    public companion object {
        internal fun fromValue(value: String): LoLTier {
            return when (value) {
                "IRON" -> IRON
                "BRONZE" -> BRONZE
                "SILVER" -> SILVER
                "GOLD" -> GOLD
                "PLATINUM" -> PLATINUM
                "EMERALD" -> EMERALD
                "DIAMOND" -> DIAMOND
                "MASTER" -> MASTER
                "GRANDMASTER" -> GRANDMASTER
                "CHALLENGER" -> CHALLENGER
                else -> throw IllegalArgumentException("Invalid value for LoLTier: $value")
            }
        }
    }
}

public enum class LoLDivision(public val value: String) {
    I("I"),
    II("II"),
    III("III"),
    IV("IV"),
    ;

    internal companion object {
        fun fromValue(value: String): LoLDivision {
            return when (value) {
                "I" -> I
                "II" -> II
                "III" -> III
                "IV" -> IV
                else -> throw IllegalArgumentException("Invalid value for LoLDivision: $value")
            }
        }
    }
}

public enum class Position {
    TOP,
    JUNGLE,
    MIDDLE,
    BOTTOM,
    UTILITY,
    UNKNOWN,
    ;

    internal companion object {
        fun fromValue(str: String): Position {
            return when (str) {
                "TOP" -> TOP
                "JUNGLE" -> JUNGLE
                "MIDDLE" -> MIDDLE
                "BOTTOM" -> BOTTOM
                "UTILITY" -> UTILITY
                else -> UNKNOWN
            }
        }
    }
}

public enum class Role {
    SOLO,
    CARRY,
    SUPPORT,
    NONE,
    UNKNOWN,
    ;

    internal companion object {
        fun fromValue(str: String): Role {
            return when (str) {
                "SOLO" -> SOLO
                "CARRY" -> CARRY
                "SUPPORT" -> SUPPORT
                "NONE" -> NONE
                else -> UNKNOWN
            }
        }
    }
}

public enum class ClashRole {
    CAPTAIN,
    MEMBER,
    ;

    internal companion object {
        fun fromValue(str: String): ClashRole {
            return when (str) {
                "CAPTAIN" -> CAPTAIN
                "MEMBER" -> MEMBER
                else -> throw IllegalArgumentException("Invalid value for ClashRole: $str")
            }
        }
    }
}

public enum class TeamSide {
    BLUE,
    RED,
    UNKNOWN,
    ;

    internal companion object {
        fun fromValue(str: String): TeamSide {
            return when (str) {
                "BLUE" -> BLUE
                "RED" -> RED
                else -> UNKNOWN
            }
        }

        fun fromValue(int: Int): TeamSide {
            return when (int) {
                100 -> BLUE
                200 -> RED
                else -> UNKNOWN
            }
        }
    }
}

public enum class LaneType {
    BOT,
    MID,
    TOP,
    ;

    internal companion object {
        fun fromValue(str: String): LaneType {
            return when (str) {
                "BOT_LANE" -> BOT
                "MID_LANE" -> MID
                "TOP_LANE" -> TOP
                else -> throw IllegalArgumentException("Invalid value for LaneType: $str")
            }
        }
    }
}

public enum class BuildingType {
    TOWER,
    INHIBITOR,
    NEXUS,
    ;

    internal companion object {
        fun fromValue(str: String): BuildingType {
            return when (str) {
                "TOWER_BUILDING" -> TOWER
                "INHIBITOR_BUILDING" -> INHIBITOR
                "NEXUS_BUILDING" -> NEXUS
                else -> throw IllegalArgumentException("Invalid value for BuildingType: $str")
            }
        }
    }
}

public enum class TowerType {
    OUTER_TURRET,
    INNER_TURRET,
    BASE_TURRET,
    NEXUS_TURRET,
    ;

    internal companion object {
        fun fromValue(str: String): TowerType? {
            return when (str) {
                "OUTER_TURRET" -> OUTER_TURRET
                "INNER_TURRET" -> INNER_TURRET
                "BASE_TURRET" -> BASE_TURRET
                "NEXUS_TURRET" -> NEXUS_TURRET
                else -> null
            }
        }
    }
}
