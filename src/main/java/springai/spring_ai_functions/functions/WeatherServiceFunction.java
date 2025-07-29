package springai.spring_ai_functions.functions;

import java.util.function.Function;

import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonClassDescription;

import springai.spring_ai_functions.records.WeatherRequest;
import springai.spring_ai_functions.records.WeatherResponse;

@JsonClassDescription("WeatherServiceFunction to fetch weather information")
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse>{

    public static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";

    private final String apiNinjasKey;

    public WeatherServiceFunction(String apiNinjasKey) {
        this.apiNinjasKey = apiNinjasKey;
    }  

    @Override
    public WeatherResponse apply(WeatherRequest request) {

        System.out.println("Weather Request: " + request);

        RestClient restClient = RestClient.builder()
                .baseUrl(WEATHER_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Content-Type", "application/json");
                    httpHeaders.set("Accept", "application/json");
                })
                .build();

        WeatherResponse response = restClient.get()
                .uri(uriBuilder -> {
                    return uriBuilder.queryParam("lat", request.lat())
                              .queryParam("lon", request.lon())
                              .build();
                }).retrieve().body(WeatherResponse.class);

        System.out.println("Weather Response: " + response);

        return response;

    }

}
