package com.noahkohrs.riot.staticapi.lol

import com.noahkohrs.riot.staticapi.RiotStaticApi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LoLStaticApiTest {
    val api = RiotStaticApi().lol

    @Test
    fun getVersions() {
        val versions = api.getVersions()
        assertTrue(versions.isNotEmpty())
    }

    @Test
    fun getLatestVersion() {
        val version = api.getLatestVersion()
        assertTrue(version.isNotEmpty())
    }

    @Test
    fun getLanguages() {
        val languages = api.getLanguages()
        assertTrue(languages.isNotEmpty())
    }

    @Test
    fun getChampions() {
        val champions = api.getChampions()
        assertTrue(champions.isNotEmpty())
        println(champions[0])
        champions.forEach {
            assertTrue(it.id.isNotEmpty())
            assertTrue(it.name.isNotEmpty())
            assertTrue(it.title.isNotEmpty())
        }
    }

    @Test
    fun getChampion() {
        val champion = api.getChampion("Aatrox")
        assertNotNull(champion)
        println(champion)
    }

    @Test
    fun getImages() {
        val splash = api.getChampionSplash("Aatrox", 0)
        assertTrue(splash.bytes.isNotEmpty())

        val spell = api.getSpell("FlashFrost.png")
        assertTrue(spell.bytes.isNotEmpty())

        val passive = api.getPassive("Anivia_P.png")
        assertTrue(passive.bytes.isNotEmpty())
    }
}
