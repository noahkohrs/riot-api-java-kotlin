package com.noahkohrs.riot.api.statics

import com.noahkohrs.riot.api.StaticRiotDragoonFactory
import feign.RequestLine

internal object StaticsRiotData {
    private val staticApi =
        StaticRiotDragoonFactory.create()
    val queues = staticApi.getQueues().associateBy { it.queueId }

    val maps = staticApi.getMaps().associateBy { it.mapId }

    // TODO: NOT WORKING YET
    // val items = staticApi.getItems().data

    internal interface StaticDragoonApi {
        @RequestLine("GET /docs/lol/queues.json")
        fun getQueues(): List<LoLQueue>

        @RequestLine("GET /docs/lol/maps.json")
        fun getMaps(): List<LoLMap>

        @RequestLine("GET /docs/lol/item.json")
        fun getItems(): ItemDto
    }
}
