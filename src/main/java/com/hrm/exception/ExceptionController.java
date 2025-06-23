package com.hrm.exception;

import com.hrm.dto.ErrorResponse;
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

}


