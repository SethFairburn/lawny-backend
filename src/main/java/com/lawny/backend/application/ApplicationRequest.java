package com.lawny.backend.application;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ApplicationRequest(

        @NotNull(message = "Product is required")
        @Positive(message = "Product ID must be positive")
        Long productId,

        @NotNull(message = "Scheduled date is required")
        LocalDate scheduledDate,

        @NotBlank(message = "Rate is required")
        String rate,

        LocalDate appliedDate) {

}

/*
This is the DTO for data going in.


Entity = represents our stored domain data.
DTO = represents data crossing a boundary, such as an HTTP request.
*/