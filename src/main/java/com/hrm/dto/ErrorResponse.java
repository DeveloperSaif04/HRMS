package com.hrm.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timeAndDate;
    private  String errorStatusCode;
    private  String  errorMessage;
    private  String errorDetails;

    public ErrorResponse(String errorStatusCode,String  errorMessage,String errorDetails){
          this.timeAndDate = LocalDateTime.now();
          this.errorStatusCode= errorStatusCode;
          this.errorMessage=errorMessage;
          this.errorDetails=errorDetails;
    }

    public LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    public String getErrorStatusCode() {
        return errorStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

}
