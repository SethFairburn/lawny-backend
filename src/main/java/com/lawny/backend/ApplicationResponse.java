package com.lawny.backend;

import java.time.LocalDate;

public record ApplicationResponse(
        Long id,
        Product product,
        LocalDate scheduledDate,
        String rate,
        LocalDate appliedDate,
        ApplicationStatus status) {
}

/*
This is the DTO for data going out.


Entity = represents our stored domain data.
DTO = represents data crossing a boundary, such as an HTTP request.
*/