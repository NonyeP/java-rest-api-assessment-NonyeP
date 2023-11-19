package com.cbfacademy.apiassessment.exceptionHandlers;

import java.time.LocalDateTime;

/* This class is designed to log Error details that includes the date and 
 * time the error occurs the message and its details that will arise while
 * running the project.
 * 
 * This class have methods that gets and sets date and time of error, the 
 * error message and its details.
 */

public class ErrorDetailsInProject {
    private LocalDateTime dateTime;
    private String errorMessage;
    private String errorDetails;
    
    
    public ErrorDetailsInProject(LocalDateTime dateTime, String errorMessage, String errorDetails) {
        super();
        this.dateTime = dateTime;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorDetails() {
        return errorDetails;
    }


    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    } 

    

    
}
