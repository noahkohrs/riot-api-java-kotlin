package com.noahkohrs.riot.api

import com.noahkohrs.riot.api.statics.StaticsRiotData
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class StaticsRiotDataTest {
    @Test
    fun getQueues() {
        val currentQueuesNumber = 96

        assertEquals(StaticsRiotData.queues.size, currentQueuesNumber)
    }

    @Test
    fun getMaps() {
        val currentMapsNumber = 16
        assertEquals(StaticsRiotData.maps.size, currentMapsNumber)
    }

    @Test
    fun getItems() {
        val currentItemsNumber = 0
        assertEquals(StaticsRiotData.items.size, currentItemsNumber)
    }
}
