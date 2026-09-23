package com.lawny.backend.watering;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record WateringResponse(
                Long id,
                DayOfWeek day,
                LocalTime time,
                Integer minutes,
                LocalDateTime nextWatering) {
}