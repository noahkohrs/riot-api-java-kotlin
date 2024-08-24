package com.noahkohrs.riot.api

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
}
