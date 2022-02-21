package pjatk.sdrm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.sdrm.exception.DuplicatedUsernameException;
import pjatk.sdrm.exception.ReservationNotFoundException;
import pjatk.sdrm.exception.UserNotFoundException;
import pjatk.sdrm.model.dto.UserDtoRequest;
import pjatk.sdrm.model.dto.UserDtoResponse;
import pjatk.sdrm.model.entity.PlaceDetails;
import pjatk.sdrm.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> save(@Valid @RequestBody UserDtoRequest userDtoRequest) throws DuplicatedUsernameException {
        return ResponseEntity.ok(userService.save(userDtoRequest));
    }

    @GetMapping()
    public ResponseEntity<List<UserDtoResponse>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> findUserByName(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/{userId}/res") //todo check chyba do usuniecia
    public ResponseEntity<Optional<List<PlaceDetails>>> findAuthorAllMovies(@PathVariable Long userId) throws ReservationNotFoundException {
        return ResponseEntity.ok(userService.findAllReservationsByUserId(userId));
    }
}
