package com.lawny.backend.weather;

public record DailyForecast(
        String date,
        String condition,
        String icon,
        Integer rainChance) {
}