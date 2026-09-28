package com.lawny.backend.weather;

import java.util.List;

public record OpenMeteoResponse(
        Current current,
        Daily daily) {

    public record Current(
            Double temperature_2m,
            Integer weather_code) {
    }

    public record Daily(
            List<String> time,
            List<Integer> weather_code,
            List<Integer> precipitation_probability_max) {
    }
}