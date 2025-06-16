package com.example.uberreviewservice.services;

import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Driver;
import com.example.uberreviewservice.repositories.BookingRepository;
import com.example.uberreviewservice.repositories.DriverRepository;
import com.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class ReviewService implements CommandLineRunner {

    private final BookingRepository bookingRepository;
    private final ReviewRepository reviewRepository;
    private final DriverRepository driverRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, DriverRepository driverRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("*********************");

//        Review r = Review.builder().content("Amazing ride").rating(4.2).build();
//
//        Booking b = Booking
//                .builder()
//                .review(r)
//                .endTime(new Date())
//                .build();
//
//        bookingRepository.save(b);
//
//        System.out.println(r);
////       // this code executes sql queryAdd commentMore actions
//        System.out.println(r.getId());
//        List<Review> reviews = reviewRepository.findAll();
//
//        for(Review review : reviews) {
//            System.out.println(r.getContent());
//        }
//
//        Optional<Booking> booking = bookingRepository.findById(5L);
//
//        if(booking.isPresent()){
//            bookingRepository.delete(booking.get());
//        }

//        List<Booking> bookings = bookingRepository.findByDriverId(2L);
//
//        for(Booking booking : bookings) {
//            System.out.println(booking.getBookingStatus());
//        }

//        Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L, "DL1212");
//        if(driver.isPresent()) {
//            System.out.println(driver.get().getName());
//        }

//        Optional<Driver> driver = driverRepository.rawFindByIdAndLicenseNumber(1L, "DL1212");
//        Optional<Driver> driver = driverRepository.hqlFindByIdAndLicenseNumber(1L, "DL1212");
//        System.out.println(driver.get().getName());

        List<Long> driverIds = new ArrayList<>(Arrays.asList(1L, 2L));
        List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);

        //List<Booking> b = bookingRepository.findAllByDriverIn(drivers);

        for(Driver driver : drivers) {
            List<Booking> bookings = driver.getBookings();
            bookings.forEach(booking -> System.out.println(booking.getId()));
        }
    }
}
