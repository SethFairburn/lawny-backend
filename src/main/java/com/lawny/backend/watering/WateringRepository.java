package com.lawny.backend.watering;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringRepository
        extends JpaRepository<WateringSchedule, Long> {
}