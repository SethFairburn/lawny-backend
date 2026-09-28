package com.lawny.backend.weather;

import java.util.List;

public record WeatherResponse(
        Double currentTemperature,
        String currentCondition,
        String currentIcon,
        List<DailyForecast> dailyForecast) {
}