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
    public ApplicationResponse getApplicationById(
            @PathVariable("id") Long id) {

        return applicationService.getApplicationById(id);
    }

    @PostMapping
    public ApplicationResponse createApplication(
            @RequestBody ApplicationRequest request) {

        return applicationService.createApplication(request);
    }

    @PutMapping("/{id}")
    public ApplicationResponse updateApplication(
            @PathVariable("id") Long id,
            @RequestBody ApplicationRequest request) {

        return applicationService.updateApplication(id, request);
    }

    @PutMapping("/{id}/applied")
    public ApplicationResponse markAsApplied(
            @PathVariable("id") Long id) {

        return applicationService.markAsApplied(id);
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
