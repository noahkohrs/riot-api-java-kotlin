package apis.template

import com.fasterxml.jackson.annotation.JsonProperty
//TODO: Create necessary data classes for the responses from the API

// TODO: Remove/Transform this example.
data class SomeResponse(
    // @JsonProperty is the name in the Json response
    @JsonProperty("someField")
    val field: Int,
    @JsonProperty("someListOfFields")
    val someListOfFields: List<Int>,
)

