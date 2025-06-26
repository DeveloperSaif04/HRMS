package com.hrm.exception;

import com.hrm.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponse UserAlreadExist(UserAlreadyExistsException userAlready, WebRequest webRequest){
        ErrorResponse errorResponse =  new ErrorResponse(
                "403",
                userAlready.getMessage(),
                webRequest.getDescription(false)
        );
        return errorResponse;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse userNotFound(UserNotFoundException userNotFound,WebRequest webRequest){ErrorResponse errorResponse = new ErrorResponse(
                "404",
                userNotFound.getMessage(),
                webRequest.getDescription(false)
        );
        return errorResponse;
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "400",
                "Validation Failed: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //Property and Room not available exception handling.
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Object> handleNoHotelNotFound(HotelNotFoundException e){
        return buildResponse(HttpStatus.NOT_FOUND,e.getMessage());
    }
    @ExceptionHandler(NoRoomAvailableException.class)
    public ResponseEntity<Object> handleNoRoomFound(NoRoomAvailableException e){
        return buildResponse(HttpStatus.OK, e.getMessage());
    }
    @ExceptionHandler(WrongWayDateEntryException.class)
    public ResponseEntity<Object> handleWrongWayDateEntry(WrongWayDateEntryException e){
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status,String message){
        Map<String ,Object> body=new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("Status",status.value());
        body.put("error",status.getReasonPhrase());
        body.put("message",message);
        return new ResponseEntity<>(body,status);
    }




}


