package springai.spring_ai_functions.records;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("WeatherResponse DTO")
public record WeatherResponse(
    @JsonPropertyDescription("Percentage of cloud cover")
    Integer cloudPct,
    @JsonPropertyDescription("Temperature in degrees Celsius")
    Integer temp,
    @JsonPropertyDescription("\"Feels like\" temperature in Celsius, considers humidity and wind")
    Integer feelsLike,
    @JsonPropertyDescription("Relative humidity percentage (0-100%)")
    Integer humidity,
    @JsonPropertyDescription("Minimum temperature in Celsius for the location")
    Integer minTemp,
    @JsonPropertyDescription("Maximum temperature in Celsius for the location")
    Integer maxTemp,
    @JsonPropertyDescription("Wind speed in km/h")
    BigDecimal windSpeed,
    @JsonPropertyDescription("Wind direction in degrees (0-360, where 0 is North, 90 is East, etc.)")
    Integer windDegrees,
    @JsonPropertyDescription("Epoch timestamp of Sunrise in seconds")
    Long sunrise,
    @JsonPropertyDescription("Epoch timestamp of Sunset in seconds")
    Long sunset
) {

}
