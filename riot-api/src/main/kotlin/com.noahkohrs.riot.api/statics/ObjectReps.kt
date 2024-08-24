package com.noahkohrs.riot.api.statics

import com.noahkohrs.riot.api.statics.StaticsRiotData.queues

public data class LoLQueue(
    val queueId: Int,
    val map: String,
    val description: String?,
    val notes: String?,
) {
    public companion object {
        public fun fromId(queueId: Int): LoLQueue {
            return queues[queueId] ?: error("Queue $queueId not found")
        }
    }
}

public data class LoLMap(
    val mapId: Int,
    val mapName: String,
    val notes: String,
)

internal data class ItemDto(
    val data: Map<String, LoLItem>,
)

public data class LoLItem(
//    "name": "",
    val name: String,
// "rune": {
    val rune: Rune,
// "gold": {
    val gold: Gold,
// "group": "",
    val group: String,
// "description": "",
    val description: String,
// "colloq": ";",
    val colloq: String,
// "plaintext": "",
    val plaintext: String,
// "consumed": false,
    val consumed: Boolean,
// "stacks": 1,
    val stacks: Int,
// "depth": 1,
    val depth: Int,
// "consumeOnFull": false,
    val consumeOnFull: Boolean,
// "from": [],
    val from: List<Int>,
// "into": [],
    val into: List<Int>,
// "specialRecipe": 0,
    val specialRecipe: Int,
// "inStore": true,
    val inStore: Boolean,
// "hideFromAll": false,
    val hideFromAll: Boolean,
// "requiredChampion": "",
    val requiredChampion: String,
// "requiredAlly": "",
    val requiredAlly: String,
// "stats": {
    val stats: Map<String, Int>,
//    "FlatHPPoolMod": 0,
//    "rFlatHPModPerLevel": 0,
//    "FlatMPPoolMod": 0,
//    "rFlatMPModPerLevel": 0,
//    "PercentHPPoolMod": 0,
//    "PercentMPPoolMod": 0,
//    "FlatHPRegenMod": 0,
//    "rFlatHPRegenModPerLevel": 0,
//    "PercentHPRegenMod": 0,
//    "FlatMPRegenMod": 0,
//    "rFlatMPRegenModPerLevel": 0,
//    "PercentMPRegenMod": 0,
//    "FlatArmorMod": 0,
//    "rFlatArmorModPerLevel": 0,
//    "PercentArmorMod": 0,
//    "rFlatArmorPenetrationMod": 0,
//    "rFlatArmorPenetrationModPerLevel": 0,
//    "rPercentArmorPenetrationMod": 0,
//    "rPercentArmorPenetrationModPerLevel": 0,
//    "FlatPhysicalDamageMod": 0,
//    "rFlatPhysicalDamageModPerLevel": 0,
//    "PercentPhysicalDamageMod": 0,
//    "FlatMagicDamageMod": 0,
//    "rFlatMagicDamageModPerLevel": 0,
//    "PercentMagicDamageMod": 0,
//    "FlatMovementSpeedMod": 0,
//    "rFlatMovementSpeedModPerLevel": 0,
//    "PercentMovementSpeedMod": 0,
//    "rPercentMovementSpeedModPerLevel": 0,
//    "FlatAttackSpeedMod": 0,
//    "PercentAttackSpeedMod": 0,
//    "rPercentAttackSpeedModPerLevel": 0,
//    "rFlatDodgeMod": 0,
//    "rFlatDodgeModPerLevel": 0,
//    "PercentDodgeMod": 0,
//    "FlatCritChanceMod": 0,
//    "rFlatCritChanceModPerLevel": 0,
//    "PercentCritChanceMod": 0,
//    "FlatCritDamageMod": 0,
//    "rFlatCritDamageModPerLevel": 0,
//    "PercentCritDamageMod": 0,
//    "FlatBlockMod": 0,
//    "PercentBlockMod": 0,
//    "FlatSpellBlockMod": 0,
//    "rFlatSpellBlockModPerLevel": 0,
//    "PercentSpellBlockMod": 0,
//    "FlatEXPBonus": 0,
//    "PercentEXPBonus": 0,
//    "rPercentCooldownMod": 0,
//    "rPercentCooldownModPerLevel": 0,
//    "rFlatTimeDeadMod": 0,
//    "rFlatTimeDeadModPerLevel": 0,
//    "rPercentTimeDeadMod": 0,
//    "rPercentTimeDeadModPerLevel": 0,
//    "rFlatGoldPer10Mod": 0,
//    "rFlatMagicPenetrationMod": 0,
//    "rFlatMagicPenetrationModPerLevel": 0,
//    "rPercentMagicPenetrationMod": 0,
//    "rPercentMagicPenetrationModPerLevel": 0,
//    "FlatEnergyRegenMod": 0,
//    "rFlatEnergyRegenModPerLevel": 0,
//    "FlatEnergyPoolMod": 0,
//    "rFlatEnergyModPerLevel": 0,
//    "PercentLifeStealMod": 0,
//    "PercentSpellVampMod": 0
// },
// "tags": [],
    val tags: List<String>,
// "maps": {
//    "1": true,
//    "8": true,
//    "10": true,
//    "12": true
// }
    val maps: Map<String, Boolean>,
// },
) {
    public data class Rune(
        val isrune: Boolean,
        val tier: Int,
        val type: String,
    )

    public data class Gold(
        val base: Int,
        val total: Int,
        val sell: Int,
        val purchasable: Boolean,
    )
}
