package pjatk.sdrm.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pjatk.sdrm.exception.*;
import pjatk.sdrm.model.error.ErrorModel;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.BAD_REQUEST.value(),
                ex.toString(), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SpaceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleSpaceNotFoundException(SpaceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.NOT_FOUND.value(),
                ex.toString(), Instant.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectHourException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleHourException(IncorrectHourException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.NOT_FOUND.value(),
                ex.toString(), Instant.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.BAD_REQUEST.value(),
                ex.toString(), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handlePlaceNotFoundException(PlaceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.BAD_REQUEST.value(),
                ex.toString(), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TimeslotNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleTimeslotNotFoundException(TimeslotNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.BAD_REQUEST.value(),
                ex.toString(), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongReservationDateException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleWrongReservationDateException(WrongReservationDateException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.FORBIDDEN.value(),
                ex.toString(), Instant.now()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DuplicatedUsernameException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleDuplicatedUsernameException(DuplicatedUsernameException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.FORBIDDEN.value(),
                ex.toString(), Instant.now()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleReservationNotFoundException(ReservationNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.NOT_FOUND.value(),
                ex.toString(), Instant.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(HttpStatus.NOT_FOUND.value(),
                ex.toString(), Instant.now()), HttpStatus.NOT_FOUND);
    }
}