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

        champions.forEach {
            assertTrue(it.id.isNotEmpty())
            assertTrue(it.name.isNotEmpty())
            assertTrue(it.title.isNotEmpty())
        }
    }
}
