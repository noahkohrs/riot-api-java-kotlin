package com.noahkohrs.riot.api.values

public enum class Languages(
    public val language: String,
) {
    // ar_AE
    ARABIC("ar_AE"),

    // cs_CZ
    CZECH("cs_CZ"),

    // de_DE
    GERMAN("de_DE"),

    // el_GR
    GREEK("el_GR"),

    // en_AU
    ENGLISH_AU("en_AU"),

    // en_GB
    ENGLISH_GB("en_GB"),

    // en_PH
    ENGLISH_PH("en_PH"),

    // en_SG
    ENGLISH_SG("en_SG"),

    // en_US
    ENGLISH("en_US"),

    // es_AR
    SPANISH_AR("es_AR"),

    // es_ES
    SPANISH("es_ES"),

    // es_MX
    SPANISH_MX("es_MX"),

    // fr_FR
    FRENCH("fr_FR"),

    // hu_HU
    HUNGARIAN("hu_HU"),

    // it_IT
    ITALIAN("it_IT"),

    // ja_JP
    JAPANESE("ja_JP"),

    // ko_KR
    KOREAN("ko_KR"),

    // pl_PL
    POLISH("pl_PL"),

    // pt_BR
    PORTUGUESE("pt_BR"),

    // ro_RO
    ROMANIAN("ro_RO"),

    // ru_RU
    RUSSIAN("ru_RU"),

    // th_TH
    THAI("th_TH"),

    // tr_TR
    TURKISH("tr_TR"),

    // vi_VN
    VIETNAMESE("vi_VN"),

    // zh_CN
    CHINESE("zh_CN"),

    // zh_MY
    CHINESE_MY("zh_MY"),

    // zh_TW
    CHINESE_TW("zh_TW"),
    ;

    internal companion object {
        fun fromValue(value: String): Languages {
            return entries.firstOrNull { it.language == value } ?: throw IllegalArgumentException("Unknown language: $value")
        }
    }
}
