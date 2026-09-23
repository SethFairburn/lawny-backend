package com.lawny.backend.watering;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/watering")
public class WateringController {

    private final WateringService wateringService;

    public WateringController(WateringService wateringService) {
        this.wateringService = wateringService;
    }

    @GetMapping
    public List<WateringResponse> getAllWateringSchedules() {
        return wateringService.getAllWateringSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WateringResponse> getWateringById(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(wateringService.getWateringById(id));
    }

    @PostMapping
    public WateringResponse createWatering(
            @Valid @RequestBody WateringRequest request) {

        return wateringService.createWatering(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WateringResponse> updateWatering(
            @PathVariable("id") Long id,
            @Valid @RequestBody WateringRequest request) {

        return ResponseEntity.ok(
                wateringService.updateWatering(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteWatering(
            @PathVariable("id") Long id) {

        wateringService.deleteWatering(id);
    }

    @GetMapping("/next")
    public ResponseEntity<WateringResponse> getNextWatering() {

        return ResponseEntity.ok(
                wateringService.getNextWatering());
    }

}