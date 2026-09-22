package com.lawny.backend;

import java.time.LocalDate;

public record ApplicationRequest(
        Long productId,
        LocalDate scheduledDate,
        String rate,
        LocalDate appliedDate) {
}

/*
This is the DTO for data going in.


Entity = represents our stored domain data.
DTO = represents data crossing a boundary, such as an HTTP request.
*/