package com.lawny.backend.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final RestClient restClient = RestClient.create();

    public WeatherResponse getWeather() {

        String url = "https://api.open-meteo.com/v1/forecast"
                + "?latitude=30.2672"
                + "&longitude=-97.7431"
                + "&current=temperature_2m,weather_code"
                + "&daily=weather_code,precipitation_probability_max"
                + "&temperature_unit=fahrenheit"
                + "&timezone=America/Chicago";

        OpenMeteoResponse openMeteoResponse = restClient
                .get()
                .uri(url)
                .retrieve()
                .body(OpenMeteoResponse.class);

        List<DailyForecast> dailyForecasts = new ArrayList<>();

        for (int i = 0; i < openMeteoResponse.daily().time().size(); i++) {

            String date = openMeteoResponse.daily().time().get(i);
            Integer weatherCode = openMeteoResponse.daily().weather_code().get(i);
            Integer rainChance = openMeteoResponse.daily()
                    .precipitation_probability_max()
                    .get(i);
            String condition = getCondition(weatherCode);
            String icon = getIcon(weatherCode);

            DailyForecast forecast = new DailyForecast(
                    date,
                    condition,
                    icon,
                    rainChance);

            dailyForecasts.add(forecast);

        }

        Double currentTemperature = openMeteoResponse.current().temperature_2m();
        Integer currentWeatherCode = openMeteoResponse.current().weather_code();

        String currentCondition = getCondition(currentWeatherCode);
        String currentIcon = getIcon(currentWeatherCode);

        return new WeatherResponse(
                currentTemperature,
                currentCondition,
                currentIcon,
                dailyForecasts);
    }

    private String getCondition(Integer weatherCode) {
        if (weatherCode == 0) {
            return "Clear";
        } else if (weatherCode <= 3) {
            return "Cloudy";
        } else if (weatherCode <= 48) {
            return "Foggy";
        } else if (weatherCode <= 57) {
            return "Drizzle";
        } else if (weatherCode <= 67) {
            return "Rain";
        } else if (weatherCode <= 77) {
            return "Snow";
        } else if (weatherCode <= 82) {
            return "Rain Showers";
        } else if (weatherCode <= 86) {
            return "Snow Showers";
        } else {
            return "Thunderstorm";
        }
    }

    private String getIcon(Integer weatherCode) {
        if (weatherCode == 0) {
            return "☀️";
        } else if (weatherCode <= 3) {
            return "☁️";
        } else if (weatherCode <= 48) {
            return "🌫️";
        } else if (weatherCode <= 57) {
            return "🌦️";
        } else if (weatherCode <= 82) {
            return "🌧️";
        } else if (weatherCode <= 86) {
            return "🌨️";
        } else {
            return "⛈️";
        }
    }
}