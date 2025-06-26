package com.hrm.exception;

public class WrongWayDateEntryException extends RuntimeException{
    public WrongWayDateEntryException(String message){
        super(message);
    }
}
