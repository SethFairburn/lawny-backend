package com.lawny.backend;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable("id") Long id) {

        ApplicationResponse application = applicationService.getApplicationById(id);

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(application);
    }

    @PostMapping
    public ApplicationResponse createApplication(
            @RequestBody ApplicationRequest request) {

        return applicationService.createApplication(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(
            @PathVariable("id") Long id,
            @RequestBody ApplicationRequest request) {

        ApplicationResponse application = applicationService.updateApplication(id, request);

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(application);
    }

    @PutMapping("/{id}/applied")
    public ResponseEntity<ApplicationResponse> markAsApplied(
            @PathVariable("id") Long id) {

        ApplicationResponse application = applicationService.markAsApplied(id);

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(application);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable("id") Long id) {
        applicationService.deleteApplication(id);
    }

    @GetMapping("/upcoming")
    public List<ApplicationResponse> getUpcomingApplications() {
        return applicationService.getUpcomingApplications();
    }

    @GetMapping("/missed")
    public List<ApplicationResponse> getMissedApplications() {
        return applicationService.getMissedApplications();
    }

    @GetMapping("/history")
    public List<ApplicationResponse> getAppliedApplications() {
        return applicationService.getAppliedApplications();
    }
}
