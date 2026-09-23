package com.lawny.backend.watering;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import org.springframework.stereotype.Service;
import com.lawny.backend.exception.ResourceNotFoundException;

@Service
public class WateringService {

    private final WateringRepository wateringRepository;

    public WateringService(WateringRepository wateringRepository) {
        this.wateringRepository = wateringRepository;
    }

    public List<WateringResponse> getAllWateringSchedules() {

        return wateringRepository.findAll()
                .stream()
                .sorted((watering1, watering2) -> getNextWateringDateTime(watering1)
                        .compareTo(getNextWateringDateTime(watering2)))
                .map(this::toResponse)
                .toList();
    }

    public WateringResponse getWateringById(Long id) {

        WateringSchedule watering = wateringRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Watering schedule with ID " + id + " was not found."));

        return toResponse(watering);
    }

    public WateringResponse createWatering(WateringRequest request) {

        WateringSchedule watering = new WateringSchedule(
                request.day(),
                request.time(),
                request.minutes());

        WateringSchedule savedWatering = wateringRepository.save(watering);

        return toResponse(savedWatering);
    }

    public WateringResponse updateWatering(Long id, WateringRequest request) {

        WateringSchedule watering = wateringRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Watering schedule with ID " + id + " was not found."));

        watering.setDay(request.day());
        watering.setTime(request.time());
        watering.setMinutes(request.minutes());

        WateringSchedule savedWatering = wateringRepository.save(watering);

        return toResponse(savedWatering);
    }

    public void deleteWatering(Long id) {

        WateringSchedule watering = wateringRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Watering schedule with ID " + id + " was not found."));

        wateringRepository.delete(watering);
    }

    private WateringResponse toResponse(WateringSchedule watering) {

        return new WateringResponse(
                watering.getId(),
                watering.getDay(),
                watering.getTime(),
                watering.getMinutes(),
                getNextWateringDateTime(watering));
    }

    private LocalDateTime getNextWateringDateTime(WateringSchedule watering) {

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        LocalDate nextDate = today.with(
                TemporalAdjusters.nextOrSame(watering.getDay()));

        if (nextDate.equals(today)
                && watering.getTime().isBefore(now)) {

            nextDate = today.with(
                    TemporalAdjusters.next(watering.getDay()));
        }

        return LocalDateTime.of(
                nextDate,
                watering.getTime());
    }

    public WateringResponse getNextWatering() {

        WateringSchedule nextWatering = wateringRepository.findAll()
                .stream()
                .min((watering1, watering2) -> getNextWateringDateTime(watering1)
                        .compareTo(getNextWateringDateTime(watering2)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No watering schedules were found."));

        return toResponse(nextWatering);
    }

}