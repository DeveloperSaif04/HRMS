package com.hrm.exception;

import com.hrm.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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
    public ErrorResponse userNotFound(UserNotFoundException userNotFound,WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(
                "404",
                userNotFound.getMessage(),
                webRequest.getDescription(false)
        );
        return errorResponse;
    }

//    @ExceptionHandler(MethodArgNotValid.class)
//    public ErrorResponse methodInvaildArg(MethodArgNotValid methodArgumentNotValidException
//    ,WebRequest webRequest) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                "404",
//                methodArgumentNotValidException.getMessage(),
//        webRequest.getDescription(false)
//         );
//        return  errorResponse;
//
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "400",
                "Validation Failed: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }




}


