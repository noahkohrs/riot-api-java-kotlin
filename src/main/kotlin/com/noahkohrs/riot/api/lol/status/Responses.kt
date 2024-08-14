package apis.lol.status

import com.squareup.moshi.Json

//ContentDto
public data class ContentDto(
    @Json(name = "locale")
    val locale: String,
    @Json(name = "content")
    val content: String
)
//UpdateDto
public data class UpdateDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "author")
    val author: String,
    @Json(name = "publish")
    val publish: Boolean,
    @Json(name = "publish_locations")
    val publishLocations: List<String>,
    @Json(name = "translations")
    val translations: List<ContentDto>,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
)

//StatusDto
public data class StatusDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "maintenance_status")
    val maintenanceStatus: String,
    @Json(name = "incident_severity")
    val incidentSeverity: String?,
    @Json(name = "titles")
    val titles: List<ContentDto>,
    @Json(name = "updates")
    val updates: List<UpdateDto>,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "archive_at")
    val archiveAt: String,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "platforms")
    val platforms: List<String>,
)

//PlatformDataDto
public data class PlatformResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "locales")
    val locales: List<String>,
    @Json(name = "maintenances")
    val maintenances: List<StatusDto>,
    @Json(name = "incidents")
    val incidents: List<StatusDto>,
)


