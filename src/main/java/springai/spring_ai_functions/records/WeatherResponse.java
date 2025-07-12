package springai.spring_ai_functions.records;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("WeatherResponse DTO")
public record WeatherResponse(
    @JsonProperty("cloud_pct")
    @JsonPropertyDescription("Percentage of cloud cover")
    Integer cloudPct,

    @JsonProperty("temp")
    @JsonPropertyDescription("Temperature in degrees Celsius")
    Integer temp,

    @JsonProperty("feels_like")
    @JsonPropertyDescription("\"Feels like\" temperature in Celsius, considers humidity and wind")
    Integer feelsLike,

    @JsonProperty("humidity")
    @JsonPropertyDescription("Relative humidity percentage (0-100%)")
    Integer humidity,

    @JsonProperty("min_temp")
    @JsonPropertyDescription("Minimum temperature in Celsius for the location")
    Integer minTemp,

    @JsonProperty("max_temp")
    @JsonPropertyDescription("Maximum temperature in Celsius for the location")
    Integer maxTemp,

    @JsonProperty("wind_speed")
    @JsonPropertyDescription("Wind speed in km/h")
    BigDecimal windSpeed,

    @JsonProperty("wind_degrees")
    @JsonPropertyDescription("Wind direction in degrees (0-360, where 0 is North, 90 is East, etc.)")
    Integer windDegrees,

    @JsonProperty("sunrise")
    @JsonPropertyDescription("Epoch timestamp of Sunrise in seconds GMT")
    Long sunrise,

    @JsonProperty("sunset")
    @JsonPropertyDescription("Epoch timestamp of Sunset in seconds GMT")
    Long sunset
) {

}
