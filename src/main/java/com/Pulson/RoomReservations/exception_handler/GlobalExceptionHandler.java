package com.Pulson.RoomReservations.exception_handler;

import com.Pulson.RoomReservations.room.RoomNotFoundException;
import com.Pulson.RoomReservations.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class, RoomNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleResourceNotFound(Exception exception, WebRequest request){
        return new ResponseEntity<>(new ErrorMessage(
                        LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage()),
                        HttpStatus.NOT_FOUND);
    }
}
