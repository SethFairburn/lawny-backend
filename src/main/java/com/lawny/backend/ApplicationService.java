package com.lawny.backend;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ProductRepository productRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            ProductRepository productRepository) {

        this.applicationRepository = applicationRepository;
        this.productRepository = productRepository;
    }

    public List<ApplicationResponse> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ApplicationResponse getApplicationById(Long id) {

        Application application = applicationRepository.findById(id).orElse(null);

        if (application == null) {
            return null;
        }

        return toResponse(application);
    }

    public ApplicationResponse createApplication(ApplicationRequest request) {

        Product product = productRepository
                .findById(request.productId())
                .orElse(null);

        if (product == null) {
            return null;
        }

        Application application = new Application(
                product,
                request.scheduledDate(),
                request.rate(),
                request.appliedDate());

        Application savedApplication = applicationRepository.save(application);

        return toResponse(savedApplication);
    }

    public ApplicationResponse updateApplication(
            Long id,
            ApplicationRequest request) {

        Application existingApplication = applicationRepository.findById(id).orElse(null);

        if (existingApplication == null) {
            return null;
        }

        Product product = productRepository.findById(request.productId()).orElse(null);

        if (product == null) {
            return null;
        }

        existingApplication.setProduct(product);
        existingApplication.setScheduledDate(request.scheduledDate());
        existingApplication.setRate(request.rate());
        existingApplication.setAppliedDate(request.appliedDate());

        Application savedApplication = applicationRepository.save(existingApplication);

        return toResponse(savedApplication);
    }

    public ApplicationStatus getApplicationStatus(Application application) {

        if (application.getAppliedDate() != null) {
            return ApplicationStatus.APPLIED;
        }

        if (application.getScheduledDate().isBefore(LocalDate.now())) {
            return ApplicationStatus.MISSED;
        }

        return ApplicationStatus.UPCOMING;
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public ApplicationResponse markAsApplied(Long id) {

        Application application = applicationRepository.findById(id).orElse(null);

        if (application == null) {
            return null;
        }

        application.setAppliedDate(LocalDate.now());

        Application savedApplication = applicationRepository.save(application);

        return toResponse(savedApplication);
    }

    private ApplicationResponse toResponse(Application application) {

        return new ApplicationResponse(
                application.getId(),
                application.getProduct(),
                application.getScheduledDate(),
                application.getRate(),
                application.getAppliedDate(),
                getApplicationStatus(application));
    }

    public List<ApplicationResponse> getUpcomingApplications() {

        return applicationRepository
                .findByAppliedDateIsNullAndScheduledDateGreaterThanEqualOrderByScheduledDateAsc(
                        LocalDate.now())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ApplicationResponse> getMissedApplications() {

        return applicationRepository
                .findByAppliedDateIsNullAndScheduledDateBeforeOrderByScheduledDateAsc(
                        LocalDate.now())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ApplicationResponse> getAppliedApplications() {

    return applicationRepository
            .findByAppliedDateIsNotNullOrderByAppliedDateDesc()
            .stream()
            .map(this::toResponse)
            .toList();
}
}

/*
 * This is where we put endpoints. That's one of the Controller's main jobs:
 * define which HTTP addresses/actions your backend exposes to the outside
 * world.
 */