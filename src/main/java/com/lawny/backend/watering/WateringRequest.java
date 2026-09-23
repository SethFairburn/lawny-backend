package com.lawny.backend.watering;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record WateringRequest(

        @NotNull(message = "Day is required") DayOfWeek day,

        @NotNull(message = "Time is required") LocalTime time,

        @NotNull(message = "Minutes is required") @Positive(message = "Minutes must be positive") Integer minutes) {
}