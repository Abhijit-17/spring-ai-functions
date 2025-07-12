package springai.spring_ai_functions.records;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("WeatherRequest DTO")
public record WeatherRequest(
    @JsonProperty(required = true, value = "lat") 
    @JsonPropertyDescription("The lattitude of the place for which weather information needs to be fetched") 
    double lat,
    @JsonProperty(required = true, value = "lon") 
    @JsonPropertyDescription("The longitude of the place for which weather information needs to be fetched")
    double lon) {

}
