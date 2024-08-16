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

public enum class LoLQueue(public val value: String) {
    RankedSoloQueue("RANKED_SOLO_5x5"),
    RankedFlexQueue("RANKED_FLEX_SR"),
    // Twisted Treeline is deprecated on riot's end
//    RANKED_FLEX_TT("RANKED_FLEX_TT"),
    // TFT is deprecated too on riot's end
//    RANKED_TFT("RANKED_TFT"),
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
}

public enum class LoLDivision(public val value: String) {
    I("I"),
    II("II"),
    III("III"),
    IV("IV"),
}
