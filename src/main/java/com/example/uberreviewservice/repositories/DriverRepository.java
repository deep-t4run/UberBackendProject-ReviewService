package com.example.uberreviewservice.repositories;

import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(long id, String licenseNumber);

    List<Driver> findAllByIdIn(List<Long> DriverIds);

//    @Query(nativeQuery = true, value = "select * from Driver where id = :id and license_number = :license") // raw sql query, error is thrown at run time only
//    Optional<Driver> rawFindByIdAndLicenseNumber(long id, String license);
//
//    @Query("From Driver as d where d.id = :id and d.licenseNumber = :license")  // hibernate query, error is thrown at compile time
//    Optional<Driver> hqlFindByIdAndLicenseNumber(long id, String license);
}
