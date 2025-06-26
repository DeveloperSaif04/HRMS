package com.hrm.exception;

public class BookingOverlapException extends RuntimeException{
    public BookingOverlapException(String message){
        super(message);
    }
}
