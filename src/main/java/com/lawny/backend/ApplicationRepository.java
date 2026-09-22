package com.lawny.backend;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    List<Application> findByAppliedDateIsNullAndScheduledDateGreaterThanEqualOrderByScheduledDateAsc(
            LocalDate date);

    List<Application> findByAppliedDateIsNullAndScheduledDateBeforeOrderByScheduledDateAsc(
            LocalDate date);

    List<Application> findByAppliedDateIsNotNullOrderByAppliedDateDesc();

}
/*
 * JpaRepository gives us common database operations automatically.
 * We DO NOT need to implement these ourselves.
 *
 * ----- READ -----
 *
 * findAll()
 * Returns all Application records.
 *
 * findById(id)
 * Finds one Application by its ID.
 * Returns Optional<Application>.
 *
 * existsById(id)
 * Returns true if an Application with that ID exists.
 *
 * count()
 * Returns the total number of Application records.
 *
 *
 * ----- CREATE / UPDATE -----
 *
 * save(application)
 * Saves an Application.
 * - New entity -> INSERT
 * - Existing entity -> UPDATE
 *
 * saveAll(applications)
 * Saves multiple Applications at once.
 *
 *
 * ----- DELETE -----
 *
 * delete(application)
 * Deletes the supplied Application object.
 *
 * deleteById(id)
 * Deletes an Application using its ID.
 *
 * deleteAll()
 * Deletes all Applications.
 *
 * deleteAllById(ids)
 * Deletes multiple Applications by their IDs.
 *
 *
 * ----- OTHER USEFUL JPA METHODS -----
 *
 * flush()
 * Immediately synchronizes pending changes with the database.
 *
 * saveAndFlush(application)
 * Saves an Application and immediately flushes the change.
 *
 *
 * ----- CUSTOM QUERIES -----
 *
 * We can also define our own repository methods here.
 * Spring Data JPA can often generate the query from the method name.
 *
 * Examples:
 *
 * List<Application> findByScheduledDateAfter(LocalDate date);
 *
 * List<Application> findByProductId(Long productId);
 *
 * List<Application> findByAppliedDateIsNull();
 *
 * These are NOT automatically present.
 * We add them when Lawny needs specific database queries.
 */
