package apis.template

import com.squareup.moshi.Json

// TODO: Create necessary data classes for the responses from the API

// TODO: Remove/Transform this example.
public data class SomeResponse(
    // @JsonProperty is the name in the Json response
    @Json(name = "someField")
    val field: Int,
    @Json(name = "someListOfFields")
    val someListOfFields: List<Int>,
)
