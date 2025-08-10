package com.uber.UberReviewService.repositories;

import com.uber.UberReviewService.model.CustomDriver;
import com.uber.UberReviewService.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

//    @Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id = :id AND license_number = :licenseNumber") // Raw mysql query, where error is thrown at runtime
//    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String licenseNumber);

    /*
        Notes on Interface Projection in Spring Data JPA
            What Are Interface Projections?
                Interface projections in Spring Data JPA enable you to define Java interfaces containing getter methods
                for only the fields you want to retrieve from an entity.

                When a repository method returns a projection interface, Spring generates a proxy at runtime, populating
                its properties based on entity field values.

            Why Use Interface Projections?
                Type-Safety:
                    You get compile-time safety: your interface methods must match entity fields, so typos or missing
                    properties are caught early.

                Reduced Boilerplate:
                    No need for manual DTO classes or complex mappings—Spring generates instances of your interface for
                    each result.

                Performance Optimization:
                    Only the fields declared in your interface are fetched from the database, lowering bandwidth and
                    reducing unnecessary data loading.

                Reusable & Maintainable:
                    Change your data view by updating the interface; you don’t need to touch queries or mapping logic.

             How to Define an Interface Projection
                Assume you have an entity Driver:

                java
                @Entity
                public class Driver {
                    private Long id;
                    private String name;
                    private String licenseNumber;
                    // ... getters/setters
                }
                Projection Example:

                java
                public interface CustomDriver {
                    Long getId();
                    String getName();
                }
                Using Projections in Repository Queries

                java
                @Repository
                public interface DriverRepository extends JpaRepository<Driver, Long> {

                    @Query("FROM Driver d WHERE d.id = :id AND d.licenseNumber = :ln")
                    Optional<CustomDriver> findDriverView(Long id, String ln);
                }
                Spring will generate a proxy for CustomDriver that returns only the id and name fields.

                Works with both JPQL and native queries (sometimes requires explicit field selection).

             Key Points
                No Manual Implementation Required:
                    You do NOT need to implement the interface. Spring Data JPA creates proxy instances automatically.

                Getter Name Mapping:
                    The interface method names should correspond to entity property names (e.g., getId() → id).

                Works With Optional, Page, List, etc.:
                    Repository methods can return a single projection, lists, pages, or Optionals of the projection
                    interface.

                Multiple Projections:
                    You can define as many projection interfaces as you need, for different use cases and views.

                Limitations & Notes
                    Interface projections only retrieve simple, direct properties of the entity.

                    For nested or computed properties, use DTO projections (constructor expressions) or class-based
                    projections.

                    Spring Data JPA projections are mainly read-only; you cannot update via projections.

                    Native queries may require explicit selection of fields matching your interface.

                Example Usage
                java
                Optional<CustomDriver> result = driverRepository.findDriverView(1L, "DL1234");
                if (result.isPresent()) {
                    System.out.println(result.get().getName());
                }
    */

    @Query("FROM Driver as d WHERE d.id = :id AND d.licenseNumber = :ln") // hibernate query, where error is thrown at compile time.
    Optional<CustomDriver> rawFindByIdAndLicenseNumber(Long id, String ln);

}
