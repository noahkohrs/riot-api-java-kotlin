package com.noahkohrs.riot.api

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StaticsRiotDataTest {

    @Test
    fun getQueues() {
        val currentQueuesNumber = 95
        assertEquals(StaticsRiotData.queues.size, currentQueuesNumber)
    }

    @Test
    fun getMaps() {
        val currentMapsNumber = 16
        assertEquals(StaticsRiotData.maps.size, currentMapsNumber)
    }
}