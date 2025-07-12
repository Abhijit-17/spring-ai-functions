package springai.spring_ai_functions.functions;

import java.util.function.Function;

import org.springframework.web.client.RestClient;

import springai.spring_ai_functions.records.WeatherRequest;
import springai.spring_ai_functions.records.WeatherResponse;

public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse>{

    public static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";

    private final String apiNinjasKey;

    public WeatherServiceFunction(String apiNinjasKey) {
        this.apiNinjasKey = apiNinjasKey;
    }  

    @Override
    public WeatherResponse apply(WeatherRequest request) {
        // Here you would implement the logic to call the weather API using the provided request
        // and return a WeatherResponse based on the fetched data.

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

        return response;

    }

}
