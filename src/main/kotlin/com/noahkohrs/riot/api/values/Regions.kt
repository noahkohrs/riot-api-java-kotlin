package com.noahkohrs.riot.api.values

public enum class Region(public val value: String) {
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
        public fun fromRegion(region: Region): GlobalRegion {
            return when (region) {
                Region.BR1, Region.LA1, Region.LA2, Region.NA1, Region.OC1 -> AMERICAS
                Region.EUN1, Region.EUW1, Region.TR1, Region.RU, Region.ME1 -> EUROPE
                Region.JP1, Region.KR -> ASIA
                Region.PH2, Region.SG2, Region.TH2, Region.TW2, Region.VN2 -> SEA
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
        public fun fromRegion(region: Region): AccountRegion {
            return when (region) {
                Region.BR1, Region.LA1, Region.LA2, Region.NA1, Region.OC1 -> AMERICAS
                Region.EUN1, Region.EUW1, Region.TR1, Region.RU, Region.ME1 -> EUROPE
                Region.JP1, Region.KR -> ASIA
                else -> EUROPE
            }
        }
    }
}

