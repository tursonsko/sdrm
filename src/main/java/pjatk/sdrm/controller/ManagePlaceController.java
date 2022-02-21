package pjatk.sdrm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pjatk.sdrm.exception.IncorrectHourException;
import pjatk.sdrm.exception.PlaceNotFoundException;
import pjatk.sdrm.exception.SpaceNotFoundException;
import pjatk.sdrm.exception.WrongReservationDateException;
import pjatk.sdrm.model.dto.PlaceDtoRequest;
import pjatk.sdrm.model.dto.PlaceDtoResponse;
import pjatk.sdrm.service.PlaceDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/manage/reservation")
public class ManagePlaceController {

    private final PlaceDetailsService placeDetailsService;

    public ManagePlaceController(PlaceDetailsService placeDetailsService) {
        this.placeDetailsService = placeDetailsService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}") //dziala
    public ResponseEntity<PlaceDtoResponse> findById(@PathVariable Long id) throws PlaceNotFoundException {
        return ResponseEntity.ok(placeDetailsService.findById(id));
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/timeslot/{timeslotId}")
    public ResponseEntity<?> findSpecificTimeSlot(@PathVariable Long id, @PathVariable Long timeslotId) {
        return ResponseEntity.ok(placeDetailsService.findTimeSlot(id, timeslotId));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<PlaceDtoResponse>> findAllReservations() {
        return ResponseEntity.ok(placeDetailsService.findAllReservations());
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        placeDetailsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping //tworzenie interwałów timeslot
    public ResponseEntity<PlaceDtoResponse> save(@RequestBody PlaceDtoRequest placeDtoRequest) throws IncorrectHourException, WrongReservationDateException, SpaceNotFoundException {
        return ResponseEntity.ok(placeDetailsService.save(placeDtoRequest));
    }
}