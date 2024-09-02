package com.noahkohrs.riot.api.lol.status

import com.noahkohrs.riot.api.annotations.UnstableApi
import com.noahkohrs.riot.api.dtos.ContentDto
import com.noahkohrs.riot.api.dtos.PlatformDataDto
import com.noahkohrs.riot.api.dtos.StatusDto
import com.noahkohrs.riot.api.dtos.UpdateDto

@UnstableApi
public class Content private constructor(
    @JvmField public val locale: String,
    @JvmField public val content: String,
) {
    internal companion object {
        fun fromDto(dto: ContentDto): Content {
            return Content(
                locale = dto.locale,
                content = dto.content,
            )
        }
    }
}

@UnstableApi
public class Update private constructor(
    @JvmField public val id: Int,
    @JvmField public val author: String,
    @JvmField public val publish: Boolean,
    @JvmField public val publishLocations: List<String>,
    @JvmField public val translations: List<Content>,
    @JvmField public val createdAt: String,
    @JvmField public val updatedAt: String,
) {
    internal companion object {
        fun fromDto(dto: UpdateDto): Update {
            return Update(
                id = dto.id,
                author = dto.author,
                publish = dto.publish,
                publishLocations = dto.publishLocations,
                translations = dto.translations.map { Content.fromDto(it) },
                createdAt = dto.createdAt,
                updatedAt = dto.updatedAt,
            )
        }
    }
}

@UnstableApi
public class Status private constructor(
    @JvmField public val id: Int,
    @JvmField public val maintenanceStatus: String,
    @JvmField public val incidentSeverity: String?,
    @JvmField public val titles: List<Content>,
    @JvmField public val updates: List<Update>,
    @JvmField public val createdAt: String,
    @JvmField public val archiveAt: String,
    @JvmField public val updatedAt: String?,
    @JvmField public val platforms: List<String>,
) {
    internal companion object {
        fun fromDto(dto: StatusDto): Status {
            return Status(
                id = dto.id,
                maintenanceStatus = dto.maintenanceStatus,
                incidentSeverity = dto.incidentSeverity,
                titles = dto.titles.map { Content.fromDto(it) },
                updates = dto.updates.map { Update.fromDto(it) },
                createdAt = dto.createdAt,
                archiveAt = dto.archiveAt,
                updatedAt = dto.updatedAt,
                platforms = dto.platforms,
            )
        }
    }
}

@UnstableApi
public class PlatformData private constructor(
    @JvmField public val id: String,
    @JvmField public val name: String,
    @JvmField public val locales: List<String>,
    @JvmField public val maintenances: List<Status>,
    @JvmField public val incidents: List<Status>,
) {
    internal companion object {
        fun fromDto(dto: PlatformDataDto): PlatformData {
            return PlatformData(
                id = dto.id,
                name = dto.name,
                locales = dto.locales,
                maintenances = dto.maintenances.map { Status.fromDto(it) },
                incidents = dto.incidents.map { Status.fromDto(it) },
            )
        }
    }
}
