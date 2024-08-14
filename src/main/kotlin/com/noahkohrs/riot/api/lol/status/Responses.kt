package com.noahkohrs.riot.api.lol.status

import com.fasterxml.jackson.annotation.JsonProperty

//ContentDto
public data class ContentDto(
    @JsonProperty("locale")
    val locale: String,
    @JsonProperty("content")
    val content: String
)
//UpdateDto
public data class UpdateDto(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("author")
    val author: String,
    @JsonProperty("publish")
    val publish: Boolean,
    @JsonProperty("publish_locations")
    val publishLocations: List<String>,
    @JsonProperty("translations")
    val translations: List<ContentDto>,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String,
)

//StatusDto
public data class StatusDto(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("maintenance_status")
    val maintenanceStatus: String,
    @JsonProperty("incident_severity")
    val incidentSeverity: String?,
    @JsonProperty("titles")
    val titles: List<ContentDto>,
    @JsonProperty("updates")
    val updates: List<UpdateDto>,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("archive_at")
    val archiveAt: String,
    @JsonProperty("updated_at")
    val updatedAt: String?,
    @JsonProperty("platforms")
    val platforms: List<String>,
)

//PlatformDataDto
public data class PlatformResponse(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("locales")
    val locales: List<String>,
    @JsonProperty("maintenances")
    val maintenances: List<StatusDto>,
    @JsonProperty("incidents")
    val incidents: List<StatusDto>,
)


