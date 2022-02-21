package pjatk.sdrm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.sdrm.exception.PlaceNotFoundException;
import pjatk.sdrm.exception.TimeslotNotFoundException;
import pjatk.sdrm.exception.UserNotFoundException;
import pjatk.sdrm.model.dto.BookingDetails;
import pjatk.sdrm.model.dto.BookingStatus;
import pjatk.sdrm.service.BookingService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final BookingService bookingService;

    public ReservationController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PutMapping("/book")
    public ResponseEntity<BookingStatus> bookTimeSlot(@RequestBody BookingDetails bookingDetails)
            throws UserNotFoundException, PlaceNotFoundException, TimeslotNotFoundException {
        return ResponseEntity.ok(bookingService.addBooking(bookingDetails));
    }

    @GetMapping("/user/{userId}/booked")
    public ResponseEntity<?> getAllBookedSlotsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getAllBookedSlotsByUserId(userId));
    }

    @DeleteMapping("/user/{userId}/{reservationId}")
    public ResponseEntity<?> deleteReservationById(@PathVariable Long userId, @PathVariable Long reservationId) {
        bookingService.deleteReservationById(userId, reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/cancelled")
    public ResponseEntity<?> getAllCanceled(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getAllCanceledReservation(userId));
    }
}
